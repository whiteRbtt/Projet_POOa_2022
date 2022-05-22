package pooa_june_22.ExceptionPackage;

public class AddAstroBodyException extends GeneralException {
    private String wrongAstro;

    public AddAstroBodyException(String wrongAstro) {
        this.wrongAstro = wrongAstro;
    }

    public String getMessage() {
        return "L'objet : " + wrongAstro + " n'a pas pu être ajouté.";
    }

    public String getTitle() {
        return "Erreur lors de l'ajout d'une planète ! ";
    }
}
