package solo.pawnshop.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import solo.pawnshop.customer.dto.request.CreateCustomerRequest;
import solo.pawnshop.customer.embeddable.Address;
import solo.pawnshop.customer.exception.DuplicatedCustomerException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class customerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    // 생성 가능.
    @Test
    @DisplayName("고객 정보 생성 가능")
    void createCustomer(){
        //given


        CreateCustomerRequest request =
                CreateCustomerRequest.builder()
                        .name("customer")
                        .phone("010-1234-5678")
                        .birthDate(LocalDate.of(2000,06,11))
                        .zipCode("47193")
                        .roadAddress("roadAddress")
                        .detailAddress("detailAddress")
                        .payerBank("test bank")
                        .payerName("test name")
                        .payerAccount("test account")
                        .build();

        when(customerRepository.existByNameAndPhoneAndBirthDate("customer","01012345678", request.birthDate()))
                .thenReturn(false);

        // when
        customerService.createCustomer(request);

        //then
        ArgumentCaptor<Customer> captor
                = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).save(captor.capture());

        Customer savedCustomer = captor.getValue();

        assertThat(savedCustomer.getPhone()).isEqualTo("01012345678");
    }

    // 중복회원 등록 불가
    @Test
    @DisplayName("이름,전화번호, 생년월일 중복 불가")
    void cannotDuplicatedNameAndPhoneAndBirthDate(){
        //given


        CreateCustomerRequest request =
                CreateCustomerRequest.builder()
                        .name("customer")
                        .phone("010-1234-5678")
                        .birthDate(LocalDate.of(2000,06,11))
                        .zipCode("47193")
                        .roadAddress("roadAddress")
                        .detailAddress("detailAddress")
                        .payerBank("test bank")
                        .payerName("test name")
                        .payerAccount("test account")
                        .build();

        when(customerRepository.existByNameAndPhoneAndBirthDate(request.name(),"01012345678",request.birthDate()))
                .thenReturn(true);

        //when && then
        assertThatThrownBy(()->customerService.createCustomer(request))
                .isInstanceOf(DuplicatedCustomerException.class);

        verify(customerRepository, never()).save(any(Customer.class));
    }
}
