package pooa_june_22.BusinessPackage;

import pooa_june_22.DataAccessPackage.DAO.SpecieDataAccess;
import pooa_june_22.DataAccessPackage.SpecieDBAccess;
import pooa_june_22.ExceptionPackage.AllSpeciesException;
import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ModelPackage.Specie;

import java.util.ArrayList;

public class SpecieManager {
    SpecieDataAccess dao;

    public SpecieManager() {
        setDao(new SpecieDBAccess());
    }

    public ArrayList<Specie> getAllSpecies() throws ConnectionException, AllSpeciesException, NameException {
        return dao.getAllSpecies();
    }

    public void setDao(SpecieDBAccess dao) {
        this.dao = dao;
    }
}
