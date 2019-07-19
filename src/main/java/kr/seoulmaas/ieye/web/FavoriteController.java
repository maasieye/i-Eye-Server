package kr.seoulmaas.ieye.web;

import kr.seoulmaas.ieye.service.FavoriteService;
import kr.seoulmaas.ieye.service.dto.favorite.FavoriteRemoveReqDto;
import kr.seoulmaas.ieye.service.dto.favorite.FavoriteReqDto;
import kr.seoulmaas.ieye.service.dto.favorite.FavoriteResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<String> saveFavorite(@RequestBody FavoriteReqDto favoriteReqDto) {
        String stationName = favoriteService.saveFavorite(favoriteReqDto);
        return ResponseEntity.ok(stationName);
    }

    @GetMapping
    public ResponseEntity<List<FavoriteResDto>> getAllFavorite(@RequestParam String mobileNumber) {
        return ResponseEntity.ok(favoriteService.getAllFavorite(mobileNumber));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeFavorite(@RequestBody FavoriteRemoveReqDto favoriteRemoveReqDto) {
        favoriteService.deleteFavorite(favoriteRemoveReqDto);
        return ResponseEntity.ok().build();
    }
}
