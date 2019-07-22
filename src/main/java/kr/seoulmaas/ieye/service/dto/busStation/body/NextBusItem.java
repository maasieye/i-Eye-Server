package kr.seoulmaas.ieye.service.dto.busStation.body;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "itemList")
@Getter
@ToString
public class NextBusItem {

    @XmlElement(name = "lastStnId")
    private String lastStationId;

    @XmlElement(name = "nextStId")
    private String nextStationId;

    @XmlElement(name = "vehId")
    private String busId;

    public boolean equalBusId(String busId) {
        return this.busId.equals(busId);
    }

    public boolean isNextLast() {
        return nextStationId.equals(lastStationId);
    }
}
