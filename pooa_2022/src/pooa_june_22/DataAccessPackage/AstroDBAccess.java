package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.AstroBodyDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AstroDBAccess implements AstroBodyDataAccess {

    public void addAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException {
        Connection connection = SingletonConnexion.getInstance();

        String sqlInstruction = "insert into astronomicalbody " +
                "(AstroId, Name, Type, Climate, FirstKnownExplorer, Gravity, HasEndemicLifeform, FirstExploDate) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, astroBody.getAstroId());
            preparedStatement.setString(2, astroBody.getName());
            preparedStatement.setString(3, astroBody.getType());

            if (astroBody.getClimate() != null) {
                preparedStatement.setString(4, astroBody.getClimate());
            } else {
                preparedStatement.setNull(4, Types.VARCHAR);
            }

            if (astroBody.getFirstExplorer() != null) {
                preparedStatement.setInt(5, astroBody.getFirstExplorer());
            } else {
                preparedStatement.setNull(5, Types.INTEGER);
            }

            if (astroBody.getGravity() != null) {
                preparedStatement.setInt(6, astroBody.getGravity());
            } else {
                preparedStatement.setNull(6, Types.INTEGER);
            }

            if (astroBody.getHasLifeform() != null) {
                preparedStatement.setBoolean(7, astroBody.getHasLifeform());
            } else {
                preparedStatement.setNull(7, Types.BIT);
            }

            if (astroBody.getFirstExploDate() != null) {
                preparedStatement.setDate(8, new java.sql.Date(astroBody.getFirstExploDate().getTimeInMillis()));
            } else {
                preparedStatement.setNull(8, Types.DATE);
            }

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new AddAstroBodyException(e.getMessage());
        }

    }

    public void updateAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException {
        Connection connection = SingletonConnexion.getInstance();

        String sqlInstruction =
                "update astronomicalbody " +
                        "set Name = ? , Type = ?, Climate = ?, FirstKnownExplorer = ?, Gravity = ?, HasEndemicLifeform = ?, FirstExploDate = ? " +
                        "where AstroId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, astroBody.getName());
            preparedStatement.setString(2, astroBody.getType());

            if (astroBody.getClimate() != null) {
                preparedStatement.setString(3, astroBody.getClimate());
            } else {
                preparedStatement.setNull(3, Types.VARCHAR);
            }

            if (astroBody.getFirstExplorer() != null) {
                preparedStatement.setInt(4, astroBody.getFirstExplorer());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }

            if (astroBody.getGravity() != null) {
                preparedStatement.setInt(5, astroBody.getGravity());
            } else {
                preparedStatement.setNull(5, Types.INTEGER);
            }

            if (astroBody.getHasLifeform() != null) {
                preparedStatement.setBoolean(6, astroBody.getHasLifeform());
            } else {
                preparedStatement.setNull(6, Types.BIT);
            }

            if (astroBody.getFirstExploDate() != null) {
                preparedStatement.setDate(7, new java.sql.Date(astroBody.getFirstExploDate().getTimeInMillis()));
            } else {
                preparedStatement.setNull(7, Types.DATE);
            }

            preparedStatement.setInt(8, astroBody.getAstroId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new AddAstroBodyException(e.getMessage());
        }

    }

    public ArrayList<AstroBody> getAllAstroBodies() throws AllAstroBodiesException, ClimateException, TypeException, GravityException, NameException, DateException, IdException, ConnectionException {
        ArrayList<AstroBody> allAstroBodies = new ArrayList<>();

        try {
            Connection connection = SingletonConnexion.getInstance();

            String sqlInstruction = "select * from astronomicalbody";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {

                AstroBody astroBody = new AstroBody
                        (
                                data.getInt("AstroId"),
                                data.getString("Name"),
                                null,
                                data.getString("Type"),
                                null,
                                null,
                                null,
                                null
                        );


                Integer firstExplorer = data.getInt("FirstKnownExplorer");
                if (!data.wasNull()) {
                    astroBody.setFirstExplorer(firstExplorer);
                }

                String climate = data.getString("Climate");
                if (!data.wasNull()) {
                    astroBody.setClimate(climate);
                }

                Integer gravity = data.getInt("Gravity");
                if (!data.wasNull()) {
                    astroBody.setGravity(gravity);
                }

                Boolean hasLifeform = data.getBoolean("hasEndemicLifeform");
                if (!data.wasNull()) {
                    astroBody.setHasLifeform(hasLifeform);
                }

                java.sql.Date firstExploDate = data.getDate("FirstExploDate");
                if (!data.wasNull()) {
                    GregorianCalendar date = new GregorianCalendar();
                    date.setTime(firstExploDate);
                    astroBody.setFirstExploDate(date);
                }

                allAstroBodies.add(astroBody);
            }

        } catch (SQLException exception) {
            throw new AllAstroBodiesException(exception.getMessage());
        }

        return allAstroBodies;
    }
}
