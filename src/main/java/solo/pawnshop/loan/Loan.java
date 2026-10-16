package solo.pawnshop.loan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import solo.pawnshop.customer.Customer;
import solo.pawnshop.operator.domain.Operator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // 원금
    private Long principal;
    // 연 금리
    private int annualInterestRate;
    // 계약일
    private LocalDate contractDate;
    // 최초 만기일
    private LocalDate initialMaturityDate;
    // 현재 만기일
    private LocalDate currentMaturityDate;
    // 계약 상태
    private Status status;
    // 데이터 생성 시간
    private LocalDateTime createdAt;



}
