package kr.seoulmaas.ieye.service.dto.busStation;

import kr.seoulmaas.ieye.service.dto.busStation.body.BusItem;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "ServiceResult")
@Getter
@ToString
public class BusStationResDto {

    @XmlElementWrapper(name = "msgBody")
    @XmlElement(name = "itemList")
    private List<BusItem> itemList;

    public List<BusItem> getItemList() {
        return new ArrayList<>(itemList);
    }

}
