package kr.seoulmaas.ieye.service.dto.busStop;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusStopGeometryReqDto {

    private String x;
    private String y;
    private String stationId;

    public BusStopGeometryReqDto(Double x, Double y, String stationId) {
        this.x = x.toString();
        this.y = y.toString();
        this.stationId = stationId;
    }

    public BusStopGeometryReqDto(String x, String y, String stationId) {
        this.x = x;
        this.y = y;
        this.stationId = stationId;
    }
}
