package solo.pawnshop.customer.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AddressRequest(
        @NotBlank(message = "우편번호는 필수입니다.")
        String zipCode,

        @NotBlank(message = "도로명 주소는 필수입니다.")
        String roadAddress,

        @NotBlank(message = "상세 주소는 필수입니다.")
        String detailAddress
) {
}
