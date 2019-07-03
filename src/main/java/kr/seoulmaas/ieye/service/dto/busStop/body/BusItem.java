package kr.seoulmaas.ieye.service.dto.busStop.body;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "itemList")
@Getter
@ToString
public class BusItem {

    @XmlElement(name = "distance")
    private String distance;

    @XmlElement(name = "time")
    private String time;

    @XmlElement(name = "pathList")
    private List<BusPath> pathList;

    public List<BusPath> getPathList() {
        return new ArrayList<>(pathList);
    }

}
