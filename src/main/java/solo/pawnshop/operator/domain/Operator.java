package solo.pawnshop.operator.domain;

import jakarta.persistence.*;
import lombok.*;
import solo.pawnshop.operator.enums.Gender;
import solo.pawnshop.operator.enums.Role;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_operator_email",
                        columnNames = "email"
                )
        }
)
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operatorId;

    // email
    @Column(nullable = false, unique = true)
    private String email;

    // password
    @Column(nullable = false)
    private String password;

    // 사장 or 직원
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Role role;

    @Column(nullable = false)
    private String name;

    //현재 근무 여부
    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String residentNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    private Operator(String email, String password, Role role, String phone,
                     String residentNumber, Gender gender, String name){
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        active = true;
        this.phone = phone;
        this.residentNumber = residentNumber;
        this.gender = gender;
    }

}
