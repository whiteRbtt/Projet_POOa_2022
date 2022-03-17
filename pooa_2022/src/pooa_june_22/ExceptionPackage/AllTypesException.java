package pooa_june_22.ExceptionPackage;

public class AllTypesException extends GeneralException{

    public AllTypesException() {

    }

    public String getMessage() {
        return "Impossible d'obtenir des informations sur les types.";
    }

    public String getTitle(){
        return "Erreur lors de la récolte des informations des époques ! ";
    }
}
