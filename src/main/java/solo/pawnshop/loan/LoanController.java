package solo.pawnshop.loan;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solo.pawnshop.loan.dto.CreateLoanRequest;
import solo.pawnshop.loan.dto.LoanInfoResponse;
import solo.pawnshop.loan.dto.UpdateLoanRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/{customerId}/loan")
    public ResponseEntity<Void> createLoan(
            @PathVariable Long customerId,
            @Valid @RequestBody CreateLoanRequest request){
        loanService.createLoan(customerId,request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanInfoResponse> findLoanById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(loanService.findLoanById(id));
    }

    @GetMapping("/{customerId}/loans")
    public ResponseEntity<List<LoanInfoResponse>> findLoansByCustomerId(
            @PathVariable Long customerId
    ){
        return ResponseEntity.ok(loanService.findLoansByCustomerId(customerId));
    }

    @GetMapping("/loans")
    public ResponseEntity<List<LoanInfoResponse>> findLoans(){
        return ResponseEntity.ok(loanService.findAll());
    }

    @PatchMapping("/loans/{id}")
    public ResponseEntity<Void> updateLoan(
            @PathVariable Long id, @Valid @RequestBody UpdateLoanRequest request
            ){
        return ResponseEntity.noContent().build();
    }

}
