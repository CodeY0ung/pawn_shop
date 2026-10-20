package solo.pawnshop.interestSchedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestScheduleService {
    private final InterestScheduleRepository interestScheduleRepository;
}
