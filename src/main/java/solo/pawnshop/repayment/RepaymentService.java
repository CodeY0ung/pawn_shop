package solo.pawnshop.repayment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RepaymentService {
    private final RepaymentRepository repaymentRepository;
}
