package solo.pawnshop.customer.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String zipCode;
    private String roadAddress;
    private String detailAddress;

    @Builder
    private Address(String zipCode, String roadAddress, String detailAddress){
        this.zipCode = zipCode;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }
}
