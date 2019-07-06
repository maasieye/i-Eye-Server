package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.domain.repository.FavoriteRepository;
import kr.seoulmaas.ieye.service.dto.busStop.BusStopReqDto;
import kr.seoulmaas.ieye.service.dto.busStop.BusStopResDto;
import kr.seoulmaas.ieye.service.utill.PathInfo;
import kr.seoulmaas.ieye.service.utill.RestTemplateConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public BusStopResDto getBusPath(BusStopReqDto busStopReqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        URI url = pathInfo.getBusPathUrl(busStopReqDto);

        BusStopResDto response = restTemplate.getForObject(url, BusStopResDto.class);

        return response;
    }

    public void saveFavorite() {

    }

    public void getFavorites() {

    }

}
