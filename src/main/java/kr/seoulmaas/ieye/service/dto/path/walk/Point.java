package kr.seoulmaas.ieye.service.dto.path.walk;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {

    private Double[] xy;

    public Point(Double[] xy) {
        this.xy = xy;
    }
}
