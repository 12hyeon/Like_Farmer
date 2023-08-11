package likelion.Spring_Like_Farmer.validation;

import static likelion.Spring_Like_Farmer.validation.HttpStatus.*;

public enum ExceptionCode {
    /**
     * 회원가입 및 로그인
     */
    SIGNUP_CREATED_OK(CREATED, "A000", "회원가입 성공"),
    SIGNUP_DUPLICATED_ID(DUPLICATED_VALUE, "A002", "ID 중복"),
    SIGNUP_DUPLICATED_NICKNAME(DUPLICATED_VALUE, "A003", "NICKNAME 중복"),

    LOGIN_OK(SUCCESS, "B000", "로그인 성공"),
    LOGIN_NOT_FOUND_ID(NOT_FOUND_VALUE, "B001", "잘못된 ID 로그인 실패"),
    LOGIN_NOT_FOUND_PW(NOT_FOUND_VALUE, "B002", "잘못된 PW 로그인 실패"),
    LOGOUT_OK(SUCCESS, "B003", "로그아웃 성공"),
    LOGOUT_INVALID(NOT_FOUND_VALUE, "B004", "로그아웃 실패"),

    /**
     * 회원정보
     */
    USER_GET_OK(SUCCESS, "C000", "회원정보 있음"),
    USER_NOT_FOUND(NOT_FOUND_VALUE, "C001", "회원정보 없음"),
    USER_UPDATE_OK(SUCCESS, "C002", "회원정보 수정 성공"),
    USER_SEARCH_OK(SUCCESS, "C003", "회원 검색 성공"),
    USER_SAVE_ID_OK(SUCCESS, "C004", "NICKNAME 수정 성공"),

    
    /**
     *  토큰
     */
    INVALID_JWT_SIGNATURE(UNAUTHORIZED,"G000", "타당하지 않은 JWT 서명 오류"),
    INVALID_JWT_TOKEN(UNAUTHORIZED,"G001", "잘못된 JWT 토큰 오류"),
    EXPIRED_JWT_TOKEN(UNAUTHORIZED,"G002", "만료된 JWT 토큰 오류"),
    UNSUPPORTED_JWT_TOKEN(UNAUTHORIZED,"G003", "지원되지 않는 JWT 토큰 오류"),
    TOKEN_SUCCESS(SUCCESS, "G005", "토큰 확인 성공"),

    /**
     * item정보
     */
    ITEM_SAVE_OK(SUCCESS, "I000", "ITEM 저장 성공"),
    ITEM_NOT_FOUND(NOT_FOUND_VALUE, "I001", "ITEM 정보 없음"),
    ITEM_UPDATE_OK(SUCCESS, "I002", "ITEM 수정 성공"),
    ITEM_GET_OK(SUCCESS, "I003", "ITEM 조회 성공"),
    ITEM_DELETE_OK(SUCCESS, "I004", "ITEM 삭제 성공"),

    RECORD_SAVE_OK(SUCCESS, "J000", "RECORD 저장 성공"),
    RECORD_NOT_FOUND(NOT_FOUND_VALUE, "J001", "RECORD 정보 없음"),
    RECORD_UPDATE_OK(SUCCESS, "J002", "RECORD 수정 성공"),
    RECORD_GET_OK(SUCCESS, "J003", "RECORD 조회 성공"),
    RECORD_DELETE_OK(SUCCESS, "J004", "RECORD 삭제 성공"),
    /**
     * post정보
     */
    POST_SAVE_OK(SUCCESS, "I000", "POST 저장 성공"),
    POST_NOT_FOUND(NOT_FOUND_VALUE, "I001", "POST 정보 없음"),
    POST_UPDATE_OK(SUCCESS, "I002", "POST 수정 성공"),
    POST_DELETE_OK(SUCCESS, "I004", "POST 삭제 성공"),
    POST_GET_OK(SUCCESS, "I003", "POST 불러오기 성공"),
    /**
     * comment정보
     */
    COMMENT_SAVE_OK(SUCCESS, "I000", "COMMENT 저장 성공"),
    COMMENT_NOT_FOUND(NOT_FOUND_VALUE, "I001", "COMMENT 정보 없음"),
    COMMENT_UPDATE_OK(SUCCESS, "I002", "COMMENT 수정 성공"),
    COMMENT_DELETE_OK(SUCCESS, "I004", "COMMENT 삭제 성공"),
    /**
     * 잘못된 ExceptionCode
     */
    INVALID_FORM(INVALID_ACCESS, "Z000", "형식에 어긋난 이름"),
    EMPTY(null, "", ""),
    WRONG_PASSWORD(INVALID_ACCESS, "Z000", "잘못된 비밀번호");
    private final HttpStatus status;
    private final String code;
    private final String message;

    ExceptionCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
