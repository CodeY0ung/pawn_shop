package solo.pawnshop.operator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.pawnshop.operator.domain.Operator;
import solo.pawnshop.operator.dto.CreateOperatorRequest;
import solo.pawnshop.operator.enums.Role;
import solo.pawnshop.operator.exception.DuplicatedEmailException;
import solo.pawnshop.operator.repository.OperatorRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperatorService {

    private final OperatorRepository operatorRepository;
    private final PasswordEncoder passwordEncoder;


    // 직원 생성
    @Transactional
    public void createStaff(CreateOperatorRequest request){
        createOperator(request,Role.STAFF);
    }

    // 사장 생성
    @Transactional
    public void createOwner(CreateOperatorRequest request){
        createOperator(request, Role.OWNER);
    }

    private void createOperator(CreateOperatorRequest request, Role role){
        // email 중복 검사
        validateDuplicate(request.email());

        // password encoding
        String encodedPassword = passwordEncoder.encode(request.password());

        Operator owner = Operator.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .role(role)
                .phone(request.phone())
                .residentNumber(request.residentNumber())
                .gender(request.gender())
                .build();
        //save
        operatorRepository.save(owner);
    }

    private void validateDuplicate(String email){
        if(operatorRepository.existsByEmail(email)){
            throw new DuplicatedEmailException();
        }
    }

    // 직원 전체 조회
    public void findStaffAll(){
        operatorRepository.findAll();
    }

    // id로 조회
    public void findStaffById(Long id){
        operatorRepository.findById(id);
    }
}
