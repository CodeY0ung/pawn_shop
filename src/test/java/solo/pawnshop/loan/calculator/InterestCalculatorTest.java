package solo.pawnshop.loan.calculator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InterestCalculatorTest {
    private final InterestCalculator calculator = new InterestCalculator();

    @Test
    void extraFiveDaysAreIncludedInOverdueSettlement() {
        long originallyDue = calculator.calculate(1_000_000,
                LocalDate.of(2026, 10, 20), LocalDate.of(2026, 11, 20));
        long totalDueAtSettlement = calculator.calculate(1_000_000,
                LocalDate.of(2026, 10, 20), LocalDate.of(2026, 11, 25));

        assertThat(originallyDue).isEqualTo(16_986);
        assertThat(totalDueAtSettlement).isEqualTo(19_726);
        assertThat(totalDueAtSettlement - originallyDue).isEqualTo(2_740);
    }

    @Test
    void nextInterestDiffersWithDueDatePolicy() {
        LocalDate settlementDate = LocalDate.of(2026, 11, 25);
        assertThat(calculator.calculate(1_000_000, settlementDate,
                LocalDate.of(2026, 12, 20))).isEqualTo(13_698);
        assertThat(calculator.calculate(1_000_000, settlementDate,
                LocalDate.of(2026, 12, 25))).isEqualTo(16_438);
    }

    @Test
    void invalidPrincipalThrows() {
        assertThatThrownBy(() -> calculator.calculate(0,
                LocalDate.of(2026, 10, 20), LocalDate.of(2026, 11, 20)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
