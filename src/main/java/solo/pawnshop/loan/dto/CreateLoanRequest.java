package solo.pawnshop.loan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import solo.pawnshop.loan.Status;

import java.time.LocalDate;

@Builder
public record CreateLoanRequest(
        // 원금
        @NotBlank(message = "원금은 필수입니다.")
        Long principal,
        // 계약일
        @NotBlank
        @NotNull
        LocalDate contractDate,
        // 최초 만기일
        @NotBlank
        @NotNull
        LocalDate initialMaturityDate,
        // 계약 상태
        @NotBlank
        @NotNull
        Status status
) {

}
