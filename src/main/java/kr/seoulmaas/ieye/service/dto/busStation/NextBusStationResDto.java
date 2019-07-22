package kr.seoulmaas.ieye.service.dto.busStation;

import kr.seoulmaas.ieye.service.dto.busStation.body.NextBusItem;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


/***
 * 다음 버스 정류장의 id를 파악하기 위한 객체
 */
@Getter
@ToString
@XmlRootElement(name = "ServiceResult")
public class NextBusStationResDto {

    @XmlElementWrapper(name = "msgBody")
    @XmlElement(name = "itemList")
    private List<NextBusItem> busItems;

    public List<NextBusItem> getBusItems() {
        return new ArrayList<>(this.busItems);
    }
}
