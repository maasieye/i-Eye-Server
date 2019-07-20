package kr.seoulmaas.ieye.service.dto.busStop;

import kr.seoulmaas.ieye.service.dto.busStop.body.BusStopItem;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@XmlRootElement(name = "ServiceResult")
@ToString
public class BusStopArsIdResDto {

    @XmlElementWrapper(name = "msgBody")
    @XmlElement(name = "itemList")
    private List<BusStopItem> itemList;

    public String getAsrIdByStationId(String stationId) {
        return this.itemList.stream()
                .filter(item -> item.equalStationId(stationId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(""))
                .getArsId();
    }
}
