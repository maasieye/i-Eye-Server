package kr.seoulmaas.ieye.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.seoulmaas.ieye.service.dto.busStop.BusStopResDto;
import kr.seoulmaas.ieye.service.dto.busStop.body.BusItem;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import kr.seoulmaas.ieye.service.utill.PathInfo;
import kr.seoulmaas.ieye.service.utill.RestTemplateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("classpath:openapi.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class PathServiceTest {
    @Autowired
    private PathService pathService;

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    @Autowired
    private PathInfo pathInfo;

    @Test
    public void getBusPath() throws Exception {
        //given
        PathReqDto pathReqDto = getTestPathReqDto("127.08370508148472", "37.52946809068537", "127.09404734529575", "37.50612432766213");
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .create();

        //when
        BusStopResDto response = pathService.getBusPath(pathReqDto);

        //then
        assertThat(response.getItemList()).isNotNull();
        System.out.println(gson.toJson(response.getItemList()));
    }

    @Test
    public void getShortBusPath() throws Exception {
        //given
        PathReqDto pathReqDto = getTestPathReqDto("127.08370508148472", "37.52946809068537", "127.09404734529575", "37.50612432766213");
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .create();

        //when
        BusItem busItem = pathService.getPath(pathReqDto);

        //then
        assertThat(busItem).isNotNull();
        System.out.println(gson.toJson(busItem));
    }

    private PathReqDto getTestPathReqDto(String sX, String sY, String eX, String eY) throws Exception {
        Constructor<PathReqDto> constructor = PathReqDto.class.getDeclaredConstructor(String.class, String.class, String.class, String.class);

        constructor.setAccessible(true);
        return PathReqDto.testBuilder()
                .startX(sX)
                .startY(sY)
                .endX(eX)
                .endY(eY)
                .build();
    }
}