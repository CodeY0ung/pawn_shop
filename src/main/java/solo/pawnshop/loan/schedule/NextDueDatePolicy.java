package solo.pawnshop.loan.schedule;

import java.time.LocalDate;

/**
 * 계약 연장 시 다음 만기일 및 이자 납기일을 결정한다.
 *
 * @param contractDate 최초 계약일 (원래 날짜 패턴을 복원할 때 사용)
 * @param currentDueDate 연장 전 현재 만기/납기일
 * @param interestSettledDate 기존 발생 이자를 전액 정산한 날 (일부 납부일이 아님)
 */
public interface NextDueDatePolicy {
    LocalDate calculate(
            LocalDate contractDate,
            LocalDate currentDueDate,
            LocalDate interestSettledDate
    );
}
