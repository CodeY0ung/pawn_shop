package solo.pawnshop.loan;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solo.pawnshop.loan.dto.CreateLoanRequest;
import solo.pawnshop.loan.dto.LoanInfoResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loan/")
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Void> createLoan(
            @PathVariable Long customerId, @Valid @RequestBody CreateLoanRequest request){
        loanService.createLoan(customerId,request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanInfoResponse> findLoanById(
            @PathVariable Long id
    ){
        LoanInfoResponse response
                = loanService.findLoanById(id);
        return ResponseEntity.ok(response);
    }



}
