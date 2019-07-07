package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.service.dto.busStop.BusStopReqDto;
import kr.seoulmaas.ieye.service.dto.busStop.BusStopResDto;
import kr.seoulmaas.ieye.service.utill.PathInfo;
import kr.seoulmaas.ieye.service.utill.RestTemplateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Constructor;
import java.net.URI;

@TestPropertySource("classpath:openapi.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class PathServiceTest {

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    @Autowired
    private PathInfo pathInfo;

    @Test
    public void getBusPath() throws Exception {
        //given
        Constructor<BusStopReqDto> constructor = BusStopReqDto.class.getDeclaredConstructor(String.class, String.class, String.class, String.class);

        constructor.setAccessible(true);
        BusStopReqDto busStopReqDto = constructor.newInstance("127.08370508148472",
                "37.52946809068537",
                "127.09404734529575",
                "37.50612432766213");


        //when
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        URI url = pathInfo.getBusPathUrl(busStopReqDto);

        //then
        BusStopResDto response = restTemplate.getForObject(url, BusStopResDto.class);
        System.out.println(response.toString());
    }
}