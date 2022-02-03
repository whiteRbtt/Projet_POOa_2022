package pooa_june_22.BusinessPackage;

import pooa_june_22.DataAccessPackage.ColonyDBAccess;
import pooa_june_22.DataAccessPackage.DAO.ColonyDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Colony;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ColonyManager {
    ColonyDataAccess dao;

    public ColonyManager() {
        setDao(new ColonyDBAccess());
    }

    public ArrayList<Colony> getColonies(GregorianCalendar date, String specie) throws ConnectionException, ColonyException, NameException, AllEraException, DateException {
        return (dao.getColonies(date, specie));
    }

    public void setDao(ColonyDataAccess dao) {
        this.dao = dao;
    }
}
