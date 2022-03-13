package pooa_june_22.ExceptionPackage;

public class TypeException extends Exception {
    private Integer wrongType;

    public TypeException(Integer wrongType) {
        this.wrongType = wrongType;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongType + " n'est pas une chaîne ou bien dépasse la limite de caractères (45)";
    }
}
