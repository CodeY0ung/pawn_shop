package solo.pawnshop.operator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {

    boolean existsByEmail(String email);

}
