package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.EraDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Era;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EraDBaccess implements EraDataAccess {

    public ArrayList<Era> getAllEras() throws AllEraException, ConnectionException, NameException, DateException {
        ArrayList<Era> allEras = new ArrayList<>();

        try {
            // connect
            Connection connection = SingletonConnexion.getInstance();

            // read
            String sqlInstruction = "select * from era";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {

                java.sql.Date beginning = data.getDate("Beginning");
                GregorianCalendar dateStart = new GregorianCalendar();
                dateStart.setTime(beginning);

                Era era = new Era
                        (
                                data.getString("EraName"),
                                dateStart,
                                null
                        );


                java.sql.Date ending = data.getDate("Ending");
                if (!data.wasNull()) {
                    GregorianCalendar dateEnd = new GregorianCalendar();
                    dateEnd.setTime(ending);
                    era.setEnding(dateEnd);
                }

                allEras.add(era);
            }

        } catch (SQLException exception) {
            throw new AllEraException(exception.getMessage());
        }
        return allEras;
    }
}