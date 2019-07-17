package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.service.dto.busStop.BusStopResDto;
import kr.seoulmaas.ieye.service.dto.busStop.body.BusItem;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import kr.seoulmaas.ieye.service.dto.path.WalkPathReqDto;
import kr.seoulmaas.ieye.service.dto.path.WalkPathResDto;
import kr.seoulmaas.ieye.service.dto.path.walk.Point;
import kr.seoulmaas.ieye.service.utill.PathInfo;
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
    private final PathInfo pathInfo;

    public List<Point> getPath(PathReqDto pathReqDto) {
        BusStopResDto busStopResDto = getBusPath(pathReqDto);
        BusItem fewSizeItem = getFewSizeItem(busStopResDto);

        WalkPathReqDto walkPathReqDto;
        WalkPathResDto walkPathResDto;

        Double beginX = Double.valueOf(pathReqDto.getStartX());
        Double beginY = Double.valueOf(pathReqDto.getStartY());
        String beginName = "출발지";

        Double firstX = fewSizeItem.getPathList().get(0).getDoubleFX();
        Double firstY = fewSizeItem.getPathList().get(0).getDoubleFY();
        String firstName = fewSizeItem.getPathList().get(0).getFName();

        walkPathReqDto = WalkPathReqDto.createBuilder()
                .startX(beginX)
                .startY(beginY)
                .startName(beginName)
                .endX(firstX)
                .endY(firstY)
                .endName(firstName)
                .build();

        walkPathResDto = getWalkPath(walkPathReqDto);
        List<Point> points = new ArrayList<>(getAllPoints(walkPathResDto));

        //첫 버스 타고
        points.add(fewSizeItem.getPathList().get(0).getStart());


        int callSize = fewSizeItem.getSize() - 1;
        for (int i = 0; i < callSize; i++) {
            log.info("i : " + i);
            Double startX = fewSizeItem.getPathList().get(i).getDoubleTX();
            Double startY = fewSizeItem.getPathList().get(i).getDoubleTY();
            String startName = fewSizeItem.getPathList().get(i).getTName();

            Double endX = fewSizeItem.getPathList().get(i + 1).getDoubleFX();
            Double endY = fewSizeItem.getPathList().get(i + 1).getDoubleFY();
            String endName = fewSizeItem.getPathList().get(i + 1).getFName();

            walkPathReqDto = WalkPathReqDto.createBuilder()
                    .startX(startX)
                    .startY(startY)
                    .startName(startName)
                    .endX(endX)
                    .endY(endY)
                    .endName(endName)
                    .build();

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

        Double beforeX = fewSizeItem.getPathList().get(callSize).getDoubleTX();
        Double beforeY = fewSizeItem.getPathList().get(callSize).getDoubleTY();
        String beforeName = fewSizeItem.getPathList().get(callSize).getTName();

        //마지막 버스 내리고
        points.add(fewSizeItem.getPathList().get(callSize).getEnd());

        Double finalX = Double.valueOf(pathReqDto.getEndX());
        Double finalY = Double.valueOf(pathReqDto.getEndY());
        String finalName = "도착지";

        walkPathReqDto = WalkPathReqDto.createBuilder()
                .startX(beforeX)
                .startY(beforeY)
                .startName(beforeName)
                .endX(finalX)
                .endY(finalY)
                .endName(finalName)
                .build();

        walkPathResDto = getWalkPath(walkPathReqDto);
        points.addAll(getAllPoints(walkPathResDto));

        return points;
    }

    public BusItem getShortestDistanceItem(BusStopResDto resDto) {
        DistanceComparator comparator = new DistanceComparator();
        return resDto.getItemList().stream()
                .min(comparator)
                .orElseThrow(() -> new RuntimeException("경로 없음"));
    }

    public BusItem getFewSizeItem(BusStopResDto resDto) {
        SizeComparator comparator = new SizeComparator();
        return resDto.getItemList().stream()
                .min(comparator)
                .orElseThrow(() -> new RuntimeException("경로없음"));
    }

    public BusStopResDto getBusPath(PathReqDto pathReqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        URI url = pathInfo.getBusPathURI(pathReqDto);

        return restTemplate.getForObject(url, BusStopResDto.class);
    }

    public WalkPathResDto getWalkPath(WalkPathReqDto reqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = pathInfo.getTMapHeaders();
        URI url = pathInfo.getWalkPathURI(reqDto);

        HttpEntity httpEntity = new HttpEntity<>(reqDto, headers);

        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, WalkPathResDto.class)
                .getBody();
    }

    public List<Point> getAllPoints(WalkPathResDto resDto) {
        List<Point> points = new ArrayList<>();
        resDto.getFeatures().stream()
                .map(feature -> feature.getGeometry().getPoints(feature.getProperties().getTurnType()))
                .forEach(points::addAll);
        return points;
    }


}
