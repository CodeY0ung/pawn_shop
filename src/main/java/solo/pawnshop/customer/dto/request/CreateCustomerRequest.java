package solo.pawnshop.customer.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateCustomerRequest(

        @NotBlank(message = "회원 이름은 필수입니다.")
        String name,

        @NotBlank(message = "전화번호는 필수입니다.")
        @Pattern(
                regexp = "^01[016789]-?\\d{3,4}-?\\d{4}$",
                message = "올바른 전화번호 형식이 아닙니다."
        )
        String phone,

        @NotNull(message = "주소는 필수입니다.")
        @Valid
        AddressRequest address,

        @NotNull(message = "생년월일은 필수입니다.")
        @Past
        LocalDate birthDate
) {
}
