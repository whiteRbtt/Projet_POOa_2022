package pooa_june_22.ExceptionPackage;

public class FormException extends GeneralException {
    private String message;

    public FormException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "le champs " + message + " ne peut pas être vides";
    }

    public String getTitle(){
        return "Le formulaire n'est pas complété correctement";
    }
}
