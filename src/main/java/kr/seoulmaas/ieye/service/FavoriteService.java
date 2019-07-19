package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.domain.entity.Favorite;
import kr.seoulmaas.ieye.domain.repository.FavoriteRepository;
import kr.seoulmaas.ieye.service.dto.favorite.FavoriteRemoveReqDto;
import kr.seoulmaas.ieye.service.dto.favorite.FavoriteReqDto;
import kr.seoulmaas.ieye.service.dto.favorite.FavoriteResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Transactional
    public String saveFavorite(FavoriteReqDto favoriteReqDto) {
        Favorite favorite = favoriteRepository.save(favoriteReqDto.toEntity());
        return favorite.getName();
    }

    public List<FavoriteResDto> getAllFavorite(String mobileNumber) {
        return favoriteRepository.findAllByMobileNumber(mobileNumber).stream()
                .map(FavoriteResDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteFavorite(FavoriteRemoveReqDto req) {
        favoriteRepository.deleteByMobileNumberAndAndName(req.getMobileNumber(), req.getName());
    }
}
