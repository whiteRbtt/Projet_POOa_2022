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

    public void setDao(EraDataAccess dao) {
        this.dao = dao;
    }

    public ArrayList<Era> getAllEras() throws AllEraException, NameException, DateException, ConnectionException {
        return dao.getAllEras();
    }
}
