package kr.seoulmaas.ieye.service.dto.bus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@XmlRootElement(name = "ServiceResult")
public class BusTimeResDto {

    @XmlElementWrapper(name = "msgBody")
    @XmlElement(name = "itemList")
    private List<BusTimeDetailDto> busTimeDetailDtos;


}
