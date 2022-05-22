package pooa_june_22.ExceptionPackage;

public abstract class GeneralException extends Exception {

    public GeneralException() {
    }

    public abstract String getMessage();

    public String getTitle() {
        return "Erreur générique !";
    }
}
