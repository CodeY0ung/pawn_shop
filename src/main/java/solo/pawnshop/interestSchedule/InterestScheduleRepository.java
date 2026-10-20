package solo.pawnshop.interestSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestScheduleRepository extends JpaRepository<InterestSchedule, Long> {

}
