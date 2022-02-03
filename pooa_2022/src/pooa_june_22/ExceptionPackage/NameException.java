package pooa_june_22.ExceptionPackage;

public class NameException extends Exception {
    private String wrongName;

    public NameException(String wrongName) {
        this.wrongName = wrongName;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongName + " n'est pas une chaine ou bien dépasse la limite de caractères (45)";
    }
}
