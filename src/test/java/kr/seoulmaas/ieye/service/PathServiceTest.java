package kr.seoulmaas.ieye.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.seoulmaas.ieye.service.dto.busStation.BusStationResDto;
import kr.seoulmaas.ieye.service.dto.busStation.body.BusItem;
import kr.seoulmaas.ieye.service.dto.path.PathDetailResDto;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import kr.seoulmaas.ieye.service.dto.path.WalkPathReqDto;
import kr.seoulmaas.ieye.service.dto.path.WalkPathResDto;
import kr.seoulmaas.ieye.service.dto.path.walk.Feature;
import kr.seoulmaas.ieye.service.dto.path.walk.Geometry;
import kr.seoulmaas.ieye.service.dto.path.walk.Point;
import kr.seoulmaas.ieye.service.utill.RestInfo;
import kr.seoulmaas.ieye.service.utill.RestTemplateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    private RestInfo restInfo;

    private Gson gson = new GsonBuilder().setPrettyPrinting()
            .create();

    @Test
    public void getPath() throws Exception {
        //given
        PathReqDto pathReqDto = getTestPathReqDto("127.08370508148472", "37.52946809068537", "127.09404734529575", "37.50612432766213");

        //when
        PathDetailResDto resDto = pathService.getPath(pathReqDto);

        //then
        System.out.println(gson.toJson(resDto));
    }

    @Test
    public void getBusPath() throws Exception {
        //given
        PathReqDto pathReqDto = getTestPathReqDto("127.08370508148472", "37.52946809068537", "127.09404734529575", "37.50612432766213");

        //when
        BusStationResDto response = pathService.getBusPath(pathReqDto);

        //then
        assertThat(response.getItemList()).isNotNull();

        System.out.println(gson.toJson(response.getItemList()));
    }

    @Test
    public void getShortBusPath() throws Exception {
        //given
        PathReqDto pathReqDto = getTestPathReqDto("127.08370508148472", "37.52946809068537", "127.09404734529575", "37.50612432766213");

        //when
        BusStationResDto busStationResDto = pathService.getBusPath(pathReqDto);
        BusItem busItem = pathService.getShortestDistanceItem(busStationResDto);

        //then
        assertThat(busItem).isNotNull();
        System.out.println(gson.toJson(busItem));
    }

    @Test
    public void getFewSizeItem() throws Exception {
        //given
        PathReqDto pathReqDto = getTestPathReqDto("127.08370508148472", "37.52946809068537", "127.09404734529575", "37.50612432766213");

        //when
        BusStationResDto busStationResDto = pathService.getBusPath(pathReqDto);
        BusItem busItem = pathService.getFewSizeItem(busStationResDto);

        //then
        assertThat(busItem).isNotNull();
        System.out.println(gson.toJson(busItem));
    }

    @Test
    public void getWalkPathResDto() {
        //given
        WalkPathReqDto testWalkPathReqDto = getTestWalkPathReqDto("127.08370508148472", "37.52946809068537", "출발지", "127.08468121119274", "37.531925459337224", "도착지");

        //when
        WalkPathResDto walkPathResDto = pathService.getWalkPath(testWalkPathReqDto);

        //then
        assertThat(walkPathResDto).isNotNull();
        System.out.println(gson.toJson(walkPathResDto));
        walkPathResDto.getFeatures().stream()
                .map(Feature::getGeometry)
                .forEach(Geometry::getCoordinateInfo);
    }

    @Test
    public void getPoints() {
        //given
        WalkPathReqDto testWalkPathReqDto = getTestWalkPathReqDto("127.08370508148472", "37.52946809068537", "출발지", "127.08468121119274", "37.531925459337224", "도착지");

        //when
        WalkPathResDto walkPathResDto = pathService.getWalkPath(testWalkPathReqDto);
        List<Point> points = pathService.getAllPoints(walkPathResDto);

        //then
        assertThat(points).isNotNull();
        System.out.println(gson.toJson(points));
    }

    private PathReqDto getTestPathReqDto(String sX, String sY, String eX, String eY) throws Exception {
        return PathReqDto.testBuilder()
                .startX(sX)
                .startY(sY)
                .endX(eX)
                .endY(eY)
                .build();
    }

    private WalkPathReqDto getTestWalkPathReqDto(String sX, String sY, String sName, String eX, String eY, String eName) {
        return WalkPathReqDto.createBuilder()
                .startX(Double.parseDouble(sX))
                .startY(Double.parseDouble(sY))
                .startName(sName)
                .endX(Double.parseDouble(eX))
                .endY(Double.parseDouble(eY))
                .endName(eName)
                .build();
    }
}