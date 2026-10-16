package solo.pawnshop.loan;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solo.pawnshop.loan.dto.CreateLoanRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers/")
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/{customerId}/loan")
    public ResponseEntity<Void> createLoan(
            @PathVariable Long customerId, @Valid @RequestBody CreateLoanRequest request){
        loanService.createLoan(customerId,request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
