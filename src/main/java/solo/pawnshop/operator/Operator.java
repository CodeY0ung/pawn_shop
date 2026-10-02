package solo.pawnshop.operator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Operator {

    @Id
    private Long operator_id;
    // 로그인 id
    private String id;
    // 사장 or 직원
    private Role role;


}
