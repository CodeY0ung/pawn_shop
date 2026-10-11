package solo.pawnshop.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solo.pawnshop.customer.dto.CreateCustomerRequest;

import java.util.concurrent.CompletionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<Void> createCustomer(CreateCustomerRequest request){

        customerService.createCustomer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

}
