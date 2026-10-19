package solo.pawnshop.loan.dto;

import jakarta.validation.constraints.NotNull;
import solo.pawnshop.loan.LoanStatus;

import java.time.LocalDate;

public record UpdateLoanRequest(
        @NotNull(message = "계약 만기일은 필수입니다.")
        LocalDate changedMaturity,
        @NotNull(message = "계약 상태는 필수입니다.")
        LoanStatus status
) {
}
