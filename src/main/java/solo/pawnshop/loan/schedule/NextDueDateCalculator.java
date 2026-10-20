package solo.pawnshop.loan.schedule;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

/** 정책을 인자로 받아 두 계산 구현 중 하나로 위임한다. */
@Component
public class NextDueDateCalculator {

    // 정책 구현은 상태 없는 순수 Java 객체이므로 각각 별도 Spring Bean일 필요가 없다.
    private final Map<DueDatePolicyType, NextDueDatePolicy> policies = Map.of(
            DueDatePolicyType.FIXED_SCHEDULE, new FixedScheduleDueDatePolicy(),
            DueDatePolicyType.PAYMENT_DATE, new PaymentDateDueDatePolicy()
    );

    public LocalDate calculate(DueDatePolicyType type,
                               LocalDate contractDate,
                               LocalDate currentDueDate,
                               LocalDate interestSettledDate) {
        Objects.requireNonNull(type, "납기일 계산 정책을 지정해야 합니다.");
        return policies.get(type).calculate(contractDate, currentDueDate, interestSettledDate);
    }
}
