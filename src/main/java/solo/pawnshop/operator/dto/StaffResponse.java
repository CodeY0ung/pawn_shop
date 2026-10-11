package solo.pawnshop.operator.dto;

import solo.pawnshop.operator.domain.Operator;
import solo.pawnshop.operator.enums.Gender;
import solo.pawnshop.operator.enums.Role;

public record StaffResponse(
        Long operatorId,
        String email,
        String name,
        String phone,
        Role role,
        boolean active
) {

    public static StaffResponse from(Operator operator) {
        return new StaffResponse(
                operator.getOperatorId(),
                operator.getEmail(),
                operator.getName(),
                operator.getPhone(),
                operator.getRole(),
                operator.isActive()
        );
    }
}
