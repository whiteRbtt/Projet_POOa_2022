package pooa_june_22.BusinessPackage;

import pooa_june_22.DataAccessPackage.DAO.ExplorerDataAccess;
import pooa_june_22.DataAccessPackage.ExplorerDBAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Explorer;

import java.util.ArrayList;

public class ExplorerManager {
    ExplorerDataAccess dao;

    public ExplorerManager() {
        setDao(new ExplorerDBAccess());
    }

    public ArrayList<Explorer> getAllExplorers() throws ConnectionException, AllExplorersException, IdException, DateException, NameException {
        return dao.getAllExplorers();
    }

    public void setDao(ExplorerDataAccess dao) {
        this.dao = dao;
    }
}
