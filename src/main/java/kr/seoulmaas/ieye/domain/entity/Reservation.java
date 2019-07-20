package kr.seoulmaas.ieye.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobileNumber;

    private String busId;

    private String busRouteId;

    private String startStationId;

    private String endStationId;

    private String status;

    @Builder
    public Reservation(String mobileNumber, String busId, String busRouteId, String startStationId, String endStationId) {
        this.mobileNumber = mobileNumber;
        this.busId = busId;
        this.busRouteId = busRouteId;
        this.startStationId = startStationId;
        this.endStationId = endStationId;
        this.status = "미승차";
    }

    public void takeOn() {
        this.status = "승차";
    }

    public void takeOff() {
        this.status = "하치";
    }
}
