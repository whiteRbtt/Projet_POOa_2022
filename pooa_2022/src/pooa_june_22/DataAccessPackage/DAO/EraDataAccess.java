package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.AllEraException;
import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.DateException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ModelPackage.Era;

import java.util.ArrayList;

public interface EraDataAccess {
    ArrayList<Era>getAllEras() throws AllEraException, ConnectionException, NameException, DateException;
}
