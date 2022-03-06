package pooa_june_22.ExceptionPackage;

public class FormException extends Exception {
    private String message;

    public FormException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "les champs " + message + " ne peuvent pas être vides";
    }
}
