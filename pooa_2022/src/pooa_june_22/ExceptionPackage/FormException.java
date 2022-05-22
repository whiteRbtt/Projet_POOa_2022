package pooa_june_22.ExceptionPackage;

public class FormException extends GeneralException {
    private String message;

    public FormException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "le champ " + message + " ne peut pas être vide";
    }

    public String getTitle() {
        return "Le formulaire n'est pas complété correctement";
    }
}
