package solo.pawnshop.customer.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String roadAddress;
    private String detailAddress;

    @Builder
    private Address(String zipCode, String roadAddress, String detailAddress){
        this.zipCode = zipCode;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }
}
