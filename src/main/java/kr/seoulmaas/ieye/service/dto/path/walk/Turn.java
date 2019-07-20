package kr.seoulmaas.ieye.service.dto.path.walk;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Turn {

    S(11, "직진 하세요."),
    L(12, "좌 회전 하세요."),
    R(13, "우 회전 하세요."),
    U_TURN(14, "유턴 하세요."),
    L_EIGHT(16, "8시 방향 좌회전 하세요."),
    L_TEN(17, "10시 방향 좌회전 하세요."),
    R_TWO(18, "2시 방향 우회전 하세요."),
    R_FOUR(19, "4시 방향 우회전 하세요."),
    YOOK_GYO(125, "육교로 진입합니다."),
    ZI_HA(126, "지하보도로 진입합니다."),
    STAIR(127, "계단으로 진입합니다."),
    SLOPE(128, "경사로 진입합니다."),
    STAIR_SLOPE(129, "계단으로된 경사로 진입합니다."),
    CROSS(211, "횡단보도로 진입합니다. 주의하세요."),
    L_CROSS(212, "좌측 횡단보도로 진입합니다. 주의하세요."),
    R_CROSS(213, "우측 횡단보도로 진입합니다. 주의하세요."),
    EIGHT_CROSS(214, "8시 방향 횡단보도로 진입합니다. 주의하세요."),
    TEN_CROSS(215, "4시 방향 횡단보도로 진입합니다. 주의하세요."),
    TWO_CROSS(216, "2시 방향 횡단보도로 진입합니다. 주의하세요."),
    FOUR_CROSS(217, "4시 방향 횡단보도로 진입합니다. 주의하세요."),
    ELVA(218, "엘리베이터를 타세요."),
    S_TEMP(233, "직진 하세요."),
    DEFAULT(0, "앞으로 가세요.");

    Turn(int no, String message) {
        this.no = no;
        this.message = message;
    }

    private int no;
    private String message;

    public static String findTurnTypeByNo(int no) {
        return Arrays.stream(Turn.values())
                .filter(turn -> turn.isNo(no))
                .findAny()
                .orElse(Turn.DEFAULT)
                .getMessage();
    }

    private boolean isNo(int no) {
        return this.no == no;
    }
}
//- 11: 직진
//- 12: 좌회전
//- 13: 우회전
//- 14: U-turn
//- 16: 8시 방향 좌회전
//- 17: 10시 방향 좌회전
//- 18: 2시 방향 우회전
//- 19: 4시 방향 우회전
//- 125: 육교
//- 126: 지하보도
//- 127: 계단 진입
//- 128: 경사로 진입
//- 129: 계단+경사로 진입
//- 211: 횡단보도
//- 212: 좌측 횡단보도
//- 213: 우측 횡단보도
//- 214: 8시 방향 횡단보도
//- 215: 10시 방향 횡단보도
//- 216: 2시 방향 횡단보도
//- 217: 4시 방향 횡단보도
//- 218: 엘리베이터
//- 233: 직진 임시
