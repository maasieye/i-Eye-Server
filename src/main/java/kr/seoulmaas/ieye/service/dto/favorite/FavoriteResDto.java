package kr.seoulmaas.ieye.service.dto.favorite;

import kr.seoulmaas.ieye.domain.entity.Favorite;
import lombok.Getter;

@Getter
public class FavoriteResDto {

    private String name;

    private Long x;

    private Long y;

    public FavoriteResDto(Favorite favorite) {
        this.name = favorite.getName();
        this.x = favorite.getX();
        this.y = favorite.getY();
    }
}
