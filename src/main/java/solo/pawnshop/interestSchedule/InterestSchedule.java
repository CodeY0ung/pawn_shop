package solo.pawnshop.interestSchedule;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solo.pawnshop.loan.Loan;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    private InterestSchedule(LocalDate periodStart, LocalDate periodEnd,
                             LocalDate dueDate, Long amountDue){
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.dueDate = dueDate;
        this.amountDue = amountDue;
        this.createdAt = LocalDateTime.now();
    }
}
