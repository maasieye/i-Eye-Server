package kr.seoulmaas.ieye.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract class BaseGpsEntity {

    @Column(name = "Longitude")
    @NotBlank
    private Long x;

    @Column(name = "Latitude")
    @NotBlank
    private Long y;
}
