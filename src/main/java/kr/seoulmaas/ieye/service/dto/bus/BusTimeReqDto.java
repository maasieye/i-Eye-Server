package kr.seoulmaas.ieye.service.dto.bus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusTimeReqDto {

    private String stationId;
    private String busRouteId;

    public BusTimeReqDto(String stationId, String busRouteId) {
        this.stationId = stationId;
        this.busRouteId = busRouteId;
    }
}
