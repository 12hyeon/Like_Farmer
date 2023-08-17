package likelion.Spring_Like_Farmer.brand.repository;

import likelion.Spring_Like_Farmer.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand save(Brand brand);
    Optional<Brand> findByBrandId(Long brandId);
    boolean existsByBrandId(Long brandId);
}