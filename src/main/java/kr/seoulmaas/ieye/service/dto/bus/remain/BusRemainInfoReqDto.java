package kr.seoulmaas.ieye.service.dto.bus.remain;

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
}
