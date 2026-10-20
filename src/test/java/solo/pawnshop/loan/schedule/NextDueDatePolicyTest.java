package solo.pawnshop.loan.schedule;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NextDueDatePolicyTest {
    private final NextDueDateCalculator calculator = new NextDueDateCalculator();

    @Test
    void originalDueDateIsPreservedForFixedSchedule() {
        assertThat(calculator.calculate(
                DueDatePolicyType.FIXED_SCHEDULE,
                LocalDate.of(2026, 10, 20),
                LocalDate.of(2026, 11, 20),
                LocalDate.of(2026, 11, 25)
        )).isEqualTo(LocalDate.of(2026, 12, 20));
    }

    @Test
    void paymentDateBecomesAnchorForPaymentDatePolicy() {
        assertThat(calculator.calculate(
                DueDatePolicyType.PAYMENT_DATE,
                LocalDate.of(2026, 10, 20),
                LocalDate.of(2026, 11, 20),
                LocalDate.of(2026, 11, 25)
        )).isEqualTo(LocalDate.of(2026, 12, 25));
    }

    @Test
    void paymentOnDueDateProducesSameResult() {
        for (DueDatePolicyType type : DueDatePolicyType.values()) {
            assertThat(calculator.calculate(type,
                    LocalDate.of(2026, 10, 20),
                    LocalDate.of(2026, 11, 20),
                    LocalDate.of(2026, 11, 20)
            )).isEqualTo(LocalDate.of(2026, 12, 20));
        }
    }

    @Test
    void fixedScheduleKeepsOriginalMonthEndAnchor() {
        assertThat(calculator.calculate(
                DueDatePolicyType.FIXED_SCHEDULE,
                LocalDate.of(2026, 1, 31),
                LocalDate.of(2026, 2, 28),
                LocalDate.of(2026, 3, 2)
        )).isEqualTo(LocalDate.of(2026, 3, 31));
    }

    @Test
    void fixedScheduleSkipsOverdueDatesAlreadyPassed() {
        assertThat(calculator.calculate(
                DueDatePolicyType.FIXED_SCHEDULE,
                LocalDate.of(2026, 10, 20),
                LocalDate.of(2026, 11, 20),
                LocalDate.of(2027, 1, 5)
        )).isEqualTo(LocalDate.of(2027, 1, 20));
    }

    @Test
    void earlySettlementThatDoesNotExtendMaturityIsRejectedByPaymentDatePolicy() {
        assertThatThrownBy(() -> calculator.calculate(
                DueDatePolicyType.PAYMENT_DATE,
                LocalDate.of(2026, 10, 20),
                LocalDate.of(2026, 11, 20),
                LocalDate.of(2026, 10, 20)
        )).isInstanceOf(IllegalArgumentException.class);
    }
}
