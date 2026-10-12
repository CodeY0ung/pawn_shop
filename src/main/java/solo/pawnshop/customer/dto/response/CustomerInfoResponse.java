package solo.pawnshop.customer.dto.response;

import lombok.Builder;
import solo.pawnshop.customer.Customer;

import java.time.LocalDate;

@Builder
public record CustomerInfoResponse(

        Long id,
        String name,

        LocalDate birthDate,

        String phone,

        AddressResponse address
){
    public static CustomerInfoResponse from(Customer customer){
        return CustomerInfoResponse.builder()
                .id(customer.getCustomerId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .birthDate(customer.getBirthDate())
                .address(AddressResponse.from(customer.getAddress()))
                .build();
    }
}
