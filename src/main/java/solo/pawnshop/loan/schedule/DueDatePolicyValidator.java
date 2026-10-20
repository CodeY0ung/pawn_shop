package solo.pawnshop.loan.schedule;

import java.time.LocalDate;

final class DueDatePolicyValidator {
    private DueDatePolicyValidator() { }

    static void validate(LocalDate contractDate, LocalDate currentDueDate,
                         LocalDate interestSettledDate) {
        if (contractDate == null || currentDueDate == null || interestSettledDate == null) {
            throw new IllegalArgumentException("날짜는 null일 수 없습니다.");
        }
        if (!currentDueDate.isAfter(contractDate)) {
            throw new IllegalArgumentException("기존 납기일은 계약일 이후여야 합니다.");
        }
        if (interestSettledDate.isBefore(contractDate)) {
            throw new IllegalArgumentException("이자 정산일은 계약일보다 이전일 수 없습니다.");
        }
    }
}
