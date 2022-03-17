package pooa_june_22.ExceptionPackage;

public class AllSpeciesException extends GeneralException {

    public AllSpeciesException() {
    }

    public String getMessage() {
        return "Impossible d'obtenir des informations sur les différentes espèces.";
    }

    public String getTitle(){
        return "Erreur lors de la récolte des informations des espèces ! ";
    }
}
