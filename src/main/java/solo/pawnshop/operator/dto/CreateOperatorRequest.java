package solo.pawnshop.operator.dto;

import solo.pawnshop.operator.enums.Gender;

public record CreateOperatorRequest(
        String email,
        String password,
        String name,
        String phoneNum,
        String resident_number,
        Gender gender
) {
}
