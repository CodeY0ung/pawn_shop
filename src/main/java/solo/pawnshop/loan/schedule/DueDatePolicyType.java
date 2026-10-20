package solo.pawnshop.loan.schedule;

/** 계약 연장 후 다음 납기일 계산 정책. */
public enum DueDatePolicyType {
    /** 최초 계약일을 기준으로 형성된 월별 납기일을 유지한다. */
    FIXED_SCHEDULE,
    /** 연체 이자를 완납한 날짜로부터 새로운 1개월을 시작한다. */
    PAYMENT_DATE
}
