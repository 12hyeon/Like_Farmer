package likelion.Spring_Like_Farmer.brand.service;

import likelion.Spring_Like_Farmer.brand.domain.Brand;
import likelion.Spring_Like_Farmer.brand.dto.BrandDto;
import likelion.Spring_Like_Farmer.brand.repository.BrandRepository;
import likelion.Spring_Like_Farmer.record.dto.RecordDto;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.user.domain.User;
import likelion.Spring_Like_Farmer.user.dto.UserDto;
import likelion.Spring_Like_Farmer.user.repository.UserRepository;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    public Object findFarmers(Long brandId) {
        Optional<Brand> brand = brandRepository.findByBrandId(brandId);
        if (brand.isEmpty()) {
            return new BrandDto.BrandResponse(ExceptionCode.BRAND_NOT_FOUND);
        }
        List<Long> farmers = brand.get().getUsers();
        List<UserDto.UserList> users = new ArrayList<>();
        for(Long userId: farmers) {
            users.add(new UserDto.UserList(userRepository.findByUserId(userId).get()));
        }

        return new BrandDto.FarmerResponse(ExceptionCode.BRAND_GET_OK, users);
    }

    public Object findBrands() {
        List<Brand> brands = brandRepository.findAll();
        return new BrandDto.BrandResponse(ExceptionCode.BRAND_GET_OK, brands);
    }

    public Object saveFarmer(UserPrincipal userPrincipal, Long brandId) {

        User user = userRepository.findByUserId(userPrincipal.getUserId()).get();

        Optional<Brand> brand = brandRepository.findByBrandId(brandId);
        if (brand.isEmpty()) {
            return new BrandDto.BrandResponse(ExceptionCode.BRAND_NOT_FOUND);
        }
        Brand b = brand.get();
        b.addFarmer(user);
        System.out.println("b.getUsers() = " + b.getUsers());
        brandRepository.save(b);

        return new BrandDto.BrandResponse(ExceptionCode.USER_UPDATE_OK);
    }

    public Object saveBrand(UserPrincipal brandPrincipal, BrandDto.SaveBrand saveBrand) {

        User user = userRepository.findByUserId(brandPrincipal.getUserId()).get();

        Brand brand = Brand.builder()
                .saveBrand(saveBrand)
                .user(user)
                .build();
        brandRepository.save(brand);

        return new BrandDto.BrandResponse(ExceptionCode.BRAND_SAVE_OK);
    }
}
