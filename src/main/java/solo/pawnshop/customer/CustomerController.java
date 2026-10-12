package solo.pawnshop.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solo.pawnshop.customer.dto.CreateCustomerRequest;
import solo.pawnshop.customer.dto.CustomerInfoResponse;

import java.util.List;
import java.util.concurrent.CompletionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<Void> createCustomer(
            @RequestBody CreateCustomerRequest request){

        customerService.createCustomer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping()
    public ResponseEntity<List<CustomerInfoResponse>> findAll(
    ){
        List<CustomerInfoResponse> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerInfoResponse> findById(
            @PathVariable Long id
    ){
        CustomerInfoResponse customer = customerService.findById(id);

        return ResponseEntity.ok(customer);
    }

}
