package kr.seoulmaas.ieye.service.dto.bus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BusStationTimeResDto {

    private String firstMsg;
    private String firstTime;
    private String firstBusId;

    private String secondMsg;
    private String secondTime;
    private String secondBusId;

}
