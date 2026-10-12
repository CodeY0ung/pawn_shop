package solo.pawnshop.customer.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(){
        super("회원 정보가 없습니다.");
    }
}
