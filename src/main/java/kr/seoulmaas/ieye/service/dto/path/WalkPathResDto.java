package kr.seoulmaas.ieye.service.dto.path;

import kr.seoulmaas.ieye.service.dto.path.walk.Feature;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/***
 *  API REF : http://tmapapi.sktelecom.com/main.html#webservice/docs/tmapRoutePedestrianDoc
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WalkPathResDto {

    private String type;
    private List<Feature> features;

}
