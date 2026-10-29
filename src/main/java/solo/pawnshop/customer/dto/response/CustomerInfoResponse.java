package solo.pawnshop.customer.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import solo.pawnshop.customer.Customer;

import java.time.LocalDate;

@Builder
public record CustomerInfoResponse(

        Long id,
        String name,
        LocalDate birthDate,
        String phone,
        String zipCode,
        String roadAddress,
        String detailAddress,
        String payerBank,
        String payerAccount,
        String payerName
){
    private static String maskAccount(String account) {
        if (account == null || account.length() <= 4) {
            return "****";
        }

        return "*".repeat(account.length() - 4)
                + account.substring(account.length() - 4);
    }
    public static CustomerInfoResponse from(Customer customer){
        return CustomerInfoResponse.builder()
                .id(customer.getCustomerId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .birthDate(customer.getBirthDate())
                .zipCode(customer.getAddress().getZipCode())
                .roadAddress(customer.getAddress().getRoadAddress())
                .detailAddress(customer.getAddress().getDetailAddress())
                .payerAccount(maskAccount(customer.getPayerAccount().getPayerAccount()))
                .payerBank(customer.getPayerAccount().getPayerBank())
                .payerName(customer.getPayerAccount().getPayerName())
                .build();
    }
}
