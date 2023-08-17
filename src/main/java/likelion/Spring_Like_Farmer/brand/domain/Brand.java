package likelion.Spring_Like_Farmer.brand.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likelion.Spring_Like_Farmer.config.BaseEntity;
import likelion.Spring_Like_Farmer.brand.dto.BrandDto;
import likelion.Spring_Like_Farmer.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Brand")
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;
    
    private List<Long> users;

    private String name;
    private String slogan;
    private String crops;
    private String introduce;


    // builder
    @Builder
    public Brand(BrandDto.SaveBrand saveBrand, User user) {
        this.users = new ArrayList<>();
        users.add((Long) user.getUserId());
        this.name = saveBrand.getName();
        this.slogan = saveBrand.getSlogan();
        this.crops = saveBrand.getCrops();
        this.introduce = saveBrand.getIntroduce();
    }

    public void addFarmer(User user) {
        if (! users.contains(user)) {
            users.add((Long) user.getUserId());
        }
    }
}
