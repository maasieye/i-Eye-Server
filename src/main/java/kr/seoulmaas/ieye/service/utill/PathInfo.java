package kr.seoulmaas.ieye.service.utill;

import kr.seoulmaas.ieye.service.dto.busStop.BusStopReqDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class PathInfo {

    private static final String BUS_PATH_INFO_URL = "http://ws.bus.go.kr/api/rest/pathinfo/getPathInfoByBus?ServiceKey=%s&startX=%s&startY=%s&endX=%s&endY=%s";

    @Value("${seoul.transport.servicekey}")
    private String serviceKey;

    public URI getBusPathUrl(BusStopReqDto reqDto) {
        final String hostUrl = "ws.bus.go.kr";
        final String pathUrl = "/api/rest/pathinfo/getPathInfoByBus";

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(hostUrl)
                .path(pathUrl)
                .queryParam("ServiceKey", serviceKey)
                .queryParam("startX", reqDto.getStartX())
                .queryParam("startY", reqDto.getStartY())
                .queryParam("endX", reqDto.getEndX())
                .queryParam("endY", reqDto.getEndY())
                .build()
                .toUri();
        String url = String.format(BUS_PATH_INFO_URL, serviceKey, reqDto.getStartX(), reqDto.getStartY(), reqDto.getEndX(), reqDto.getEndY());
        return URI.create(url);
//        return uri;
    }
}
