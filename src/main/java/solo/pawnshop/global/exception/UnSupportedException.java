package solo.pawnshop.global.exception;

public class UnSupportedException extends RuntimeException{
    public UnSupportedException(){
        super("아직 구현되지 않은 기능입니다.");
    }

    public UnSupportedException(String message){
        super(message);
    }
}
