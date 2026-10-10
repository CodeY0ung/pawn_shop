package solo.pawnshop.Operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import solo.pawnshop.operator.controller.OperatorController;
import solo.pawnshop.operator.dto.CreateOperatorRequest;
import solo.pawnshop.operator.service.OperatorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OperatorController.class)
public class OperatorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    OperatorService operatorService;

    @Test
    @DisplayName("직원을 생성할 수 있다.")
    void createStaff() throws Exception{

        String json = """
                {
                    "email" : "test@email",
                    "password" : "password",
                    "name" : "name",
                    "phone" : "phone",
                    "residentNumber" : "residentNumber",
                    "gender" : "FEMALE"
                }
                """;

        // api 호출
        mockMvc.perform(
                post("/api/operators/staff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isCreated());

        // 검증
        verify(operatorService).createStaff(any(CreateOperatorRequest.class));
    }

    @Test
    @DisplayName("사장을 생성할 수 있다.")
    void createOwner() throws Exception{
        String json = """
                {
                    "email" : "owner@email",
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

        verify(operatorService).createOwner(any(CreateOperatorRequest.class));

    }

}
