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

    private Type type = Type.LINE;
    private Double x;
    private Double y;
    private String turnType;

    private String fid;
    private String tid;
    private String busNumber;
    private String routeId;

    public Point(JsonNode coordinate) {
        this.x = coordinate.get(0).doubleValue();
        this.y = coordinate.get(1).doubleValue();
    }

    public Point(JsonNode coordinate, String type, int turnType) {
        if (Type.isPoint(type)) {
            this.type = Type.POINT;
            this.turnType = Turn.findTurnTypeByNo(turnType);
        }
        this.x = coordinate.get(0).doubleValue();
        this.y = coordinate.get(1).doubleValue();
    }


    public Point(String x, String y, Type busStop, String busNumber, String routeId, String fid, String tid) {
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.type = busStop;
        if (busNumber != null) {
            this.busNumber = busNumber;
        }
        if (routeId != null) {
            this.routeId = routeId;
        }
        if (fid != null) {
            this.fid = fid;
        }
        if (tid != null) {
            this.tid = tid;
        }
    }

}
