package pooa_june_22.ExceptionPackage;

public class AllExplorersException extends GeneralException{

    public AllExplorersException() {

    }

    public String getMessage() {
        return "Impossible d'obtenir des informations sur les explorateurs.";
    }

    public String getTitle(){
        return "Erreur lors de la récolte des informations des explorateurs ! ";
    }
}
