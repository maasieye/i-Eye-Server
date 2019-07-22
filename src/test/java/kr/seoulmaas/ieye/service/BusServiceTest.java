package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.service.dto.bus.BusStationTimeResDto;
import kr.seoulmaas.ieye.service.dto.bus.BusTimeReqDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource("classpath:openapi.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class BusServiceTest {

    @Autowired
    private BusService busService;

    @Test
    public void getBusTimeMsg() {
        BusTimeReqDto busTimeReqDto = new BusTimeReqDto("122000722", "100100572");
        BusStationTimeResDto busStationTimeResDto = busService.getBusStationTime(busTimeReqDto);

        System.out.println(busStationTimeResDto);
    }

}