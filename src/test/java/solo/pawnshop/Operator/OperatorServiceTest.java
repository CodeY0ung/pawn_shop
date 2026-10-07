package solo.pawnshop.Operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import solo.pawnshop.operator.Operator;
import solo.pawnshop.operator.OperatorRepository;
import solo.pawnshop.operator.OperatorService;
import solo.pawnshop.operator.dto.CreateOperatorRequest;
import solo.pawnshop.operator.enums.Gender;
import solo.pawnshop.operator.enums.Role;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OperatorServiceTest {

    @Mock
    OperatorRepository operatorRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    OperatorService operatorService;

    @Test
    @DisplayName("직원계정 생성시 Role.STAFF, active=true 로 저장된다.")
    void createStaffSuccess(){
        // given
        CreateOperatorRequest request =
                new CreateOperatorRequest(
                        "test@test",
                        "password",
                        "staff1",
                        "010-0000-0000",
                        "000000-0000000",
                        Gender.MALE
                );
//      email 중복 검사
        when(operatorRepository.existsByEmail("test@test"))
                .thenReturn(false);

//      password 암호화 검사
        when(bCryptPasswordEncoder.encode("password"))
                .thenReturn("encodedPassword");

        // when
        operatorService.createStaff(request);

        // then
//      save 된 operator 객체 잡아오기
        ArgumentCaptor<Operator> captor =
                ArgumentCaptor.forClass(Operator.class);

        verify(operatorRepository).save(captor.capture());

        verify(bCryptPasswordEncoder).encode("password");

        Operator savedOperator = captor.getValue();

        assertThat(savedOperator.getEmail())
                .isEqualTo("test@test");
        assertThat(savedOperator.getPassword())
                .isEqualTo("encodedPassword");
        assertThat(savedOperator.getRole())
                .isEqualTo(Role.STAFF);
        assertThat(savedOperator.getName())
                .isEqualTo("staff1");
        assertThat(savedOperator.getPhone())
                .isEqualTo("010-0000-0000");
        assertThat(savedOperator.getResidentNumber())
                .isEqualTo("000000-0000000");
        assertThat(savedOperator.getGender())
                .isEqualTo(Gender.MALE);
        assertThat(savedOperator.isActive())
                .isTrue();


    }
}
