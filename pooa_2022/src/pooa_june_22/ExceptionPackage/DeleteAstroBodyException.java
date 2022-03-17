package pooa_june_22.ExceptionPackage;

public class DeleteAstroBodyException extends GeneralException {
    private int wrongID;

    public DeleteAstroBodyException(int wrongID) {
        this.wrongID = wrongID;
    }

    public String getMessage() {
        return "La suppression de l'objet :  " + wrongID + " N'a pas pu s'effectuer";
    }

    public String getTitle(){
        return "Erreur lors de la suppression ! ";
    }
}
