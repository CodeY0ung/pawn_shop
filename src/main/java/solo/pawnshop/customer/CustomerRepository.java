package solo.pawnshop.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    boolean existByNameAndPhoneAndBirthDate(String name, String phone, LocalDate birthDate);
    boolean existsByNameAndPhoneAndBirthDateAndCustomerIdNot(
            String name,
            String phone,
            LocalDate birthDate,
            Long id
            );

    @Query("""
    select c
    from Customer c
    where (:name is null
           or c.name like concat('%', :name, '%'))
      and (:phone is null
           or c.phone = :phone)
      and (:birthDate is null
           or c.birthDate = :birthDate)
    """)
    List<Customer> searchCustomers(
            @Param("name") String name,
            @Param("phone") String phone,
            @Param("birthDate") LocalDate birthDate
    );

}
