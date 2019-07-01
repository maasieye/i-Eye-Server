package kr.seoulmaas.ieye.web;

import kr.seoulmaas.ieye.service.PathService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/path")
@Slf4j
public class PathController {

    private final PathService pathService;
}
