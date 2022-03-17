package pooa_june_22.ExceptionPackage;

public class ConnectionException extends GeneralException {
    private boolean toConnect;


    public ConnectionException(boolean toConnect) {
        this.toConnect = toConnect;
    }

    public String getMessage() {
        if(this.toConnect){return "La connexion n'a pas pu s'effectuer : ";}
        return "La fermeture de la connexion n'a pas pu s'effectuer";
    }

    public String getTitle(){
        return "Erreur de connexion";
    }
}
