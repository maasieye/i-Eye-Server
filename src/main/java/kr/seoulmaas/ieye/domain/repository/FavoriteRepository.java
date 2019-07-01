package kr.seoulmaas.ieye.domain.repository;

import kr.seoulmaas.ieye.domain.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
