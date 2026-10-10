package solo.pawnshop.operator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solo.pawnshop.operator.domain.Operator;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {

    boolean existsByEmail(String email);

}
