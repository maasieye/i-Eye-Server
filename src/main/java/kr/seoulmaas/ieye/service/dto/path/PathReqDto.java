package kr.seoulmaas.ieye.service.dto.path;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PathReqDto {

    @NotBlank
    private Long startX;

    @NotBlank
    private Long startY;

    @NotBlank
    private Long endX;

    @NotBlank
    private Long endY;
}
