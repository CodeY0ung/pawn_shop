package solo.pawnshop.customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solo.pawnshop.customer.dto.request.CreateCustomerRequest;
import solo.pawnshop.customer.dto.request.CustomerSearchConditions;
import solo.pawnshop.customer.dto.request.UpdateCustomerRequest;
import solo.pawnshop.customer.dto.response.CustomerInfoResponse;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<CustomerInfoResponse> createCustomer(
            @Valid @RequestBody CreateCustomerRequest request){

        CustomerInfoResponse response = customerService.createCustomer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerInfoResponse>> findAll(
    ){
        List<CustomerInfoResponse> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerInfoResponse> findById(
           @Positive @PathVariable Long id
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

    // 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<CustomerInfoResponse> updateCustomerById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequest request
            ){
        CustomerInfoResponse response = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(response);
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(
            @PathVariable Long id
    ){
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<CustomerInfoResponse> restoreCustomer(
            @PathVariable Long id){
        CustomerInfoResponse response
                = customerService.restoreCustomerById(id);
        return ResponseEntity.ok(response);
    }


}
