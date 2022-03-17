package pooa_june_22.ExceptionPackage;

public class TypeException extends GeneralException {
    private String wrongType;

    public TypeException(String wrongType) {
        this.wrongType = wrongType;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongType + " n'est pas une chaîne ou bien dépasse la limite de caractères (45)";
    }

    public String getTitle(){
        return "Erreur sur le type ! ";
    }
}
