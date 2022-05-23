package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.ColonyDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.*;

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
            String sqlInstruction = "select c.TribalName, a.Name, a.AstroId, e.EraName, e.Beginning, e.Ending, s.ScientificName, s.VernacularName, s.IsExtinct, s.IsIntelligent from colony c " +
                    "join era e on c.Period = e.EraName " +
                    "join specie s on c.Lifeform = s.ScientificName " +
                    "join astronomicalbody a on a.AstroId = c.Location " +
                    "where s.VernacularName = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, specie);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {

                    GregorianCalendar beginningDate = new GregorianCalendar();
                    if (data.getDate("Beginning") == null) beginningDate = null;
                    else beginningDate.setTime(data.getDate("Beginning"));

                    GregorianCalendar endingDate = new GregorianCalendar();
                    if (data.getDate("Ending") == null) endingDate = null;
                    else endingDate.setTime(data.getDate("Ending"));


                    Colony colony = new Colony(
                            new Specie(
                                    data.getString("ScientificName"),
                                    data.getString("VernacularName"),
                                    data.getBoolean("IsIntelligent"),
                                    data.getBoolean("IsExtinct")
                            ),
                            new AstroBody(
                                    data.getInt("AstroId"),
                                    data.getString("Name"),
                                    null,
                                    new AstroType(999, "exoplanet"),
                                    null,
                                    null,
                                    null,
                                    null
                            ),
                            new Era(data.getString("EraName"), beginningDate, endingDate),
                            data.getString("TribalName")
                    );
                    colonies.add(colony);
                }
            }
        } catch (SQLException | TypeIDException | IdException | ClimateException | TypeException | GravityException e) {
            throw new AllColoniesException(specie);
        }
        return colonies;
    }
}
