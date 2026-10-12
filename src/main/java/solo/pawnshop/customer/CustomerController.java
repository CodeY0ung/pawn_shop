package solo.pawnshop.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solo.pawnshop.customer.dto.request.CreateCustomerRequest;
import solo.pawnshop.customer.dto.request.CustomerSearchConditions;
import solo.pawnshop.customer.dto.response.CustomerInfoResponse;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<List<CustomerInfoResponse>> findByConditions(
            @ModelAttribute CustomerSearchConditions conditions
    ){
        List<CustomerInfoResponse> customers = customerService.findByConditions(conditions);

        return ResponseEntity.ok(customers);
    }

}
