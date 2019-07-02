package kr.seoulmaas.ieye.service.dto.busStop;

import kr.seoulmaas.ieye.service.dto.busStop.path.PathList;
import lombok.Getter;

import java.util.List;

@Getter
public class BusStopResDto {

    private String distance;

    private String time;

    private List<PathList> pathList;

}
