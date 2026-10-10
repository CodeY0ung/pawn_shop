package solo.pawnshop.operator.exception;

public class StaffNotFoundException extends RuntimeException{

    public StaffNotFoundException(){
        super("해당 직원이 존재하지 않습니다.");
    }
    public StaffNotFoundException(String message){
        super(message);
    }
}
