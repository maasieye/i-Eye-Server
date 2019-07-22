package kr.seoulmaas.ieye.service.utill;

import kr.seoulmaas.ieye.service.dto.bus.BusTimeReqDto;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import kr.seoulmaas.ieye.service.dto.path.WalkPathReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
public class RestInfo {

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
        final String hostUrl = "ws.bus.go.kr/api/rest/arrive";
        final String pathUrl = "/getArrInfoByRouteAll";

        String uriString = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(hostUrl)
                .path(pathUrl)
                .queryParam("serviceKey", "ast3JkT7%2Fxg%2BNKFURzalhmBOG175x6IVJ%2BN4VvLpYqJX2xF2QrUFPLMiCLmW54nCo%2F%2FhEg0GzpfjmlTtBMf8vw%3D%3D")
                .queryParam("busRouteId", reqDto.getBusRouteId())
                .build()
                .toString();

        return URI.create(uriString);
    }

    public HttpHeaders getDefaultHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public HttpHeaders getTMapHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("appKey", appKey);
        headers.add("Accept-Language", "ko");
        return headers;
    }

    /**
     * @param busRouteId 노선 아이디
     * @return 다음 정류소 아이디를 구하기 위한 URI
     */
    public URI getNextBusStationIdURI(String busRouteId) {
        final String hostUrl = "ws.bus.go.kr/api/rest/buspos";
        final String pathUrl = "/getBusPosByRtid";

        String uriString = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(hostUrl)
                .path(pathUrl)
                .queryParam("serviceKey", "ast3JkT7%2Fxg%2BNKFURzalhmBOG175x6IVJ%2BN4VvLpYqJX2xF2QrUFPLMiCLmW54nCo%2F%2FhEg0GzpfjmlTtBMf8vw%3D%3D")
                .queryParam("busRouteId", busRouteId)
                .build()
                .toString();

        return URI.create(uriString);
    }

    /**
     * @return 노선의 전체 정류장 아이디, 정류장 이름, 몇번째 인지 알려주는 URI
     */
    public URI getAllBusPathURI(String busRouteId) {
        final String hostUrl = "ws.bus.go.kr/api/rest/busRouteInfo";
        final String pathUrl = "/getStaionByRoute";

        String uriString = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(hostUrl)
                .path(pathUrl)
                .queryParam("serviceKey", "ast3JkT7%2Fxg%2BNKFURzalhmBOG175x6IVJ%2BN4VvLpYqJX2xF2QrUFPLMiCLmW54nCo%2F%2FhEg0GzpfjmlTtBMf8vw%3D%3D")
                .queryParam("busRouteId", busRouteId)
                .build()
                .toString();

        return URI.create(uriString);
    }
}
