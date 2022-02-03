package pooa_june_22.ExceptionPackage;

public class GravityException extends Exception {
    private Integer wrongGravity;

    public GravityException(Integer wrongGravity) {
        this.wrongGravity = wrongGravity;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongGravity + " pour la gravité doit etre un entier positif.";
    }
}
