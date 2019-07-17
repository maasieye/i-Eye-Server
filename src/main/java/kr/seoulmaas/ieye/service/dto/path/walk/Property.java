package kr.seoulmaas.ieye.service.dto.path.walk;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Property {
    private String index;
    private String name;
    private String description;
    private String direction;
    private String intersectionName;
    private String nearPoiX;
    private String nearPoiY;
    private String nearPoiName;
    private Integer turnType;
    private String pointType;
    private String facilityType;
    private String facilityName;
    private Long totalDistance;
    private Long totalTime;
    private Long pointIndex;

    public Integer getTurnType() {
        return Optional.ofNullable(this.turnType)
                .orElse(0);
    }
}
