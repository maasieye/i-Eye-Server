package kr.seoulmaas.ieye.service.dto.path.walk;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Long turnType;
    private String pointType;
    private String facilityType;
    private String facilityName;
    private Long totalDistance;
    private Long totalTime;
    private Long pointIndex;
}
