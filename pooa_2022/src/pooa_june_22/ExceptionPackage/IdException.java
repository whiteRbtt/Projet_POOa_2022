package pooa_june_22.ExceptionPackage;

public class IdException extends GeneralException {
    private Integer wrongId;

    public IdException(Integer wrongId) {
        this.wrongId = wrongId;
    }

    public String getMessage() {
        return "La valeur " + wrongId + " doit être un entier positif";
    }

    public String getTitle() {
        return "Erreur sur l'id ! ";
    }
}
