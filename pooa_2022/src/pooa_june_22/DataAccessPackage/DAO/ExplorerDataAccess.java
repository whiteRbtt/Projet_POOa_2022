package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Explorer;

import java.util.ArrayList;

public interface ExplorerDataAccess {
    ArrayList<Explorer> getAllExplorers() throws IdException, DateException, NameException, AllExplorersException, ConnectionException;
}
