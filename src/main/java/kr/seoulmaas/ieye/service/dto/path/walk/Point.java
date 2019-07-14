package kr.seoulmaas.ieye.service.dto.path.walk;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Point {

    private Double x;
    private Double y;

    public Point(JsonNode coordinate) {
        this.x = coordinate.get(0).doubleValue();
        this.y = coordinate.get(1).doubleValue();
    }
}
