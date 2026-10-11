package solo.pawnshop.customer.exception;

public class DuplicatedCustomerException extends RuntimeException{

    public DuplicatedCustomerException(){
        super("이미 존재하는 회원입니다.");
    }
}
