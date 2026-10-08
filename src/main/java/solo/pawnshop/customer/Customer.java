package solo.pawnshop.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Customer {
    @Id
    private Long customerId;
    private String name;
    // 주민등록번호
    private String residentNumber;
    private String phone;


}
