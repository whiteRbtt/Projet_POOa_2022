package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.SpecieDataAccess;
import pooa_june_22.ExceptionPackage.AllSpeciesException;
import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ModelPackage.Specie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpecieDBAccess implements SpecieDataAccess {
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
            throw new AllSpeciesException(exception.getMessage());
        }
        return allSpecies;
    }
}
