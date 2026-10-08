package solo.pawnshop.operator;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.pawnshop.global.exception.UnSupportedException;
import solo.pawnshop.operator.dto.CreateOperatorRequest;
import solo.pawnshop.operator.enums.Role;
import solo.pawnshop.operator.exception.DuplicatedEmailException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperatorService {

    private final OperatorRepository operatorRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void createStaff(CreateOperatorRequest request){
        // email 중복 검사
        validateDuplicate(request.email());

        // password encoding
        String encodedPassword = passwordEncoder.encode(request.password());

        Operator operator = Operator.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .phone(request.phoneNum())
                .role(Role.STAFF)
                .residentNumber(request.resident_number())
                .gender(request.gender())
                .build();

        // save
        operatorRepository.save(operator);

    }

    public void validateDuplicate(String email){
        if(operatorRepository.existsByEmail(email)){
            throw new DuplicatedEmailException("중복된 email");
        }
    }
}
