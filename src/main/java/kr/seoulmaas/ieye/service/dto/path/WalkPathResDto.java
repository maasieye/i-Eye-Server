package kr.seoulmaas.ieye.service.dto.path;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WalkPathResDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    private class Geometry {
        private String type;
        private Long[] coordinates;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    private class Property {
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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    private class Feature {
        private String type;
        private List<Geometry> geometry;
        private List<Property> properties;
    }

    private String type;
    private List<Feature> features;

}
