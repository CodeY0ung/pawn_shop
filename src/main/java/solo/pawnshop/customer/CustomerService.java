package solo.pawnshop.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.pawnshop.customer.dto.request.CreateCustomerRequest;
import solo.pawnshop.customer.dto.response.CustomerInfoResponse;
import solo.pawnshop.customer.embeddable.Address;
import solo.pawnshop.customer.exception.CustomerNotFoundException;
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

    // 전체 회원 조회
    public List<CustomerInfoResponse> findAll(){

        // stream으로 list안에 담긴 customer를 하나씩 흘려보낸다.
        // map으로 customerInfoResponse형태로 변환.
        // list형태로 만들어라.
        return customerRepository.findAll()
                .stream()
                .map(CustomerInfoResponse::from)
                .toList();
    }

    // id로 조회
    public CustomerInfoResponse findById(Long id){

        Customer customer = customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);

        return CustomerInfoResponse.from(customer);
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
