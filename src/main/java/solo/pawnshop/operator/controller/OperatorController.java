package solo.pawnshop.operator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solo.pawnshop.operator.dto.CreateOperatorRequest;
import solo.pawnshop.operator.service.OperatorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operators")
public class OperatorController {

    private final OperatorService operatorService;


    @PostMapping("/staff")
    public ResponseEntity<Void> createStaff(
            @RequestBody CreateOperatorRequest request
            ){

        operatorService.createStaff(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/owner")
    public ResponseEntity<Void> createOwner(
            @RequestBody CreateOperatorRequest request
    ){
        operatorService.createOwner(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

}
