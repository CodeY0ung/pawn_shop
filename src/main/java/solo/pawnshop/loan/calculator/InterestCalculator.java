package solo.pawnshop.loan.calculator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class InterestCalculator {

    private static final BigDecimal RATE = new BigDecimal("0.20");

    public long calculate(long principal, LocalDate startDate, LocalDate endDate) {
        if (principal <= 0) {
            throw new IllegalArgumentException("원금은 0보다 커야 합니다.");
        }
        if (startDate == null || endDate == null || !startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("이자 계산 기간이 올바르지 않습니다.");
        }

        BigDecimal total = BigDecimal.ZERO;
        LocalDate cursor = startDate;

        while (cursor.isBefore(endDate)) {
            LocalDate nextYearStart = LocalDate.of(cursor.getYear() + 1, 1, 1);
            LocalDate segmentEnd = endDate.isBefore(nextYearStart) ? endDate : nextYearStart;
            long days = ChronoUnit.DAYS.between(cursor, segmentEnd);
            int daysInYear = cursor.isLeapYear() ? 366 : 365;

            BigDecimal interest = BigDecimal.valueOf(principal)
                    .multiply(RATE)
                    .multiply(BigDecimal.valueOf(days))
                    .divide(BigDecimal.valueOf(daysInYear), 12, RoundingMode.HALF_UP);
            total = total.add(interest);
            cursor = segmentEnd;
        }

        return total.setScale(0, RoundingMode.DOWN).longValueExact();
    }
}