package solo.pawnshop.customer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import solo.pawnshop.customer.embeddable.Address;

@Builder
public record AddressResponse(
        String zipCode,
        String roadAddres,
        String detailAddress
) {
    public static AddressResponse from(Address address){
        return AddressResponse.builder()
                .zipCode(address.getZipCode())
                .roadAddres(address.getRoadAddress())
                .detailAddress(address.getDetailAddress())
                .build();
    }
}
