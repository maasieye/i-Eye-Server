package kr.seoulmaas.ieye.service.dto.favorite;

import kr.seoulmaas.ieye.domain.entity.Favorite;
import lombok.Getter;

@Getter
public class FavoriteResDto {

    private String stationName;

    private Double x;

    private Double y;

    public FavoriteResDto(Favorite favorite) {
        this.stationName = favorite.getName();
        this.x = favorite.getX();
        this.y = favorite.getY();
    }
}
