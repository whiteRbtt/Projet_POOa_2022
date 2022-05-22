package pooa_june_22.ExceptionPackage;

public class NameException extends GeneralException {
    private String wrongName;

    public NameException(String wrongName) {
        this.wrongName = wrongName;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongName + " n'est pas une chaine ou bien dépasse la limite de caractères (45)";
    }

    public String getTitle() {
        return "Erreur sur le nom ! ";
    }
}
