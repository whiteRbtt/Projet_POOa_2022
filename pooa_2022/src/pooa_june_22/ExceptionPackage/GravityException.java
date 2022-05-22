package pooa_june_22.ExceptionPackage;

public class GravityException extends GeneralException {
    private Integer wrongGravity;

    public GravityException(Integer wrongGravity) {
        this.wrongGravity = wrongGravity;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongGravity + " pour la gravité doit être un entier positif.";
    }

    public String getTitle() {
        return "Erreur sur la gravité ! ";
    }
}
