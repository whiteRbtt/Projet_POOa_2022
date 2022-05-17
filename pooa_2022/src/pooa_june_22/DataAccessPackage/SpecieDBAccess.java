package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.SpecieDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.BestSpecie;
import pooa_june_22.ModelPackage.Era;
import pooa_june_22.ModelPackage.Specie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SpecieDBAccess implements SpecieDataAccess {

    //-----------------------------------Get all species from the DB-----------------------------------
    @Override
    public ArrayList<Specie> getAllSpecies() throws AllSpeciesException, ConnectionException, NameException {
        ArrayList<Specie> allSpecies = new ArrayList<>();

        try {
            // connect
            Connection connection = SingletonConnexion.getInstance();

            // read
            String sqlInstruction = "select * from specie";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {

                Specie specie = new Specie
                        (
                                data.getString("ScientificName"),
                                data.getString("VernacularName"),
                                null,
                                null
                        );


                Boolean isIntelligent = data.getBoolean("IsIntelligent");
                if (!data.wasNull()) {
                    specie.setIntelligent(isIntelligent);
                }

                Boolean isExtinct = data.getBoolean("IsExtinct");
                if (!data.wasNull()) {
                    specie.setExtinct(isExtinct);
                }

                allSpecies.add(specie);
            }

        } catch (SQLException exception) {
            throw new AllSpeciesException();
        }
        return allSpecies;
    }
    //-----------------------------------Get the number of colonies for species-----------------------------------
    public int getNbColonyForSpecie(String vernacularName) throws ColonyException, ConnectionException {

        int nbColony = 0;
        try {
            // connect
            Connection connection = SingletonConnexion.getInstance();

            // read triple joint
            String sqlInstruction = "select count(*) as 'Count'\n" +
                    "from specie s, colony c\n" +
                    "where s.ScientificName = c.Lifeform and VernacularName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, vernacularName);
            ResultSet data = preparedStatement.executeQuery();

            data.next();

            if(!data.wasNull()){
                nbColony = data.getInt("Count");

            }
        } catch (SQLException e) {
            throw new ColonyException("Erreur lors de la recherche d'infos sur le nombre de colonies de l'espèce");
        }
        return nbColony;
    }

    //-----------------------------------Get Eras for Colony-----------------------------------
    public ArrayList<Era> getBestEras(String vernacularName) throws AllEraException, ConnectionException, DateException {
        ArrayList<Era> eras = new ArrayList<>();
        try{
            // connect
            Connection connection = SingletonConnexion.getInstance();

            // read

            String sqlInstruction = "select e.EraName, e.Beginning, e.Ending from specie s, colony c, era e where s.ScientificName = c.Lifeform and c.Period = e.EraName and s.VernacularName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, vernacularName);
            ResultSet data = preparedStatement.executeQuery();
            while(data.next()){
                java.sql.Date beginDate = data.getDate("Beginning");
                GregorianCalendar begin = new GregorianCalendar();
                begin.setTime(beginDate);
                Era bestEra = new Era(data.getString("EraName"), begin);
                java.sql.Date endDate = data.getDate("Ending");
                GregorianCalendar ending = new GregorianCalendar();
                if(!data.wasNull()){
                    ending.setTime(endDate);
                    bestEra.setEnding(ending);
                }
                eras.add(bestEra);
            }

        } catch (SQLException e) {
            throw new AllEraException();
        }
        return eras;
    }
    public ArrayList<Integer> getGravityOfColony(String vernacularName) throws ConnectionException, GravityException {
        ArrayList<Integer> gravities = new ArrayList<>();
        try{
            Connection connection = SingletonConnexion.getInstance();

            String sqlInstruction = "select a.Gravity from specie s, colony c, astronomicalbody a where s.ScientificName = c.Lifeform and c.Location = a.AstroId and s.VernacularName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, vernacularName);
            ResultSet data = preparedStatement.executeQuery();
            int gravity = 0;
            while(data.next()){
                if(!data.wasNull()){
                    gravity = data.getInt("Gravity");
                }
                gravities.add(gravity);
            }


        } catch (SQLException throwables) {
            throw new GravityException(-1);
        }
        return gravities;
    }
}
