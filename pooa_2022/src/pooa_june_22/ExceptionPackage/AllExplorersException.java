package pooa_june_22.ExceptionPackage;

public class AllExplorersException extends Throwable {
    private String message;

    public AllExplorersException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
