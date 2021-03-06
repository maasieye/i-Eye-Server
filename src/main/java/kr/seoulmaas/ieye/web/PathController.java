package kr.seoulmaas.ieye.web;

import kr.seoulmaas.ieye.service.PathService;
import kr.seoulmaas.ieye.service.dto.path.PathDetailResDto;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    public ResponseEntity<PathDetailResDto> getPath(@RequestBody @Valid PathReqDto pathReqDto) {
        PathDetailResDto resDto = pathService.getPath(pathReqDto);
        return ResponseEntity.ok(resDto);
    }

}
