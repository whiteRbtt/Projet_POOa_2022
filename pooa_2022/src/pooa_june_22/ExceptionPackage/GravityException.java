package pooa_june_22.ExceptionPackage;

public class GravityException extends GeneralException {

    public GravityException() {

    }

    @Override
    public String getMessage() {
        return "La gravité doit être positive ou nulle";
    }

    public String getTitle() {
        return "Erreur sur la gravité ! ";
    }
}
