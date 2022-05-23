package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.AstroBodyDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AstroDBAccess implements AstroBodyDataAccess {

    //-----------------------------------Get the greatest ID of the DB for incrementation-----------------------------------
    public int getMaxId() throws ConnectionException, IdException {
        String instructionSQL;
        Connection connection;
        int id = 0;
        try {
            instructionSQL = "select max(AstroId) from astronomicalbody";
            connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(instructionSQL);
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            id = data.getInt(1);

        } catch (SQLException e) {
            throw new IdException(id);
        }
        return id;
    }

    //-----------------------------------Delete the colonies of an astro body given-----------------------------------
    public void deleteColonyForAstroBody(int location) throws ConnectionException, SQLException {
        String sqlInstruction;
        Connection connection;
        sqlInstruction = "delete from colony where Location = ?";
        connection = SingletonConnexion.getInstance();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
        preparedStatement.setInt(1, location);
        preparedStatement.executeUpdate();
    }

    //-----------------------------------Delete an astro body and the depedencies link to the table-----------------------------------
    public void deleteAstroBody(int astroID) throws ConnectionException, DeleteAstroBodyException {
        String sqlInstruction;
        Connection connection;
        try {
            deleteColonyForAstroBody(astroID);
            sqlInstruction = "delete from astronomicalbody where AstroID = ?";
            connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, astroID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteAstroBodyException(astroID);
        }
    }

    //-----------------------------------Add an astro body to the DB-----------------------------------
    public void addAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException {
        Connection connection = SingletonConnexion.getInstance();

        String sqlInstruction = "insert into astronomicalbody " +
                "(AstroId, Name, Type, Climate, FirstKnownExplorer, Gravity, HasEndemicLifeform, FirstExplorationDate) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, astroBody.getAstroId());
            preparedStatement.setString(2, astroBody.getName());
            preparedStatement.setInt(3, astroBody.getType().getTypeID());

            if (astroBody.getClimate() != null) {
                preparedStatement.setString(4, astroBody.getClimate());
            } else {
                preparedStatement.setNull(4, Types.VARCHAR);
            }

            if (astroBody.getFirstExplorer() != null) {
                preparedStatement.setInt(5, astroBody.getFirstExplorer().getExplorerId());
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
            throw new AddAstroBodyException(astroBody.getName());
        }

    }

    //-----------------------------------Update an astro body-----------------------------------
    public void updateAstroBody(AstroBody astroBody) throws ConnectionException, UpdateAstroBodyException {
        Connection connection = SingletonConnexion.getInstance();

        String sqlInstruction =
                "update astronomicalbody " +
                        "set Name = ? , Type = ?, Climate = ?, FirstKnownExplorer = ?, Gravity = ?, HasEndemicLifeform = ?, FirstExplorationDate = ? " +
                        "where AstroId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, astroBody.getName());
            preparedStatement.setInt(2, astroBody.getType().getTypeID());

            if (astroBody.getClimate() != null) {
                preparedStatement.setString(3, astroBody.getClimate());
            } else {
                preparedStatement.setNull(3, Types.VARCHAR);
            }

            if (astroBody.getFirstExplorer() != null) {
                preparedStatement.setInt(4, astroBody.getFirstExplorer().getExplorerId());
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
            throw new UpdateAstroBodyException(astroBody.getName());
        }

    }

    //-----------------------------------Get an explorer who discovered an astro body-----------------------------------
    public Explorer getExplorerForAstroBody(int exploID) throws IdException, DateException, NameException, ConnectionException, SQLException {
        String sqlInstruction;
        Connection connection;
        Explorer explorer = null;
        sqlInstruction = "select * from explorer where ExplorerId = ?";
        connection = SingletonConnexion.getInstance();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
        preparedStatement.setInt(1, exploID);
        ResultSet data = preparedStatement.executeQuery();
        data.next();
        explorer = new Explorer(data.getInt("ExplorerId"));

        explorer.setName(data.getString("Name"));
        explorer.setSpecie(new Specie(data.getString("Specie"), "alien", null, null));
        boolean isAlive = data.getBoolean("IsAlive");
        if (!data.wasNull()) {
            explorer.setAlive(isAlive);
        }
        GregorianCalendar calendar = new GregorianCalendar();
        java.sql.Date date = data.getDate("BirthDate");
        if (!data.wasNull()) {
            calendar.setTime(date);
            explorer.setBirthDate(calendar);
        }
        return explorer;
    }

    //-----------------------------------Get type for a given type from an astroBody-----------------------------------
    public AstroType getTypeForAstroBody(Integer type) throws NameException, ConnectionException, SQLException, TypeIDException {
        String sqlInstruction;
        Connection connection;
        AstroType typeAstro = null;
        sqlInstruction = "select * from astronomicaltype where TypeId=?";
        connection = SingletonConnexion.getInstance();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
        preparedStatement.setInt(1, type);
        ResultSet data = preparedStatement.executeQuery();
        data.next();
        typeAstro = new AstroType(data.getInt("TypeId"), data.getString("Name"));

        return typeAstro;
    }

    //-----------------------------------Get all the astroBodies of the DB-----------------------------------
    public ArrayList<AstroBody> getAllAstroBodies() throws AllAstroBodiesException, ClimateException, TypeException, GravityException, NameException, DateException, IdException, ConnectionException, TypeIDException {
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
                                getTypeForAstroBody(data.getInt("Type")),
                                null,
                                null,
                                null,
                                null
                        );
                Integer firstExplorer = data.getInt("FirstKnownExplorer");
                if (!data.wasNull()) {
                    Explorer explorer = getExplorerForAstroBody(firstExplorer);
                    astroBody.setFirstExplorer(explorer);
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

                java.sql.Date firstExploDate = data.getDate("FirstExplorationDate");
                if (!data.wasNull()) {
                    GregorianCalendar date = new GregorianCalendar();
                    date.setTime(firstExploDate);
                    astroBody.setFirstExploDate(date);
                }

                allAstroBodies.add(astroBody);
            }

        } catch (SQLException exception) {
            throw new AllAstroBodiesException("La récolte n'a pas pu s'effectuer");
        }

        return allAstroBodies;
    }

    //-----------------------------------Get an astroBody for a given type-----------------------------------
    public ArrayList<AstroBody> getAstroBodiesForType(String type) throws ConnectionException, ClimateException, NameException, DateException, IdException, GravityException, TypeException {
        ArrayList<AstroBody> astroBodies = new ArrayList<>();

        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "select s.VernacularName, e.Name as 'exploName', e.ExplorerId, e.IsAlive, e.Specie, a.FirstExplorationDate, a.Gravity, a.Climate, a.AstroId, a.Name, t.TypeId, t.Name from astronomicaltype t, specie s, explorer e, astronomicalbody a " +
                    "where t.TypeId = a.Type and a.FirstKnownExplorer = e.ExplorerId and e.Specie = s.ScientificName and t.Name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, type);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {

                    GregorianCalendar explorationDate = null;
                    if (data.getDate("FirstExplorationDate") != null) {
                        explorationDate = new GregorianCalendar();
                        explorationDate.setTime(data.getDate("FirstExplorationDate"));
                    }

                    AstroBody astroBody = new AstroBody(
                            data.getInt("AstroId"),
                            data.getString("Name"),
                            new Explorer(
                                    data.getInt("ExplorerId"),
                                    data.getString("ExploName"),
                                    data.getBoolean("IsAlive"),
                                    null,
                                    new Specie(data.getString("Specie"), "alien", null, null)
                            ),
                            new AstroType(
                                    data.getInt("TypeId"),
                                    data.getString("Name")
                            ),
                            data.getString("Climate"),
                            data.getInt("Gravity") == 0 ? null : data.getInt("Gravity"),
                            true,
                            explorationDate
                    );
                    astroBodies.add(astroBody);
                }
            }
        } catch (SQLException | TypeIDException e) {
            throw new TypeException(type);
        }
        return astroBodies;
    }

    //-----------------------------------Get all the astroBodies for a given period-----------------------------------
    public ArrayList<AstroBody> getAstroBodiesForPeriod(GregorianCalendar beginning, GregorianCalendar ending) throws ClimateException, NameException, DateException, ConnectionException {
        ArrayList<AstroBody> astroBodiesDates = new ArrayList<>();

        try {
            Connection connection = SingletonConnexion.getInstance();

            String sqlInstruction = "select a.AstroId, a.Name as 'astroName', a.Climate, t.Name as 'typeName', e.Name as 'exploName', e.BirthDate, e.ExplorerId, e.IsAlive, s.ScientificName, s.VernacularName, s.IsExtinct " +
                    "from astronomicalbody a, astronomicaltype t, explorer e, specie s " +
                    "where a.Type = t.TypeId and a.FirstKnownExplorer = e.ExplorerId and e.Specie = s.ScientificName and a.FirstExplorationDate " +
                    "between ? and ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            java.sql.Date beg = new java.sql.Date(beginning.getTimeInMillis());
            java.sql.Date end = new java.sql.Date(ending.getTimeInMillis());
            preparedStatement.setDate(1, beg);
            preparedStatement.setDate(2, end);
            ResultSet data = preparedStatement.executeQuery();


            while (data.next()) {
                if (!data.wasNull()) {

                    GregorianCalendar birthDate = new GregorianCalendar();
                    if(data.getDate("BirthDate") != null) birthDate.setTime(data.getDate("BirthDate"));
                    else birthDate = null;


                    AstroBody astroBodyDate = new AstroBody(
                            data.getInt("AstroId"),
                            data.getString("astroName"),
                            new Explorer(
                                    data.getInt("ExplorerId"),
                                    data.getString("exploName"),
                                    data.getBoolean("IsAlive"),
                                    birthDate,
                                    new Specie(
                                            data.getString("ScientificName"),
                                            data.getString("VernacularName"),
                                            null,
                                            data.getBoolean("IsExtinct")
                                    )
                            ),
                            new AstroType(999, data.getString("typeName")),
                            data.getString("climate"),
                            null,
                            null,
                            null
                    );
                    astroBodiesDates.add(astroBodyDate);
                }
            }
        } catch (TypeException | GravityException | SQLException | IdException | TypeIDException e) {
            System.out.println(e);
            throw new DateException();
        }
        return astroBodiesDates;
    }

}
