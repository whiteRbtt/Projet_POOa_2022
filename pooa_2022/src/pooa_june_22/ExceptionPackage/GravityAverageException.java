package pooa_june_22.ExceptionPackage;

public class GravityAverageException extends GeneralException {
    public GravityAverageException() {
    }

    @Override
    public String getTitle() {
        return "Erreur sur la moyenne des gravités";
    }

    @Override
    public String getMessage() {
        return "Il n'y a pas assez d'informations ou de planètes pour établir une moyenne pertinente ! ";
    }
}
