package solo.pawnshop.Operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import solo.pawnshop.operator.repository.OperatorRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OperatorIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OperatorRepository operatorRepository;

    @Test
    @DisplayName("직원을 생성할 수 있다")
    void createStaff() throws Exception{
        String json = """
                {
                    "email" : "staff@test",
                    "password" : "password",
                    "name" : "name",
                    "phone" : "phone",
                    "residentNumber" : "residentNumber",
                    "gender" : "FEMALE"
                }
                """;

        mockMvc.perform(
                post("/api/operators/staff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isCreated());

        assertThat(operatorRepository.existsByEmail("staff@test"))
                .isTrue();
    }

    @Test
    @DisplayName("사장을 생성할 수 있다.")
    void createOwner() throws Exception{
        // given
        String json = """
                {
                    "email" : "owner@test",
                    "password" : "password",
                    "name" : "name",
                    "phone" : "phone",
                    "residentNumber" : "residentNumber",
                    "gender" : "MALE"
                }
                """;

        // when
        mockMvc.perform(
                post("/api/operators/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isCreated());

        // then
        assertThat(operatorRepository.existsByEmail("owner@test"))
                .isTrue();
    }

    @Test
    @DisplayName("중복된 email로 직원을 생성하면 409 conflict가 발생한다.")
    void cannotCreateStaffWithDuplicatedEmail() throws Exception{
        // given
        String json = """
                {
                    "email" : "owner@test",
                    "password" : "password",
                    "name" : "name",
                    "phone" : "phone",
                    "residentNumber" : "residentNumber",
                    "gender" : "MALE"
                }
                """;

        mockMvc.perform(
                post("/api/operators/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isCreated());

        // when && then
        mockMvc.perform(
                post("/api/operators/staff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isConflict());
    }
}
