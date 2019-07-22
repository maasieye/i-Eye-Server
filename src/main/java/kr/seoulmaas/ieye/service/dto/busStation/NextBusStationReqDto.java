package kr.seoulmaas.ieye.service.dto.busStation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NextBusStationReqDto {

    private String busRouteId;
    private String busId;

    public NextBusStationReqDto(String busRouteId, String busId) {
        this.busRouteId = busRouteId;
        this.busId = busId;
    }
}
