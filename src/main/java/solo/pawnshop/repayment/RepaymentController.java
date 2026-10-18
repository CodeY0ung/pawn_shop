package solo.pawnshop.repayment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RepaymentController {
    private final RepaymentService service;
}
