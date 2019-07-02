package kr.seoulmaas.ieye.service.dto.busStop;

import lombok.AccessLevel;
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
}
