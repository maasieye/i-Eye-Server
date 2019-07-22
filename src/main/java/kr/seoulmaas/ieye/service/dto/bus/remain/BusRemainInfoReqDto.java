package kr.seoulmaas.ieye.service.dto.bus.remain;

import kr.seoulmaas.ieye.service.dto.busStation.NextBusStationReqDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusRemainInfoReqDto {
    private String mobileNumber;
    private String busId;
    private String busRouteId;
    private String endStationId;

    public NextBusStationReqDto toNextBusStationReqDto() {
        return new NextBusStationReqDto(busRouteId, busId);
    }
}
