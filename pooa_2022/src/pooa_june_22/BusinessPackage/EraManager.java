package pooa_june_22.BusinessPackage;

import pooa_june_22.DataAccessPackage.DAO.EraDataAccess;
import pooa_june_22.DataAccessPackage.EraDBaccess;
import pooa_june_22.ExceptionPackage.AllEraException;
import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.DateException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ModelPackage.Era;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EraManager {
    EraDataAccess dao;

    public EraManager() {
        setDao(new EraDBaccess());
    }


    // -----------------------------------tache metier-----------------------------------
    public Era findEra(GregorianCalendar date) throws ConnectionException, DateException, AllEraException, NameException {
        ArrayList<Era> eras = dao.getAllEras();
        String targetName = null;
        for (Era e : eras) {
            if (date.get(date.YEAR) >= e.getBeginning().get(e.getBeginning().YEAR)) { //TODO pas une vraie fct métier
                return e;
            }
        }
        return null;
    }

    public void setDao(EraDataAccess dao) {
        this.dao = dao;
    }
}
