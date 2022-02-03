package pooa_june_22.ExceptionPackage;

public class AllEraException extends Exception {
    private String message;

    public AllEraException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
