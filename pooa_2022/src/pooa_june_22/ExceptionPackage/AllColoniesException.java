package pooa_june_22.ExceptionPackage;

public class AllColoniesException extends GeneralException {
    private String wrongSpecie;

    public AllColoniesException(String wrongSpecie) {
        this.wrongSpecie = wrongSpecie;
    }

    public String getMessage() {
        return "Impossible de chercher des colonies pour l'espèce " + wrongSpecie;
    }

    public String getTitle(){
        return "Erreur lors de la récolte des informations des colonies ! ";
    }
}
