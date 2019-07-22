package kr.seoulmaas.ieye.service.dto.bus.busRoute.body;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "itemList")
@Getter
@ToString
public class BusRouteItem {

    @XmlElement(name = "seq")
    private Integer seq;

    @XmlElement(name = "station")
    private String busStationId;

    @XmlElement(name = "stationNm")
    private String busStationName;

    public boolean equalBusStationId(String busStationId) {
        return this.busStationId.equals(busStationId);
    }

    public boolean equalBusStationSeq(Integer seq) {
        return this.seq.equals(seq);
    }
}
