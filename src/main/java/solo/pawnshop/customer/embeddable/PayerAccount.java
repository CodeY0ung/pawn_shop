package solo.pawnshop.customer.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PayerAccount {
    @Column(nullable = false)
    private String payerBank;
    @Column(nullable = false)
    private String payerAccount;
    @Column(nullable = false)
    private String payerName;

    @Builder
    private PayerAccount(String payerBank, String payerAccount, String payerName){
        this.payerBank = payerBank;
        this.payerAccount = payerAccount;
        this.payerName = payerName;
    }
}
