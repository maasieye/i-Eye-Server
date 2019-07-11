package kr.seoulmaas.ieye.web;

import kr.seoulmaas.ieye.service.PathService;
import kr.seoulmaas.ieye.service.dto.busStop.BusStopReqDto;
import kr.seoulmaas.ieye.service.dto.busStop.BusStopResDto;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/path")
@Slf4j
public class PathController {

    private final PathService pathService;

    @GetMapping
    public ResponseEntity<Void> getPath(@RequestBody @Valid PathReqDto pathReqDto) {

        return null;
    }

    @GetMapping("/bus")
    public ResponseEntity<BusStopResDto> getBusPath(@RequestBody BusStopReqDto busStopReqDto) {
        return ResponseEntity.ok(pathService.getBusPath(busStopReqDto));
    }
}
