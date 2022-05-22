package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.ColonyDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.ResearchedColonies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ColonyDBAccess implements ColonyDataAccess {

    //-----------------------------------Get all the colonies for a given specie-----------------------------------
    public ArrayList<ResearchedColonies> getColonies(String specie) throws ConnectionException, NameException, DateException, AllColoniesException {
        ArrayList<ResearchedColonies> colonies = new ArrayList<>();

        System.out.println(specie);
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
            java.sql.Date sqlDate1;
            java.sql.Date sqlDate2;
            while (data.next()) {
                GregorianCalendar calendar1 = new GregorianCalendar();
                GregorianCalendar calendar2 = new GregorianCalendar();
                sqlDate1 = data.getDate("Beginning");
                calendar1.setTime(sqlDate1);
                ResearchedColonies colony = new ResearchedColonies(data.getString("eraName"), calendar1, null, data.getString("Name"), null);
                sqlDate2 = data.getDate("Ending");
                if (!data.wasNull()) {
                    calendar2.setTime(sqlDate2);
                    colony.setEraEnding(calendar2);
                }
                String name = data.getString("TribalName");
                if (!data.wasNull()) {
                    colony.setTribalName(name);
                }

                colonies.add(colony);

            }

            System.out.println(colonies);


        } catch (SQLException e) {
            throw new AllColoniesException(specie);
        }

        return colonies;
    }
}
