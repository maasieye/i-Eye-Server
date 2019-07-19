package kr.seoulmaas.ieye.domain.repository;

import kr.seoulmaas.ieye.domain.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findAllByMobileNumber(String mobileNumber);

    void deleteByMobileNumberAndAndName(String mobileNumber, String name);
}
