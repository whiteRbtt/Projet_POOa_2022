package pooa_june_22.BusinessPackage;

import pooa_june_22.DataAccessPackage.ColonyDBAccess;
import pooa_june_22.DataAccessPackage.DAO.ColonyDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.ResearchedColonies;

import java.util.ArrayList;

public class ColonyManager {
    ColonyDataAccess dao;

    public ColonyManager() {
        setDao(new ColonyDBAccess());
    }

    public ArrayList<ResearchedColonies> getColonies(String specie) throws ConnectionException, ColonyException, NameException, DateException, AllColoniesException {
        return (dao.getColonies(specie));
    }

    public void setDao(ColonyDataAccess dao) {
        this.dao = dao;
    }


}
