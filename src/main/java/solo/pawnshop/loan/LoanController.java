package solo.pawnshop.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

}
