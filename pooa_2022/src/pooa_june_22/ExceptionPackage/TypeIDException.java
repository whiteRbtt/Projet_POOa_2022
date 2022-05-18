package pooa_june_22.ExceptionPackage;

public class TypeIDException extends GeneralException{
    private Integer wrongID;

    public TypeIDException(Integer wrongID){
        this.wrongID =wrongID;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongID + " doit être un entier positif.";
    }

    @Override
    public String getTitle() {
        return "Erreur de type ! ";
    }
}
