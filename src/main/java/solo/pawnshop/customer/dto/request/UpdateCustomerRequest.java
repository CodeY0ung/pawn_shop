package solo.pawnshop.customer.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UpdateCustomerRequest(
        @NotBlank(message = "회원 이름은 필수입니다.")
        String name,

        @NotBlank(message = "전화번호는 필수입니다.")
        @Pattern(
                regexp = "^01[016789]-?\\d{3,4}-?\\d{4}$",
                message = "올바른 전화번호 형식이 아닙니다."
        )
        String phone,

        @NotNull(message = "생년월일은 필수입니다.")
        @Past
        LocalDate birthDate,

        @NotBlank
        String zipCode,

        @NotBlank
        String roadAddress,

        String detailAddress,

        @NotBlank
        String payerBank,

        @NotBlank
        String payerAccount,

        @NotBlank
        @Size(min = 1, max = 40)
        String payerName
) {
}
