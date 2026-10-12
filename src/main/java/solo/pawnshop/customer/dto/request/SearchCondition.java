package solo.pawnshop.customer.dto.request;

import lombok.Builder;

@Builder
public record SearchCondition(
        String name,
        String phone,
        String birth_date
) {
}
