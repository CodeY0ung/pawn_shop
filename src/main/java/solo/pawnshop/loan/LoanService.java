package solo.pawnshop.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solo.pawnshop.loan.dto.CreateLoanRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

    public void createLoan(CreateLoanRequest request){

    }
}
