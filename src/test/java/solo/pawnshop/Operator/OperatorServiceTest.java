package solo.pawnshop.Operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import solo.pawnshop.operator.domain.Operator;
import solo.pawnshop.operator.repository.OperatorRepository;
import solo.pawnshop.operator.service.OperatorService;
import solo.pawnshop.operator.dto.CreateOperatorRequest;
import solo.pawnshop.operator.enums.Gender;
import solo.pawnshop.operator.enums.Role;
import solo.pawnshop.operator.exception.DuplicatedEmailException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OperatorServiceTest {

    @Mock
    OperatorRepository operatorRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    OperatorService operatorService;

    @Test
    @DisplayName("직원계정을 생성할 수 있다. Role.STAFF, active=true")
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
        when(passwordEncoder.encode("password"))
                .thenReturn("encodedPassword");

        // when
        operatorService.createStaff(request);

        // then
//      save 된 operator 객체 잡아오기
        ArgumentCaptor<Operator> captor =
                ArgumentCaptor.forClass(Operator.class);

        verify(operatorRepository).save(captor.capture());

        verify(passwordEncoder).encode("password");

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

    @Test
    @DisplayName("사장 계정을 생성할 수 있다. Role = OWNER, active = true")
    void createOwner(){
        //given
        CreateOperatorRequest request = CreateOperatorRequest.builder()
                .email("owner@test")
                .password("owner_password")
                .name("owner")
                .phone("owner_phone")
                .gender(Gender.MALE)
                .residentNumber("owner_resident_number")
                .build();

        when(operatorRepository.existsByEmail(request.email()))
                .thenReturn(false);
        when(passwordEncoder.encode(request.password()))
                .thenReturn("owner_encoded_password");

        //when
        operatorService.createOwner(request);

        //then
        // 캡쳐 생성
        ArgumentCaptor<Operator> captor =
                ArgumentCaptor.forClass(Operator.class);

        // save가 발동됐는지, 동시에 save에 들어갈 객체 capture
        verify(operatorRepository).save(captor.capture());
        // BCryptEncoder가 동작 했는지
        verify(passwordEncoder).encode("owner_password");

        Operator savedOwner = captor.getValue();

        assertThat(savedOwner.getRole())
                .isEqualTo(Role.OWNER);
        assertThat(savedOwner.getPassword())
                .isEqualTo("owner_encoded_password");
        assertThat(savedOwner.isActive())
                .isTrue();


    }

    @Test
    @DisplayName("중복된 email로 계정을 생성할 수 없다.")
    void cannotCreateOperatorByDuplicatedEmail(){
        //given
        CreateOperatorRequest request = CreateOperatorRequest.builder()
                .email("owner@test")
                .password("owner_password")
                .name("owner")
                .phone("owner_phone")
                .gender(Gender.MALE)
                .residentNumber("owner_resident_number")
                .build();

        when(operatorRepository.existsByEmail(request.email()))
                .thenReturn(true);

        //when && then
        // email 중복 예외 터지는지
        assertThatThrownBy(()->operatorService.createOwner(request))
                .isInstanceOf(DuplicatedEmailException.class);

        // passwordencoder 동작 안했는지
        verify(passwordEncoder, never()).encode(anyString());

        // save 동작 안했는지
        verify(operatorRepository, never()).save(any(Operator.class));
    }
}
