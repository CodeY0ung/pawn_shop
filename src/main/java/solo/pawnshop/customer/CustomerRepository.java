package solo.pawnshop.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    public boolean existByNameAndPhoneAndBirthDate(String name, String phone, LocalDate birthDate);
}
