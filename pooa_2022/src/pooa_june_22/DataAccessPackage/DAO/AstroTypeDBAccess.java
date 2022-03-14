package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ExceptionPackage.TypeException;
import pooa_june_22.ModelPackage.AstroType;

import java.util.ArrayList;

public interface AstroTypeDBAccess {

    public ArrayList<AstroType> getAllTypes() throws ConnectionException, NameException, TypeException;
}
