package pooa_june_22.ExceptionPackage;

public class AllEraException extends GeneralException {


    public AllEraException() {

    }

    public String getMessage() {
        return "Impossible d'obtenir les époques.";
    }

    public String getTitle(){
        return "Erreur lors de la récolte des informations des époques ! ";
    }
}
