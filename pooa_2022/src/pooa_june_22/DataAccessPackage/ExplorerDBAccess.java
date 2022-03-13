package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.ExplorerDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Explorer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ExplorerDBAccess implements ExplorerDataAccess {

    public ArrayList<Explorer> getAllExplorers() throws IdException, DateException, NameException, AllExplorersException, ConnectionException {
        ArrayList<Explorer> allExplorers = new ArrayList<>();

        try {
            // connect
            Connection connection = SingletonConnexion.getInstance();

            // read
            String sqlInstruction = "select * from explorer";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {

                Explorer explorer = new Explorer
                        (
                                data.getInt("ExplorerId"),
                                data.getString("Name"),
                                null,
                                null,
                                data.getString("Specie")
                        );


                Boolean isAlive = data.getBoolean("IsAlive");
                if (!data.wasNull()) {
                    explorer.setAlive(isAlive);
                }

                java.sql.Date birthDate = data.getDate("BirthDate");
                if (!data.wasNull()) {
                    GregorianCalendar date = new GregorianCalendar();
                    date.setTime(birthDate);
                    explorer.setBirthDate(date);
                }

                allExplorers.add(explorer);
            }

        } catch (SQLException exception) {
            throw new AllExplorersException(exception.getMessage());
        }

        return allExplorers;
    }

}

