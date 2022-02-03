package pooa_june_22.ExceptionPackage;

public class TypeException extends Exception {
    private String wrongType;

    public TypeException(String wrongType) {
        this.wrongType = wrongType;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongType + " n'est pas une chaine ou bien dépasse la limite de caractères (45)";
    }
}
