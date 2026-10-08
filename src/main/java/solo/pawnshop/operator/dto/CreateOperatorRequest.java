package solo.pawnshop.operator.dto;

import lombok.Builder;
import solo.pawnshop.operator.enums.Gender;

@Builder
public record CreateOperatorRequest(
        String email,
        String password,
        String name,
        String phoneNum,
        String resident_number,
        Gender gender
) {
}
