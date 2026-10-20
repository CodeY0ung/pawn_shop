package solo.pawnshop.interestSchedule;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InterestScheduleController {
    private final InterestScheduleService interestScheduleService;
}
