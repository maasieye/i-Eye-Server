package kr.seoulmaas.ieye.domain.repository;

import kr.seoulmaas.ieye.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByMobileNumberAndStatus(String mobileNumber, String status);
}
