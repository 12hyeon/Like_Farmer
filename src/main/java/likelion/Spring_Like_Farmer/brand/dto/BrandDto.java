package likelion.Spring_Like_Farmer.brand.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.Spring_Like_Farmer.config.ResponseType;
import likelion.Spring_Like_Farmer.brand.domain.Brand;
import likelion.Spring_Like_Farmer.user.domain.User;
import likelion.Spring_Like_Farmer.user.dto.UserDto;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class BrandDto {

    @Getter
    public static class SaveBrand {
        private String name;
        private String slogan;
        private String crops;
        private String introduce;
    }

    @Getter
    public static class BrandResponse extends ResponseType {

        @JsonInclude(NON_NULL)
        private List<Brand> brands;

        public BrandResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
        }

        public BrandResponse(ExceptionCode exceptionCode, List<Brand> brands) {
            super(exceptionCode);
            this.brands = brands;
        }
    }

    @Getter
    public static class FarmerResponse extends ResponseType {

        @JsonInclude(NON_NULL)
        private List<UserDto.UserList> users;

        public FarmerResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
        }

        public FarmerResponse(ExceptionCode exceptionCode, List<UserDto.UserList> users) {
            super(exceptionCode);
            this.users = users;
        }
    }

    @Getter
    public static class BrandListResponse extends ResponseType{

        List<Brand> brandList;
        int total;

        public BrandListResponse(ExceptionCode exceptionCode, List<Brand> brandList) {
            super(exceptionCode);
            this.total = brandList.size();
            this.brandList = brandList;
        }
    }
}
