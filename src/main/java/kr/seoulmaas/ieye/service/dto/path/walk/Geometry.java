package kr.seoulmaas.ieye.service.dto.path.walk;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Geometry {
    private String type;
    private Long[] coordinates;
}
