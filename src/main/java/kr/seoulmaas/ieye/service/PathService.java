package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.domain.repository.FavoriteRepository;
import kr.seoulmaas.ieye.service.dto.busStop.BusStopReqDto;
import kr.seoulmaas.ieye.service.dto.busStop.BusStopResDto;
import kr.seoulmaas.ieye.service.utill.BusPathInfo;
import kr.seoulmaas.ieye.service.utill.RestTemplateConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PathService {

    private final FavoriteRepository favoriteRepository;
    private final RestTemplateConfig restTemplateConfig;
    private final BusPathInfo busPathInfo;

    public void getBusPath(BusStopReqDto busStopReqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        String url = busPathInfo.getBusPathUrl(busStopReqDto);

        log.info("url : "+url);

        String o = restTemplate.getForObject(url, String.class);

        log.info(o);
    }

    public void saveFavorite() {

    }

    public void getFavorites() {

    }

}
