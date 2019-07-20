package kr.seoulmaas.ieye.service.dto.busStop.body;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "itemList")
@Getter
@ToString
public class BusStopItem {

    @XmlElement(name = "arsId")
    private String arsId;

    @XmlElement(name = "stationId")
    private String stationId;

    public boolean equalStationId(String stationId) {
        return this.stationId.equals(stationId);
    }
}
