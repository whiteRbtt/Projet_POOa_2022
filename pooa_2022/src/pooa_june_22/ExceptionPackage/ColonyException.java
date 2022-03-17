package pooa_june_22.ExceptionPackage;

public class ColonyException extends GeneralException {
    private String message;

    public ColonyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle(){
        return "Erreur sur la colonie ! ";
    }
}
