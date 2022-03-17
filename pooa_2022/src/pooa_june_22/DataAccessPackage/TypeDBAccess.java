package pooa_june_22.DataAccessPackage;

import pooa_june_22.DataAccessPackage.DAO.AstroTypeDBAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeDBAccess implements AstroTypeDBAccess {
    public ArrayList<AstroType> getAllTypes() throws ConnectionException, NameException, AllTypesException, TypeIDException {
        ArrayList<AstroType> types = new ArrayList<>();
        try{
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "select * from astronomicaltype";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            while(data.next()){

                AstroType type = new AstroType(
                        data.getInt("TypeId"),
                        data.getString("Name")
                );
                types.add(type);
            }

        } catch (SQLException e) {
            throw new AllTypesException();
        }

        return types;
    }
}
