package solo.pawnshop.loan;

public enum LoanStatus {
    ACTIVE,     // 계약 진행 중
    OVERDUE,    // 연체중
    REPAID,     // 원금 및 이자 완납으로 계약 종료
    ADMIN_DISPOSED,     // 고객 연락 두절 후 관리자 처분으로 종료
    VOLUNTARY_DISPOSED, // 고객 동의에 따른 담보물 처분으로 종료

}
