package pooa_june_22.ExceptionPackage;

public class AllAstroBodiesException extends GeneralException {
    private String message;

    public AllAstroBodiesException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle(){
        return "Erreur lors de la récolte des informations des planètes ! ";
    }
}
