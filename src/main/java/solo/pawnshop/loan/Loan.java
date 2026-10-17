package solo.pawnshop.loan;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import solo.pawnshop.customer.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // 원금
    @Column(nullable = false)
    @Positive
    private Long principal;
    // 연 금리 20%
    @Column(nullable = false)
    private BigDecimal annualInterestRate;
    // 계약일
    @Column(nullable = false)
    private LocalDate contractDate;
    // 최초 만기일
    @Column(nullable = false)
    private LocalDate initialMaturityDate;
    // 현재 만기일
    @Column(nullable = false)
    private LocalDate currentMaturityDate;
    // 계약 상태
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    // 데이터 생성 시간
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    private Loan(Customer customer, Long principal, LocalDate contractDate){
        if(customer == null || principal < 0 || contractDate == null){
            throw new IllegalArgumentException("잘못된 계약 정보입니다.");
        }
        this.customer = customer;
        this.principal = principal;
        this.contractDate = contractDate;
        this.annualInterestRate = new BigDecimal("0.20");
        this.initialMaturityDate = contractDate.plusMonths(1);
        this.currentMaturityDate = this.initialMaturityDate;
        this.status = LoanStatus.ACTIVE;
    }

    public void changeCurrentMaturity(LocalDate currentMaturityDate){
        this.currentMaturityDate = currentMaturityDate;
    }

    public void ChangeLoanStatus(LoanStatus status){
        this.status = status;
    }

}
