package pooa_june_22.ExceptionPackage;

public class DeleteAstroBodyException extends Exception{
    private String message;

    public DeleteAstroBodyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "Erreur lors de la suppression d'un nouvel objet : " + message;
    }

}
