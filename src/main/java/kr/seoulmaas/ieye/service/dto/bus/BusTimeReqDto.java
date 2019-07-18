package kr.seoulmaas.ieye.service.dto.bus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusTimeReqDto {

    private String serviceKey;
    private String stId;
    private String busRouteId;
    private String ord;

}
