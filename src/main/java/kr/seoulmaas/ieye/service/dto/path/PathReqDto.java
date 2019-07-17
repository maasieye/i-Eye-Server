package kr.seoulmaas.ieye.service.dto.path;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PathReqDto {

    @NotBlank
    @ApiModelProperty(example = "127.08370508148472")
    private String startX;

    @NotBlank
    @ApiModelProperty(example = "37.52946809068537")
    private String startY;

    @NotBlank
    @ApiModelProperty(example = "127.09404734529575")
    private String endX;

    @NotBlank
    @ApiModelProperty(example = "37.50612432766213")
    private String endY;

    @Builder(builderMethodName = "testBuilder")
    public PathReqDto(@NotBlank String startX, @NotBlank String startY, @NotBlank String endX, @NotBlank String endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

}
