package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.ColonyDataAccess;
import pooa_june_22.ExceptionPackage.ColonyException;
import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ModelPackage.Colony;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ColonyDBAccess implements ColonyDataAccess {
    public ArrayList<Colony> getColonies(GregorianCalendar date, String specie) throws ConnectionException, NameException, ColonyException {
        ArrayList<Colony> colonies = new ArrayList<>();

        try {
            // connect
            Connection connection = SingletonConnexion.getInstance();

            // read triple joint
            String sqlInstruction = "select c.Lifeform,c.Location, c.Period, c.tribalName " +
                    "from colony c " +
                    "join specie s on s.ScientificName = c.Lifeform " +
                    "join era e on e.EraName = c.period " +

                    "where c.Lifeform = ? " +
                    "and ? between e.Beginning and IFNULL (e.Ending, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, specie);
            preparedStatement.setDate(2, new java.sql.Date(date.getTimeInMillis()));
            preparedStatement.setString(3, "9999-12-31");
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {

                Colony colony = new Colony
                        (
                                data.getString("Lifeform"),
                                data.getInt("Location"),
                                data.getString("Period"),
                                null
                        );

                String name = data.getString("TribalName");
                if (!data.wasNull()) {
                    colony.setName(name);
                }

                colonies.add(colony);
            }

        } catch (SQLException exception) {
            throw new ColonyException(exception.getMessage());
        }

        return colonies;
    }
}
