package solo.pawnshop.loan.schedule;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

/**
 * A안: 최초 계약일을 기준으로 매월 같은 납기 패턴을 유지한다.
 * 예) 10/20 계약 -> 11/20 납기 -> 11/25 완납 시 다음은 12/20.
 *
 * 기준일을 contractDate.plusMonths(n)로 계산하므로,
 * 1/31 계약 -> 2/28 -> 3/31로 월말 날짜가 누적해서 앞당겨지지 않는다.
 */
public final class FixedScheduleDueDatePolicy implements NextDueDatePolicy {

    @Override
    public LocalDate calculate(LocalDate contractDate, LocalDate currentDueDate,
                               LocalDate interestSettledDate) {
        DueDatePolicyValidator.validate(contractDate, currentDueDate, interestSettledDate);

        // 기존 납기일 및 실제 정산일보다 늦은 첫 번째 원래 계약일 기준 납기일을 고른다.
        LocalDate threshold = currentDueDate.isAfter(interestSettledDate)
                ? currentDueDate : interestSettledDate;

        long months = Math.max(1, ChronoUnit.MONTHS.between(
                YearMonth.from(contractDate), YearMonth.from(threshold)
        ));

        LocalDate candidate = contractDate.plusMonths(months);
        while (!candidate.isAfter(threshold)) {
            months++;
            candidate = contractDate.plusMonths(months);
        }
        return candidate;
    }
}
