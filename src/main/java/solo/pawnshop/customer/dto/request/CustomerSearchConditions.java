package solo.pawnshop.customer.dto.request;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
public record CustomerSearchConditions(
        String name,
        String phone,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate birth_date
) {
}
