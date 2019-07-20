package kr.seoulmaas.ieye.service.dto.reservation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationUpdateReqDto {


    private String mobileNumber;
    private String status;

    public ReservationUpdateReqDto(String mobileNumber, String status) {
        this.mobileNumber = mobileNumber;
        this.status = status;
    }
}
