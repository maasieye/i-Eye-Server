package kr.seoulmaas.ieye.service.dto.bus.remain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusRemainInfoResDto {

    private String nowStationName = "신논현역";
    private String nextStationName = "삼성역";
    private String remainStationAmount = "1";
}
