package pooa_june_22.ExceptionPackage;

public class ConnectionException extends Exception {
    private String connectionErrorMessage;

    public ConnectionException(String connectionErrorMessage) {
        this.connectionErrorMessage = connectionErrorMessage;
    }

    public String getMessage() {
        return "Erreur lors de la tentative de connexion à la base de donnée : " + connectionErrorMessage;
    }
}
