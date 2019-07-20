package kr.seoulmaas.ieye.service.dto.bus;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "itemList")
@Getter
@ToString
public class BusTimeDetailDto {

    @XmlElement(name = "arrmsg1")
    private String firstTimeMsg;

    @XmlElement(name = "traTime1")
    private String firstTime;

    @XmlElement(name = "vehId1")
    private String firstBusId;

    @XmlElement(name = "arrmsg2")
    private String secondTimeMsg;

    @XmlElement(name = "traTime2")
    private String secondTime;

    @XmlElement(name = "vehId2")
    private String secondBusId;

    @XmlElement(name = "stId")
    private String stationId;

    public boolean equalStationId(String stationId) {
        return this.stationId.equals(stationId);
    }

    public BusStationTimeResDto getBusStationTimeResDto() {
        return BusStationTimeResDto.builder()
                .firstMsg(this.firstTimeMsg)
                .firstTime(this.firstTime)
                .firstBusId(this.firstBusId)
                .secondMsg(this.secondTimeMsg)
                .secondTime(this.secondTime)
                .secondBusId(this.secondBusId)
                .build();
    }
}
