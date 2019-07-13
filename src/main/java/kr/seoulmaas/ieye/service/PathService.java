package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.domain.repository.FavoriteRepository;
import kr.seoulmaas.ieye.service.dto.busStop.BusStopResDto;
import kr.seoulmaas.ieye.service.dto.busStop.body.BusItem;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import kr.seoulmaas.ieye.service.dto.path.WalkPathReqDto;
import kr.seoulmaas.ieye.service.dto.path.WalkPathResDto;
import kr.seoulmaas.ieye.service.utill.DistanceComparator;
import kr.seoulmaas.ieye.service.utill.PathInfo;
import kr.seoulmaas.ieye.service.utill.RestTemplateConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Slf4j
public class PathService {

    private final FavoriteRepository favoriteRepository;
    private final RestTemplateConfig restTemplateConfig;
    private final PathInfo pathInfo;

    //목적지 입력 하면 / B -> C 구해짐
    //현재 출발지기준으로/ A -> B 경로구하기
    //내린곳인 C 기준으로/ C->D구하기

    public BusItem getPath(PathReqDto pathReqDto) {

        BusStopResDto busStopResDto = getBusPath(pathReqDto);
        DistanceComparator comparator = new DistanceComparator();
        BusItem shortBusItem = busStopResDto.getItemList().stream()
                .min(comparator)
                .orElseThrow(() -> new RuntimeException("경로 없음"));
        return shortBusItem;
    }

    public BusStopResDto getBusPath(PathReqDto pathReqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        URI url = pathInfo.getBusPathURI(pathReqDto);

        return restTemplate.getForObject(url, BusStopResDto.class);
    }

    public WalkPathResDto getWalkPath(WalkPathReqDto reqDto) {


        return null;
    }

    public void saveFavorite(WalkPathReqDto reqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = pathInfo.getTMapHeaders();
        URI reqUri = pathInfo.getWalkPathURI(reqDto);


    }

    public void getFavorites() {

    }

}
