package solo.pawnshop.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.pawnshop.customer.Customer;
import solo.pawnshop.customer.CustomerRepository;
import solo.pawnshop.customer.CustomerService;
import solo.pawnshop.customer.exception.CustomerNotFoundException;
import solo.pawnshop.customer.exception.LoanNotFoundException;
import solo.pawnshop.global.InterestCalculator;
import solo.pawnshop.loan.dto.CreateLoanRequest;
import solo.pawnshop.loan.dto.LoanInfoResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoanService {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;


    public void createLoan(Long customerId, CreateLoanRequest request){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        if(customer.isDeleted()){
            throw new IllegalArgumentException("삭제된 고객은 계약을 진행할 수 없습니다.");
        }
        Loan loan = Loan.builder()
                .customer(customer)
                .principal(request.principal())
                .contractDate(request.contractDate())
                .build();
        loanRepository.save(loan);
    }

    public LoanInfoResponse findLoanById(Long id){
        Loan loan = loanRepository.findById(id)
                .orElseThrow(LoanNotFoundException::new);

        LoanInfoResponse response = LoanInfoResponse.builder()
                .contractDate(loan.getContractDate())
                .initialMaturityDate(loan.getInitialMaturityDate())
                .currentMaturityDate(loan.getCurrentMaturityDate())
                .annualInterestRate(loan.getAnnualInterestRate())
                .principal(loan.getPrincipal())
                .status(loan.getStatus())
                .build();

        return response;
    }
}
