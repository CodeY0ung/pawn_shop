package solo.pawnshop.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.pawnshop.customer.Customer;
import solo.pawnshop.customer.CustomerRepository;
import solo.pawnshop.customer.exception.CustomerNotFoundException;
import solo.pawnshop.customer.exception.LoanNotFoundException;
import solo.pawnshop.interestSchedule.InterestSchedule;
import solo.pawnshop.interestSchedule.InterestScheduleRepository;
import solo.pawnshop.loan.calculator.InterestCalculator;
import solo.pawnshop.loan.dto.CreateLoanRequest;
import solo.pawnshop.loan.dto.LoanInfoResponse;
import solo.pawnshop.loan.dto.UpdateLoanRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoanService {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final InterestCalculator interestCalculator;
    private final InterestScheduleRepository interestScheduleRepository;


    @Transactional
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



        InterestSchedule interestSchedule = InterestSchedule.builder()
                .build();
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

    public List<LoanInfoResponse> findLoansByCustomerId(Long customerId){
        return loanRepository.findAllByCustomer_CustomerId(customerId)
                .stream()
                .map(LoanInfoResponse::from)
                .toList();
    }

    public List<LoanInfoResponse> findAll(){
        return loanRepository.findAll()
                .stream()
                .map(LoanInfoResponse::from)
                .toList();
    }

    @Transactional
    public void updateLoan(Long id, UpdateLoanRequest request){
        Loan loan = loanRepository.findById(id)
                .orElseThrow(LoanNotFoundException::new);

        loan.changeCurrentMaturity(request.changedMaturity());
        loan.ChangeLoanStatus(request.status());
    }
}
