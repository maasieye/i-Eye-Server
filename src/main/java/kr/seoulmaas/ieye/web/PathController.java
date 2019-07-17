package kr.seoulmaas.ieye.web;

import kr.seoulmaas.ieye.service.PathService;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import kr.seoulmaas.ieye.service.dto.path.walk.Point;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/path")
@Slf4j
public class PathController {

    private final PathService pathService;

    @PostMapping
    public ResponseEntity<List<Point>> getPath(@RequestBody @Valid PathReqDto pathReqDto) {
        List<Point> points = pathService.getPath(pathReqDto);
        return ResponseEntity.ok(points);
    }

}
