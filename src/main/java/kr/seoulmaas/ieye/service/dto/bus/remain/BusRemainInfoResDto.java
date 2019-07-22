package kr.seoulmaas.ieye.service.dto.bus.remain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class BusRemainInfoResDto {

    private String nowStationName;
    private String nextStationName;
    private String remainStationAmount;

    @Builder
    public BusRemainInfoResDto(String nowStationName, String nextStationName, String remainStationAmount) {
        this.nowStationName = nowStationName;
        this.nextStationName = nextStationName;
        this.remainStationAmount = remainStationAmount;
    }
}
