package kr.seoulmaas.ieye.service.dto.favorite;

import kr.seoulmaas.ieye.domain.entity.Favorite;
import lombok.Getter;

@Getter
public class FavoriteReqDto {

    private String mobileNumber;

    private String name;

    private Double x;

    private Double y;

    private Long stationId;

    public Favorite toEntity() {
        return Favorite.builder()
                .mobileNumber(mobileNumber)
                .name(name)
                .x(x)
                .y(y)
                .build();
    }
}
