package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.domain.repository.FavoriteRepository;
import kr.seoulmaas.ieye.service.dto.path.PathReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PathService {

    private final FavoriteRepository favoriteRepository;

    public void getPath(PathReqDto pathReqDto) {

    }

    public void saveFavorite() {

    }

    public void getFavorites() {

    }

}
