package kr.seoulmaas.ieye.service.dto.path.walk;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Geometry {
    private String type;
    private JsonNode coordinates;

    //TODO:라인 하나로 정보 쭉 찍어줄수 있게 Set 으로 Collection 하나 만들기

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
