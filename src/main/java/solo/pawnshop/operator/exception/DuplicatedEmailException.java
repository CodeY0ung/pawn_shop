package solo.pawnshop.operator.exception;

public class DuplicatedEmailException extends RuntimeException{

    public DuplicatedEmailException(){
        super("중복된 email 입니다.");
    }

    public DuplicatedEmailException(String message){
        super(message);
    }

}
