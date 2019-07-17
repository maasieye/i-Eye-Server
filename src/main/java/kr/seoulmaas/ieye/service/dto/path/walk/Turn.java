package kr.seoulmaas.ieye.service.dto.path.walk;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Turn {

    S(11, "직진"),
    L(12, "좌회전"),
    R(13, "우회전"),
    U_TURN(14, "유턴"),
    L_EIGHT(16, "8시 방향 좌회전"),
    L_TEN(17, "10시 방향 좌회전"),
    R_TWO(18, "2시 방향 우회전"),
    R_FOUR(19, "4시 방향 우회전"),
    YOOK_GYO(125, "육교"),
    ZI_HA(126, "지하보도"),
    STAIR(127, "계단 진입"),
    SLOPE(128, "경사로 진입"),
    STAIR_SLOPE(129, "계단으로된 경사로 진입"),
    CROSS(211, "횡단보도"),
    L_CROSS(212, "좌측 횡단보도"),
    R_CROSS(213, "우측 횡단보도"),
    EIGHT_CROSS(214, "8시 방향 횡단보도"),
    TEN_CROSS(215, "4시 방향 횡단보도"),
    TWO_CROSS(216, "2시 방향 횡단보도"),
    FOUR_CROSS(217, "4시 방향 횡단보도"),
    ELVA(218, "엘리베이터"),
    S_TEMP(233, "직진"),
    DEFAULT(0, "일반");

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
