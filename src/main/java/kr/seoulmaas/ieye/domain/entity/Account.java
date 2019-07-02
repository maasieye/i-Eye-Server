package kr.seoulmaas.ieye.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String serialNumber;

    @OneToMany
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_account_favorite"))
    private List<Favorite> favorites;

}
