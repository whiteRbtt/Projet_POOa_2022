package pooa_june_22.ExceptionPackage;

public class TypeException extends GeneralException {
    private String wrongType;

    public TypeException(String wrongType) {
        this.wrongType = wrongType;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongType + " n'est pas une chaîne de caractères ou dépasse la limite (45 caractères)";
    }

    public String getTitle(){
        return "Erreur de type ! ";
    }
}
