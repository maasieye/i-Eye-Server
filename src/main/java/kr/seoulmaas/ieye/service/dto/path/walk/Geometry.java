package kr.seoulmaas.ieye.service.dto.path.walk;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Geometry {
    private String type;
    private JsonNode coordinates;

    public List<Point> getPoints(int turnType) {
        List<Point> points = new ArrayList<>();
        if (Type.isPoint(this.type)) {
            Point point = new Point(this.coordinates, this.type,turnType);
            points.add(point);
        } else {
            for (JsonNode coordinate : this.coordinates) {
                Point point = new Point(coordinate);
                points.add(point);
            }
        }
        return new ArrayList<>(points);
    }

    public void getCoordinateInfo() {
        if (Type.isPoint(type)) {
            Point point = new Point(this.coordinates);
            System.out.println("Point================================");
            System.out.println(point.toString());
            System.out.println("================================Point\n");
        } else {
            System.out.println("Line================================ size : " + this.coordinates.size());
            for (JsonNode coordinate : this.coordinates) {
                Point point = new Point(coordinate);
                System.out.println(point);
            }
            System.out.println("================================Line\n");
        }
    }

}
