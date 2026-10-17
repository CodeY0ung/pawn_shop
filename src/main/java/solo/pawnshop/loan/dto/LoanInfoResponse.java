package solo.pawnshop.loan.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import solo.pawnshop.loan.Loan;
import solo.pawnshop.loan.LoanStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record LoanInfoResponse(
        // 원금
        Long principal,

        // 연 금리 20%
        BigDecimal annualInterestRate,

        // 계약일
        LocalDate contractDate,

        // 최초 만기일
        LocalDate initialMaturityDate,

        // 현재 만기일
        LocalDate currentMaturityDate,

        // 계약 상태
        @Enumerated(EnumType.STRING)
        LoanStatus status

) {
    public static LoanInfoResponse from(Loan loan){
        LoanInfoResponse response = LoanInfoResponse.builder()
                .principal(loan.getPrincipal())
                .annualInterestRate(loan.getAnnualInterestRate())
                .contractDate(loan.getContractDate())
                .initialMaturityDate(loan.getInitialMaturityDate())
                .currentMaturityDate(loan.getCurrentMaturityDate())
                .status(loan.getStatus())
                .build();

        return response;
    }
}
