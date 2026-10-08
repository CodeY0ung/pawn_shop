package solo.pawnshop.Operator;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import solo.pawnshop.operator.Operator;
import solo.pawnshop.operator.OperatorRepository;
import solo.pawnshop.operator.enums.Gender;
import solo.pawnshop.operator.enums.Role;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class OperatorRepositoryTest {


    @Autowired
    private OperatorRepository operatorRepository;

    @Test
    @DisplayName("email로 operator 존재여부를 확인할 수 있다.")
    void existsByEmailTest(){

        // given
        Operator operator = Operator.builder()
                .email("exist@email")
                .password("password")
                .name("name")
                .phone("phone")
                .role(Role.STAFF)
                .residentNumber("resident")
                .gender(Gender.MALE)
                .build();

        operatorRepository.saveAndFlush(operator);

        // when
        boolean isExists = operatorRepository.existsByEmail("exist@email");

        // then
        assertThat(isExists).isTrue();

    }

    @Test
    @DisplayName("동일한 email의 operator는 중복 저장 할 수 없다.")
    void cannotSaveSameEmail(){
        //given
        Operator operator = Operator.builder()
                .email("exist@email")
                .password("password")
                .name("name")
                .phone("phone")
                .role(Role.STAFF)
                .residentNumber("resident")
                .gender(Gender.MALE)
                .build();

        Operator operator2 = Operator.builder()
                .email("exist@email")
                .password("password")
                .name("name")
                .phone("phone")
                .role(Role.STAFF)
                .residentNumber("resident")
                .gender(Gender.MALE)
                .build();

        operatorRepository.saveAndFlush(operator);

        //when && then
        assertThatThrownBy(()->operatorRepository.save(operator2))
                .isInstanceOf(DataIntegrityViolationException.class);

    }

}
