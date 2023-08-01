package likelion.Spring_Like_Farmer.config;

import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.Getter;

@Getter
public class ResponseType {
    private final Integer status;
    private final String code;
    private final String message;

    public ResponseType(ExceptionCode exceptionCode) {
        this.status = exceptionCode.getStatus().getValue();
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
