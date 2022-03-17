package pooa_june_22.ExceptionPackage;

public class UpdateAstroBodyException extends GeneralException {
    private String wrongAstro;

    public UpdateAstroBodyException(String wrongAstro) {
        this.wrongAstro = wrongAstro;
    }

    public String getMessage() {
        return "L'objet : " + wrongAstro + " n'a pas pu être modifié.";
    }

    public String getTitle(){
        return "Erreur lors de la modification d'une planète ! ";
    }
}
