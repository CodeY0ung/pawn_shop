package solo.pawnshop.operator.dto;

import lombok.Builder;
import solo.pawnshop.operator.enums.Gender;

@Builder
public record CreateOperatorRequest(
        String email,
        String password,
        String name,
        String phone,
        String residentNumber,
        Gender gender
) {
}
