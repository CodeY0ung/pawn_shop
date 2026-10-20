package solo.pawnshop.interestSchedule;

import jakarta.persistence.*;
import lombok.Getter;
import solo.pawnshop.loan.Loan;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class InterestSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "loan_id")
    private Loan loan;

    private LocalDate periodStart;  // 이자 계산 시작일
    private LocalDate periodEnd;    // 이자 계산 종료일
    private LocalDate dueDate;      // 납부 예정일
    private Long amountDue;         // 해당 기간의 청구 이자
    private LocalDateTime createdAt;    // 일정 생성 시각
}
