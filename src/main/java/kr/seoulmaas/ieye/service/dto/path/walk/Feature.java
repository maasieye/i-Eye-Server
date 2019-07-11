package kr.seoulmaas.ieye.service.dto.path.walk;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feature {
    private String type;
    private List<Geometry> geometry;
    private List<Property> properties;
}
