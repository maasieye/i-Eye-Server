package kr.seoulmaas.ieye.service.dto.path;

import kr.seoulmaas.ieye.service.dto.path.walk.Point;
import lombok.Getter;

import java.util.List;

@Getter
public class PathDetailResDto {
    private String time;
    private List<Point> points;

    public PathDetailResDto(String time, List<Point> points) {
        this.time = time;
        this.points = points;
    }
}
