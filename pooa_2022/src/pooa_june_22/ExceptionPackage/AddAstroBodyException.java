package pooa_june_22.ExceptionPackage;

public class AddAstroBodyException extends Exception {
    private String message;

    public AddAstroBodyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "Erreur lors de l'ajout d'un nouvel objet : " + message;
    }

}
