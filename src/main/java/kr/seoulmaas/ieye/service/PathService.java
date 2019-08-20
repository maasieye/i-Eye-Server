package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.service.dto.busStation.BusStationResDto;
import kr.seoulmaas.ieye.service.dto.busStation.body.BusItem;
import kr.seoulmaas.ieye.service.dto.path.PathDetailResDto;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import kr.seoulmaas.ieye.service.dto.path.WalkPathReqDto;
import kr.seoulmaas.ieye.service.dto.path.WalkPathResDto;
import kr.seoulmaas.ieye.service.dto.path.walk.Point;
import kr.seoulmaas.ieye.service.utill.RestInfo;
import kr.seoulmaas.ieye.service.utill.RestTemplateConfig;
import kr.seoulmaas.ieye.service.utill.comparator.DistanceComparator;
import kr.seoulmaas.ieye.service.utill.comparator.SizeComparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PathService {

    private final RestTemplateConfig restTemplateConfig;
    private final RestInfo restInfo;


    public PathDetailResDto getPath(PathReqDto pathReqDto) {
        BusStationResDto busStationResDto = getBusPath(pathReqDto);
        BusItem fewSizeItem = getFewSizeItem(busStationResDto);

        WalkPathReqDto walkPathReqDto;
        WalkPathResDto walkPathResDto;

        List<Point> points = new ArrayList<>(getFirstPoints(pathReqDto, fewSizeItem));

        //첫 버스 타고
        points.add(fewSizeItem.getPathList().get(0).getStart());


        int callSize = fewSizeItem.getSize() - 1;
        for (int i = 0; i < callSize; i++) {

            walkPathReqDto = getMidleWalkPathReqDto(pathReqDto, fewSizeItem, i);

            //버스 내리고
            points.add(fewSizeItem.getPathList().get(i).getEnd());
            if (walkPathReqDto.isNotMove()) {
                //버스 내린곳에서 다시 타고
                points.add(fewSizeItem.getPathList().get(i + 1).getStart());
                continue;
            }

            walkPathResDto = getWalkPath(walkPathReqDto);
            //걷고
            points.addAll(getAllPoints(walkPathResDto));
            //버스 타고
            points.add(fewSizeItem.getPathList().get(i + 1).getStart());

        }

        //마지막 버스 내리고
        points.add(fewSizeItem.getPathList().get(callSize).getEnd());

        points.addAll(getLastPoints(pathReqDto, fewSizeItem, callSize));

        return new PathDetailResDto(fewSizeItem.getTime(), points);
    }

    private List<Point> getFirstPoints(PathReqDto pathReqDto, BusItem fewSizeItem) {
        String beginX = pathReqDto.getStartX();
        String beginY = pathReqDto.getStartY();
        String beginName = "출발지";

        String firstX = fewSizeItem.getPathList().get(0).getFx();
        String firstY = fewSizeItem.getPathList().get(0).getFy();
        String firstName = fewSizeItem.getPathList().get(0).getFName();

        WalkPathReqDto walkPathReqDto = WalkPathReqDto.createBuilder()
                .startX(beginX)
                .startY(beginY)
                .startName(beginName)
                .endX(firstX)
                .endY(firstY)
                .endName(firstName)
                .build();

        WalkPathResDto walkPathResDto = getWalkPath(walkPathReqDto);
        return new ArrayList<>(getAllPoints(walkPathResDto));
    }

    private List<Point> getLastPoints(PathReqDto pathReqDto, BusItem fewSizeItem, int callSize) {

        String beforeX = fewSizeItem.getPathList().get(callSize).getFx();
        String beforeY = fewSizeItem.getPathList().get(callSize).getFy();
        String beforeName = fewSizeItem.getPathList().get(callSize).getTName();

        String finalX = pathReqDto.getEndX();
        String finalY = pathReqDto.getEndY();
        String finalName = "도착지";

        WalkPathReqDto walkPathReqDto = WalkPathReqDto.createBuilder()
                .startX(beforeX)
                .startY(beforeY)
                .startName(beforeName)
                .endX(finalX)
                .endY(finalY)
                .endName(finalName)
                .build();


        WalkPathResDto walkPathResDto = getWalkPath(walkPathReqDto);
        return new ArrayList<>(getAllPoints(walkPathResDto));
    }

    private WalkPathReqDto getMidleWalkPathReqDto(PathReqDto pathReqDto, BusItem fewSizeItem, int index) {
        String startX = fewSizeItem.getPathList().get(index).getTx();
        String startY = fewSizeItem.getPathList().get(index).getTy();
        String startName = fewSizeItem.getPathList().get(index).getTName();

        String endX = fewSizeItem.getPathList().get(index + 1).getFx();
        String endY = fewSizeItem.getPathList().get(index + 1).getFy();
        String endName = fewSizeItem.getPathList().get(index + 1).getFName();

        return WalkPathReqDto.createBuilder()
                .startX(startX)
                .startY(startY)
                .startName(startName)
                .endX(endX)
                .endY(endY)
                .endName(endName)
                .build();
    }

    public BusItem getShortestDistanceItem(BusStationResDto resDto) {
        DistanceComparator comparator = new DistanceComparator();
        return resDto.getItemList().stream()
                .min(comparator)
                .orElseThrow(() -> new RuntimeException("경로 없음"));
    }

    public BusItem getFewSizeItem(BusStationResDto resDto) {
        SizeComparator comparator = new SizeComparator();
        return resDto.getItemList().stream()
                .min(comparator)
                .orElseThrow(() -> new RuntimeException("경로없음"));
    }

    public BusStationResDto getBusPath(PathReqDto pathReqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        URI url = restInfo.getBusPathURI(pathReqDto);

        return restTemplate.getForObject(url, BusStationResDto.class);
    }

    public WalkPathResDto getWalkPath(WalkPathReqDto reqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = restInfo.getTMapHeaders();
        URI url = restInfo.getWalkPathURI(reqDto);

        HttpEntity httpEntity = new HttpEntity<>(reqDto, headers);
        WalkPathResDto walkPathResDto = restTemplate.exchange(url, HttpMethod.POST, httpEntity, WalkPathResDto.class)
                .getBody();
        System.out.println(walkPathResDto.toString());
        return walkPathResDto;
    }

    public List<Point> getAllPoints(WalkPathResDto resDto) {
        List<Point> points = new ArrayList<>();
        resDto.getFeatures().stream()
                .map(feature -> feature.getGeometry().getPoints(feature.getProperties().getTurnType()))
                .forEach(points::addAll);
        return points;
    }


}
