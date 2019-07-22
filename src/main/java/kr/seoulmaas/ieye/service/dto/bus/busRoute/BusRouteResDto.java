package kr.seoulmaas.ieye.service.dto.bus.busRoute;

import kr.seoulmaas.ieye.service.dto.bus.busRoute.body.BusRouteItem;
import kr.seoulmaas.ieye.service.dto.bus.remain.BusRemainInfoResDto;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * 찾고자 하는 노선의 전체 정보를 가져와서 이름이랑 몇정거장남았는지 알려주기 위한 dto
 */
@Getter
@ToString
@XmlRootElement(name = "ServiceResult")
public class BusRouteResDto {

    @XmlElementWrapper(name = "msgBody")
    @XmlElement(name = "itemList")
    private List<BusRouteItem> busRouteItems;

    public List<BusRouteItem> getBusRouteItems() {
        return new ArrayList<>(this.busRouteItems);
    }

    public Integer getRemainBusStationAmount(String nowStationId, String endStationId) {
        Integer nowSeq = getNowStationSeq(nowStationId);
        Integer endSeq = this.busRouteItems.stream()
                .filter(item -> item.equalBusStationId(endStationId))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getSeq();
        return endSeq - nowSeq + 1;
    }

    public Integer getNowStationSeq(String nowStationId) {
        return this.busRouteItems.stream()
                .filter(item -> item.equalBusStationId(nowStationId))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getSeq();
    }

    public String getNowStationName(String nowStationId) {
        return this.busRouteItems.stream()
                .filter(item -> item.equalBusStationId(nowStationId))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getBusStationName();
    }

    public String getNextStationName(Integer nowSeq) {
        return this.busRouteItems.stream()
                .filter(item -> item.equalBusStationSeq(nowSeq + 1))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getSeq().toString();
    }

    public BusRemainInfoResDto getBusRemainInfo(String nowStationId, String endStationId) {
        String nowStationName = getNowStationName(nowStationId);
        String nextStationName = getNextStationName(getNowStationSeq(nowStationId));
        String remainAmount = getRemainBusStationAmount(nowStationId, endStationId).toString();
        return BusRemainInfoResDto.builder()
                .nowStationName(nowStationName)
                .nextStationName(nextStationName)
                .remainStationAmount(remainAmount)
                .build();
    }
}
