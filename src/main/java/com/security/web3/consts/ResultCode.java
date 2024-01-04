package com.security.web3.consts;

/**
 * [[[ code ]]]
 * 0    : SUCCESS
 * 1    : 회원가입이 완료되었습니다.
 * 2    : 회원 탈퇴가 완료되었습니다.
 *
 * -1   : 시스템에 문제가 발생했습니다. 다시 시도해주세요.
 * -2   : 존재하지 않는 데이터입니다.
 * -3   : 비밀번호가 일치하지 않습니다.
 * -4   : 아이디 혹은 비밀번호가 일치하지 않습니다. 입력한 내용을 다시 확인해주세요.
 * -5   : 시스템 문제로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.
 * -999 : 예상치 못한 오류가 발생했습니다.
 */
public enum ResultCode {
    SUCCESS(0),
    JOIN_COMPLETE(1),
    WITHDRAW_COMPLETE(2),

    DATA_INTEGRITY_VIOLATION(-1),
    DATA_NOT_FOUND(-2),
    MISMATCH_PASSWORD(-3),
    SECURITY_BAD_CREDENTIALS(-4),
    SECURITY_INTERNAL_AUTHENTICATION_SERVICE(-5),
    UNKNOWN_ERROR(-999);

    private final int value;

    ResultCode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
