package solo.pawnshop.customer.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import solo.pawnshop.customer.embeddable.PayerAccount;

import java.time.LocalDate;

@Builder
public record CreateCustomerRequest(

        @NotBlank
        @Size(min = 1, max = 30)
        String name,

        @NotBlank
        @Pattern(regexp = "^01[0-9]-?[0-9]{3,4}-?[0-9]{4}$")
        String phone,

        @NotNull
        @PastOrPresent
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
