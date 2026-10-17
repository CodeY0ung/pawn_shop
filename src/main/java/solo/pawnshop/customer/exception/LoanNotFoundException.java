package solo.pawnshop.customer.exception;

public class LoanNotFoundException extends RuntimeException{
    public LoanNotFoundException(){
        super("존재하지 않는 계약입니다.");
    }
}
