package solo.pawnshop.loan.schedule;

import java.time.LocalDate;

/**
 * B안: 기존 발생 이자를 전액 정산한 날부터 1개월 후를 새 납기일로 한다.
 * 예) 10/20 계약 -> 11/20 납기 -> 11/25 완납 시 다음은 12/25.
 */
public final class PaymentDateDueDatePolicy implements NextDueDatePolicy {

    @Override
    public LocalDate calculate(LocalDate contractDate, LocalDate currentDueDate,
                               LocalDate interestSettledDate) {
        DueDatePolicyValidator.validate(contractDate, currentDueDate, interestSettledDate);

        LocalDate nextDueDate = interestSettledDate.plusMonths(1);
        if (!nextDueDate.isAfter(currentDueDate)) {
            throw new IllegalArgumentException(
                    "다음 납기일이 기존 납기일 이후여야 합니다. 조기 연장의 경우 별도 규칙이 필요합니다."
            );
        }
        return nextDueDate;
    }
}
