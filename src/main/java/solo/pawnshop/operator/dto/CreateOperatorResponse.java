package solo.pawnshop.operator.dto;

import solo.pawnshop.operator.enums.Gender;

public record CreateOperatorResponse(

        long id,
        String email,
        String name,
        String phoneNumber,
        String residentNumber,
        Gender gender
) {
}
