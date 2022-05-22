package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.ColonyDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Colony;
import pooa_june_22.ModelPackage.Era;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ColonyDBAccess implements ColonyDataAccess {

    //-----------------------------------Get all the colonies for a given specie-----------------------------------
    public ArrayList<Colony> getColonies(String specie) throws ConnectionException, NameException, DateException, AllColoniesException {
        ArrayList<Colony> colonies = new ArrayList<>();

        try {
            // connect
            Connection connection = SingletonConnexion.getInstance();

            // read triple joint
            String sqlInstruction = "select c.TribalName, a.Name, era.EraName, era.Beginning, era.Ending from colony c " +
                    "join era on c.Period = era.EraName " +
                    "join specie on c.Lifeform = specie.ScientificName " +
                    "join astronomicalbody a on a.AstroId = c.Location " +
                    "where specie.VernacularName = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, specie);
            ResultSet data = preparedStatement.executeQuery();


            while (data.next()) {
                if (!data.wasNull()) {
                    GregorianCalendar beginningDate = new GregorianCalendar();
                    GregorianCalendar endingDate = new GregorianCalendar();

                    beginningDate.setTime(data.getDate("Beginning"));
                    if (data.getDate("Ending") == null) {
                        endingDate = null;
                    } else {
                        endingDate.setTime(data.getDate("Ending"));
                    }

                    Era newEra = new Era(data.getString("eraName"), beginningDate, endingDate);

                    Colony colony = new Colony(
                            specie,
                            data.getString("Name"),
                            newEra,
                            data.getString("TribalName")
                    );
                    colonies.add(colony);
                }
            }
        } catch (SQLException e) {
            throw new AllColoniesException(specie);
        }

        return colonies;
    }
}
