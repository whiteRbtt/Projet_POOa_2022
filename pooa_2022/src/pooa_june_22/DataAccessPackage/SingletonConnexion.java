package pooa_june_22.DataAccessPackage;

import pooa_june_22.ExceptionPackage.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection connectionToken;

    public static Connection getInstance() throws ConnectionException {

        if (connectionToken == null) {
            try {
                connectionToken =
                        DriverManager.getConnection("jdbc:mysql://localhost:3306/solarsystem",
                                "root",
                                "ALIcia91270");
            } catch (SQLException e) {
                throw new ConnectionException(e.getMessage());
            }
        }
        return connectionToken;
    }
}
