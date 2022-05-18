package pooa_june_22.DataAccessPackage;

import pooa_june_22.ExceptionPackage.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection connectionToken;

    //-----------------------------------Create an unique instance of the connexion-----------------------------------
    public static Connection getInstance() throws ConnectionException {

        if (connectionToken == null) {
            try {
                connectionToken =
                        DriverManager.getConnection("jdbc:mysql://localhost:3306/solarsystem",
                                "root",
                                "intruderZ");
            } catch (SQLException e) {
                throw new ConnectionException(true);
            }
        }
        return connectionToken;
    }

    //-----------------------------------Allow to close the connexion at the close of the application-----------------------------------
    public static void close() throws ConnectionException {
        if(connectionToken != null){
            try {
                connectionToken.close();
            } catch (SQLException e) {
                throw new ConnectionException(false);
            }
        }
    }
}
