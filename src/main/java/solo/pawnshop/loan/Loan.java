package solo.pawnshop.loan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solo.pawnshop.customer.Customer;
import solo.pawnshop.operator.Operator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Loan {

    @Id
    private Long loan_id;

    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JoinColumn(name = "operator_id")
    private Operator operator;

    private int principal;

    //정상 약정 금리
    private int interestRatePpm;

    private int defaultRatePpm;

    private PaymentType paymentType;

    private LocalDate contractDate;

    private LocalDate originalMaturityDate;

    private LocalDate currentMaturityDate;

    private Status status;

    private LocalDateTime createdAt;

}
