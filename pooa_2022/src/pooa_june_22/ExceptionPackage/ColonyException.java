package pooa_june_22.ExceptionPackage;

public class ColonyException extends Exception {
    private String message;

    public ColonyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
