package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Colony;

import java.util.ArrayList;

public interface ColonyDataAccess {
    ArrayList<Colony> getColonies(String specie) throws ConnectionException, NameException, ColonyException, DateException, AllColoniesException;
}
