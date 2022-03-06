package pooa_june_22.ExceptionPackage;

public class IdException extends Exception {
    private Integer wrongId;

    public IdException(Integer wrongId) {
        this.wrongId = wrongId;
    }

    public String getMessage() {
        return "La valeur " + wrongId + " doit etre un entier positif";
    }

}
