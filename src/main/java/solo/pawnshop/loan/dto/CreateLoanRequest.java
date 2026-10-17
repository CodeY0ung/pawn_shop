package solo.pawnshop.loan.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateLoanRequest(
        // 원금
        @NotNull(message = "원금은 필수입니다.")
        @Positive(message = "원금은 0 이상이어야합니다.")
        Long principal,
        // 계약일
        @NotNull
        LocalDate contractDate

) {

}
