package kr.seoulmaas.ieye.service.dto.path.walk;

import lombok.Getter;

@Getter
public enum Type {
    POINT("Point", 0),
    LINE("LineString", 1),
    BUS_STOP("BusStop", 2);

    private String typeMessage;
    private int typeValue;

    Type(String typeMessage, int typeValue) {
        this.typeMessage = typeMessage;
        this.typeValue = typeValue;
    }

    public static boolean isPoint(String type) {
        return type.equals(POINT.typeMessage);
    }
}
