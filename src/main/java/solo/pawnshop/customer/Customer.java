package solo.pawnshop.customer;

import jakarta.persistence.*;
import lombok.*;
import solo.pawnshop.customer.embeddable.Address;
import solo.pawnshop.customer.embeddable.PayerAccount;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Embedded
    private Address address;

    @Embedded
    private PayerAccount payerAccount;

    @Column(nullable = false)
    private boolean deleted;

    private LocalDateTime deletedAt;

    @Builder
    private Customer(String name, String phone,
                     Address address, PayerAccount payerAccount, LocalDate birthDate) {
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
        this.payerAccount = payerAccount;
        this.deleted = false;
    }

    public void changeName(String name){
        this.name = name;
    }
    public void changePhone(String phone){
        this.phone = phone;
    }
    public void changeBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }
    public void changeAddress(Address address){
        this.address = address;
    }

    public void changePayerAccount(PayerAccount payerAccount){
        this.payerAccount = payerAccount;
    }
    public void delete(){
        this.deleted = true;
        deletedAt = LocalDateTime.now();
    }
    public void restore(){
        this.deleted = false;
        deletedAt = null;
    }

}
