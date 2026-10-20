package solo.pawnshop.loan.calculator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class InterestCalculator {

    private static final BigDecimal ANNUAL_RATE =
            new BigDecimal("0.20");

    public long calculate(
            long principal,
            LocalDate startDate,
            LocalDate endDate
    ) {

        if (principal <= 0) {
            throw new IllegalArgumentException("원금은 양수여야 합니다.");
        }

        if (startDate == null || endDate == null
                || !startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("계산 기간이 올바르지 않습니다.");
        }

        BigDecimal totalInterest = BigDecimal.ZERO;
        LocalDate cursor = startDate;

        while (cursor.isBefore(endDate)) {

            // 연도별 계산기간 구분
            LocalDate nextYear =
                    LocalDate.of(cursor.getYear(), 12, 31).plusDays(1);

            LocalDate segmentEnd =
                    endDate.isBefore(nextYear) ? endDate : nextYear;

            long days = ChronoUnit.DAYS.between(cursor, segmentEnd);

            int daysPerYear = cursor.isLeapYear() ? 366 : 365;

            BigDecimal interest = BigDecimal.valueOf(principal)
                    .multiply(ANNUAL_RATE)
                    .multiply(BigDecimal.valueOf(days))
                    .divide(
                            BigDecimal.valueOf(daysPerYear),
                            12,
                            RoundingMode.HALF_UP
                    );

            totalInterest = totalInterest.add(interest);
            cursor = segmentEnd;
        }

        return totalInterest
                .setScale(0, RoundingMode.DOWN)
                .longValueExact();
    }
}