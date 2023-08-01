package likelion.Spring_Like_Farmer.validation;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public CustomException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
