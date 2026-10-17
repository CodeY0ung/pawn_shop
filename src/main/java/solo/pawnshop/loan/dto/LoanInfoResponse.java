package solo.pawnshop.loan.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import solo.pawnshop.loan.Loan;
import solo.pawnshop.loan.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        Status status

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
