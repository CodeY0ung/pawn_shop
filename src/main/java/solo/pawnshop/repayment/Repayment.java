package solo.pawnshop.repayment;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import solo.pawnshop.loan.Loan;

import java.time.LocalDate;

@Entity
@Getter
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    // 청구 금액 (원금 + 이자)
    private Long requestedAmount;
    // 납부 금액 (미상환시 연체, 이자만 상환시 자동 연장, 원금+이자 상환시 계약 종료)
    private Long repaidAmount;
    // 상환 날짜
    private LocalDate repaidDate;
    // 상환 상태
    private boolean isRepayment;
    // 상환 확인 날짜
    private LocalDate createdAt;

    private Repayment(Long repaidAmount){


    }
}
