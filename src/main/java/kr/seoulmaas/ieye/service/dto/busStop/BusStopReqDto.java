package kr.seoulmaas.ieye.service.dto.busStop;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusStopReqDto {

    @NotBlank
    private String startX;

    @NotBlank
    private String startY;

    @NotBlank
    private String endX;

    @NotBlank
    private String endY;

    @Builder
    private BusStopReqDto(@NotBlank String startX, @NotBlank String startY, @NotBlank String endX, @NotBlank String endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

}
