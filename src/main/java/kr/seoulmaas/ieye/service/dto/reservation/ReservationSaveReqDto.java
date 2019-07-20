package kr.seoulmaas.ieye.service.dto.reservation;

import kr.seoulmaas.ieye.domain.entity.Reservation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationSaveReqDto {

    private String mobileNumber;

    private String busId;

    private String busRouteId;

    private String startStationId;

    private String endStationId;

    @Builder
    public ReservationSaveReqDto(String mobileNumber, String busId, String busRouteId, String startStationId, String endStationId) {
        this.mobileNumber = mobileNumber;
        this.busId = busId;
        this.busRouteId = busRouteId;
        this.startStationId = startStationId;
        this.endStationId = endStationId;
    }

    public Reservation toEntity() {
        return Reservation.builder()
                .busId(this.busId)
                .busRouteId(this.busRouteId)
                .mobileNumber(this.mobileNumber)
                .startStationId(this.startStationId)
                .endStationId(this.endStationId)
                .build();
    }
}
