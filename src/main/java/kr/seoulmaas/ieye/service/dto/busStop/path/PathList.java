package kr.seoulmaas.ieye.service.dto.busStop.path;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PathList {

    private String fid;

    private String fname;

    private String fx;

    private String fy;

    private String routeId;

    private String routeNm;

    private String tid;

    private String tname;

    private String tx;

    private String ty;

}
