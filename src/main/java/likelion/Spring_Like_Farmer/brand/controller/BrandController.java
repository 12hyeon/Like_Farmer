package likelion.Spring_Like_Farmer.brand.controller;

import likelion.Spring_Like_Farmer.brand.dto.BrandDto;
import likelion.Spring_Like_Farmer.brand.service.BrandService;
import likelion.Spring_Like_Farmer.security.CurrentUser;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    // 브랜드 생성
    @PostMapping("/brand")
    public ResponseEntity<Object> saveBrand(@CurrentUser UserPrincipal userPrincipal,
                                            @RequestBody BrandDto.SaveBrand saveBrand) {
        return new ResponseEntity<>(brandService.saveBrand(userPrincipal, saveBrand), HttpStatus.OK);
    }

    // 농부 등록
    @PostMapping("/brand/farmer/{brandId}")
    public ResponseEntity<Object> saveFarmer(@CurrentUser UserPrincipal userPrincipal,
                                             @PathVariable Long brandId) {
        return new ResponseEntity<>(brandService.saveFarmer(userPrincipal, brandId), HttpStatus.OK);
    }

    // 속한 농부 리스트
    @GetMapping("/auth/brand/farmer/{brandId}")
    public ResponseEntity<Object> findFarmer(@PathVariable Long brandId) {
        return new ResponseEntity<>(brandService.findFarmers(brandId), HttpStatus.OK);
    }

    // 전체 brand 리스트
    @GetMapping("/auth/brand")
    public ResponseEntity<Object> findBrands() {
        return new ResponseEntity<>(brandService.findBrands(), HttpStatus.OK);
    }

}
