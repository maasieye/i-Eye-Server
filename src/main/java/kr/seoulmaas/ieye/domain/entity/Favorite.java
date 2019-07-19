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
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobileNumber;

    private String name;

    private Double x;

    private Double y;

    @Builder
    public Favorite(String mobileNumber, String name, Double x, Double y) {
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
