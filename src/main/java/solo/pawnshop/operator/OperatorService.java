package solo.pawnshop.operator;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.pawnshop.global.exception.UnSupportedException;
import solo.pawnshop.operator.dto.CreateOperatorRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperatorService {

    private final OperatorRepository operatorRepository;
    private final PasswordEncoder passwordEncoder;


    public Long createStaff(CreateOperatorRequest request){



        throw new UnSupportedException();
    }
}
