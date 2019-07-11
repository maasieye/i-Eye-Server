package kr.seoulmaas.ieye.service.dto.busStop.body;

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

}
