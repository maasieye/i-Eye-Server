package kr.seoulmaas.ieye.service.utill;

import kr.seoulmaas.ieye.service.dto.bus.BusTimeReqDto;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import kr.seoulmaas.ieye.service.dto.path.WalkPathReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
public class RestInfo {

//    private static final String BUS_PATH_INFO_URL = "http://ws.bus.go.kr/api/rest/pathinfo/getPathInfoByBus?ServiceKey=%s&startX=%s&startY=%s&endX=%s&endY=%s";

    @Value("${seoul.transport.servicekey}")
    private String serviceKey;

    @Value("${tmap.appKey}")
    private String appKey;

    @Value("${tmoney.appKey}")
    private String tMoneyKey;

    public URI getBusPathURI(PathReqDto reqDto) {
        final String hostUrl = "ws.bus.go.kr";
        final String pathUrl = "/api/rest/pathinfo/getPathInfoByBus";

        String uriString = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(hostUrl)
                .path(pathUrl)
                .queryParam("ServiceKey", serviceKey)
                .queryParam("startX", reqDto.getStartX())
                .queryParam("startY", reqDto.getStartY())
                .queryParam("endX", reqDto.getEndX())
                .queryParam("endY", reqDto.getEndY())
                .build()
                .toString();

//        String url = String.format(BUS_PATH_INFO_URL, serviceKey, reqDto.getStartX(), reqDto.getStartY(), reqDto.getEndX(), reqDto.getEndY());

        return URI.create(uriString);
    }

    public URI getWalkPathURI(WalkPathReqDto reqDto) {
        final String hostUrl = "api2.sktelecom.com";
        final String pathUrl = "/tmap/routes/pedestrian?version=1";

        String uriString = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(hostUrl)
                .path(pathUrl)
                .build()
                .toString();

        return URI.create(uriString);
    }

    public URI getBusTimeURI(BusTimeReqDto reqDto) {
        final String hostUrl = "apigw.tmoney.co.kr:5556";
        final String pathUrl = "/gateway/saArrInfoByRouteGet/v1/arrive/getArrInfoByRoute";

        String uriString = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(hostUrl)
                .path(pathUrl)
                .queryParam("serviceKey", "01234567890")
                .queryParam("stId", reqDto.getStId())
                .queryParam("busRouteId", reqDto.getBusRouteId())
                .queryParam("ord", "1")
                .build()
                .toString();

        return URI.create(uriString);
    }

    public HttpHeaders getBusTimeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("x-Gateway-APIKey", tMoneyKey);
        return headers;
    }

    public HttpHeaders getTMapHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("appKey", appKey);
        headers.add("Accept-Language", "ko");
        return headers;
    }
}