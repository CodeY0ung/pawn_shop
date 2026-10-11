package solo.pawnshop.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.pawnshop.customer.dto.CreateCustomerRequest;
import solo.pawnshop.customer.embeddable.Address;
import solo.pawnshop.customer.exception.DuplicatedCustomerException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void createCustomer(CreateCustomerRequest request){

        String normalizePhone = normalizePhone(request.phone());

        // 현재는 폰번호, 이름으로 구별 추후 필요하면 개선
        validate(request.name(),normalizePhone,request.birthDate());

        Address address = Address.builder()
                .zipCode(request.address().zipCode())
                .roadAddress(request.address().roadAddress())
                .detailAddress(request.address().detailAddress())
                .build();

        Customer customer = Customer.builder()
                .name(request.name())
                .phone(normalizePhone)
                .address(address)
                .birthDate(request.birthDate())
                .build();

        customerRepository.save(customer);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }



    private void validate(String name, String phone, LocalDate birthDate){
        if(customerRepository.existByNameAndPhoneAndBirthDate(name, phone, birthDate)){
            throw new DuplicatedCustomerException();
        }
    }

    private String normalizePhone(String phone) {
        return phone.replaceAll("[^0-9]", "");
    }
}
