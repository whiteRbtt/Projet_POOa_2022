package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.AstroBodyDataAccess;
import pooa_june_22.DataAccessPackage.DAO.ExplorerDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.*;
import pooa_june_22.DataAccessPackage.ExplorerDBAccess;
import pooa_june_22.ViewPackage.ResearchColonieModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
public class AstroDBAccess implements AstroBodyDataAccess {

    public int getMaxId() throws ConnectionException, IdException {
        String instructionSQL;
        Connection connection;
        int id = 0;
        try{
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

    public void deleteColonyForAstroBody(int location) throws ConnectionException, SQLException, DeleteAstroBodyException {
        String sqlInstruction;
        Connection connection;
            sqlInstruction = "delete from colony where Location = ?";
            connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, location);
            preparedStatement.executeUpdate();
    }
    public void deleteAstroBody(int astroID) throws ConnectionException, DeleteAstroBodyException {
        String sqlInstruction;
        Connection connection;
        try{
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
           explorer.setSpecie(data.getString("Specie"));
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
   public AstroType getTypeForAstroBody(Integer type) throws NameException, TypeException, ConnectionException, SQLException, TypeIDException {
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

    public ArrayList<ResearchedAstroBodies> getAstroBodiesForType(String type) throws ConnectionException, ClimateException, NameException, DateException, IdException, GravityException, TypeException {
        ArrayList<ResearchedAstroBodies> astroBodies = new ArrayList<>();

        try{
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "select s.VernacularName, e.Name as 'exploName', a.FirstExplorationDate, a.Gravity, a.Climate, a.AstroId, a.Name from astronomicaltype t, specie s, explorer e, astronomicalbody a " +
                    "where t.TypeId = a.Type and a.FirstKnownExplorer = e.ExplorerId and e.Specie = s.ScientificName and t.Name = ?" ;

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, type);
            ResultSet data = preparedStatement.executeQuery();
            java.sql.Date sqlDate;
            while(data.next()){
                GregorianCalendar calendar = new GregorianCalendar();
                ResearchedAstroBodies astroBody = new ResearchedAstroBodies(data.getString("VernacularName"), null, null, null, null, data.getInt("AstroId"), data.getString("Name"));
                sqlDate = data.getDate("FirstExplorationDate");
                if(!data.wasNull()){
                    calendar.setTime(sqlDate);
                    astroBody.setFirstExploDate(calendar);
                }

                String exploName = data.getString("exploName");

                if(!data.wasNull()){
                    astroBody.setExplorer(exploName);
                }

                Integer gravity = data.getInt("Gravity");
                if(!data.wasNull()){
                    astroBody.setGravity(gravity);
                }

                String climate = data.getString("Climate");
                if(!data.wasNull()){
                    astroBody.setClimate(climate);
                }
                astroBodies.add(astroBody);
            }
        }catch (SQLException e) {
            throw new TypeException(type);
        }
        return astroBodies;
    }

    public ArrayList<ResearchedAstroBodiesDate> getAstroBodiesForPeriod(GregorianCalendar beginning, GregorianCalendar ending) throws ClimateException, NameException, DateException, ConnectionException {
        ArrayList<ResearchedAstroBodiesDate> astroBodiesDates = new ArrayList<>();

        try{
            Connection connection = SingletonConnexion.getInstance();

            String sqlInstruction = "select a.Name as 'astroName', a.Climate, t.Name as 'typeName', e.Name as 'exploName', e.BirthDate, s.ScientificName, s.VernacularName, s.IsExtinct from astronomicalbody a, astronomicaltype t, explorer e, specie s where a.Type = t.TypeId and a.FirstKnownExplorer = e.ExplorerId and e.Specie = s.ScientificName and a.FirstExplorationDate between ? and ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            java.sql.Date beg = new java.sql.Date(beginning.getTimeInMillis());
            java.sql.Date end = new java.sql.Date(ending.getTimeInMillis());
            preparedStatement.setDate(1, beg);
            preparedStatement.setDate(2, end);
            ResultSet data = preparedStatement.executeQuery();
            String exploName;
            java.sql.Date sqlDate1;
            String specieSName;
            String specieVName;
            Boolean isExtinct;
            while(data.next()){
                GregorianCalendar birthDate = new GregorianCalendar();
                ResearchedAstroBodiesDate astroBodyDate = new ResearchedAstroBodiesDate(data.getString("astroName"), data.getString("Climate"), data.getString("typeName"), null, null, null, null, null);
                exploName = data.getString("exploName");
                if(!data.wasNull()){
                    astroBodyDate.setExploName(exploName);
                    sqlDate1 = data.getDate("BirthDate");
                    if(!data.wasNull()){
                        birthDate.setTime(sqlDate1);
                        astroBodyDate.setExploBirth(birthDate);
                    }
                    specieSName = data.getString("ScientificName");
                    astroBodyDate.setSpecieSName(specieSName);

                    specieVName = data.getString("VernacularName");
                    astroBodyDate.setSpecieVName(specieVName);

                    isExtinct = data.getBoolean("IsExtinct");
                    astroBodyDate.setExtinct(isExtinct);
                }
                astroBodiesDates.add(astroBodyDate);
            }
        } catch (SQLException e) {
            throw new DateException();
        }
        return astroBodiesDates;
    }

}
