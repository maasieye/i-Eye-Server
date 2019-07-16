package kr.seoulmaas.ieye.service.dto.busStop.body;

import kr.seoulmaas.ieye.service.dto.path.walk.Point;
import kr.seoulmaas.ieye.service.dto.path.walk.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@XmlRootElement(name = "BusPath")
@Getter
@ToString
public class BusPath {

    @XmlElement(name = "fid")
    private String fId;

    @XmlElement(name = "fname")
    private String fName;

    @XmlElement(name = "fx")
    private String fx;

    @XmlElement(name = "fy")
    private String fy;

    @XmlElement(name = "routeId")
    private String routeId;

    @XmlElement(name = "routeNm")
    private String routeNm;

    @XmlElement(name = "tid")
    private String tId;

    @XmlElement(name = "tname")
    private String tName;

    @XmlElement(name = "tx")
    private String tx;

    @XmlElement(name = "ty")
    private String ty;

    public Point getStart() {
        return new Point(fx, fy, Type.BUS_STOP);
    }

    public Point getEnd() {
        return new Point(tx, ty, Type.BUS_STOP);
    }

    public Double getDoubleFX() {
        return Double.valueOf(this.fx);
    }

    public Double getDoubleFY() {
        return Double.valueOf(this.fy);
    }

    public Double getDoubleTX() {
        return Double.valueOf(this.tx);
    }

    public Double getDoubleTY() {
        return Double.valueOf(this.ty);
    }

}
