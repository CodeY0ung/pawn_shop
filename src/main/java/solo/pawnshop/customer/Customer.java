package solo.pawnshop.customer;

import jakarta.persistence.*;
import lombok.*;
import solo.pawnshop.customer.embeddable.Address;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Embedded
    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Builder
    private Customer(String name, String phone,
                     Address address, LocalDate birthDate) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
    }
}
