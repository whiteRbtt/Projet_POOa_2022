package pooa_june_22.ExceptionPackage;

public class AllSpeciesException extends Throwable {
    private String message;

    public AllSpeciesException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
