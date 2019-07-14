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

    public void get() {
        System.out.println(this.coordinates.toString());
        if (Type.isPoint(type)) {
            System.out.println("Point================================");
            System.out.println(this.coordinates.get(0).doubleValue());
            System.out.println(this.coordinates.get(1).doubleValue());
            System.out.println("================================Point");
        } else {
            System.out.println("Line================================");
            System.out.println(this.coordinates.get(0).toString());
            System.out.println(this.coordinates.get(1).toString());
            System.out.println("================================Line");
        }
    }
}
