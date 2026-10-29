package solo.pawnshop.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.pawnshop.customer.dto.request.CreateCustomerRequest;
import solo.pawnshop.customer.dto.request.CustomerSearchConditions;
import solo.pawnshop.customer.dto.request.UpdateCustomerRequest;
import solo.pawnshop.customer.dto.response.CustomerInfoResponse;
import solo.pawnshop.customer.embeddable.Address;
import solo.pawnshop.customer.embeddable.PayerAccount;
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
                .zipCode(request.zipCode())
                .roadAddress(request.roadAddress())
                .detailAddress(request.detailAddress())
                .build();

        PayerAccount payerAccount = PayerAccount.builder()
                .payerBank(request.payerBank())
                .payerName(request.payerName())
                .payerAccount(request.payerAccount())
                .build();

        Customer customer = Customer.builder()
                .name(request.name())
                .phone(normalizePhone)
                .birthDate(request.birthDate())
                .address(address)
                .payerAccount(payerAccount)
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

    // 조건들로 조회
    public List<CustomerInfoResponse> findByConditions(CustomerSearchConditions conditions){
        String name = normalizeBlank(conditions.name());
        String phone = normalizeBlank(conditions.phone());

        if(phone != null){
            phone = normalizePhone(phone);
        }

        return customerRepository.searchCustomers(name, phone, conditions.birth_date())
                .stream()
                .map(CustomerInfoResponse::from)
                .toList();
    }

    // 고객 정보 수정
    public CustomerInfoResponse updateCustomer(Long id, UpdateCustomerRequest request){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);

        // 중복체크
        boolean duplicated = customerRepository
                .existsByNameAndPhoneAndBirthDateAndCustomerIdNot(
                        request.name(), request.phone(), request.birthDate(), id
                );

        if(duplicated){
            throw new CustomerNotFoundException();
        }

        Address address = Address.builder()
                .zipCode(request.zipCode())
                .roadAddress(request.roadAddress())
                .detailAddress(request.detailAddress())
                .build();

        customer.changeName(request.name());
        customer.changePhone(normalizePhone(request.phone()));
        customer.changeBirthDate(request.birthDate());
        customer.changeAddress(address);

        return CustomerInfoResponse.from(customer);
    }

    // 고객 삭제
    public void deleteCustomerById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow();

        customer.delete();
        // 영속성 컨텍스트 때문에 save 필요없음.
    }

    // 고객 복구
    public CustomerInfoResponse restoreCustomerById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow();
        customer.restore();
        return CustomerInfoResponse.from(customer);
    }

    private String normalizeBlank(String value){
        if(value == null || value.isBlank()){
            return null;
        }
        return value.trim();
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
