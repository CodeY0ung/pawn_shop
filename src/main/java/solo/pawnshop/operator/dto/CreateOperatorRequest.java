package solo.pawnshop.operator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import solo.pawnshop.operator.enums.Gender;

@Builder
public record CreateOperatorRequest(
        @NotBlank
        String email,
        @NotBlank
        String password,
        String name,
        String phone,
        String residentNumber,
        Gender gender
) {
}
