package pooa_june_22.ExceptionPackage;

public class GeneralException extends Throwable {
    private String message;
    public GeneralException(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
