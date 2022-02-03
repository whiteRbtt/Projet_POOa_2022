package pooa_june_22.ExceptionPackage;

public class AllAstroBodiesException extends Exception {
    private String message;

    public AllAstroBodiesException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
