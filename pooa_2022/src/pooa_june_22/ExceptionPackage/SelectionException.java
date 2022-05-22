package pooa_june_22.ExceptionPackage;

public class SelectionException extends GeneralException {
    private String message;

    public SelectionException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "Veuillez sélectionner " + message;
    }

    public String getTitle() {
        return "La sélection est incorrecte";
    }

}
