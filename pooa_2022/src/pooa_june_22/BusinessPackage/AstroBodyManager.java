package pooa_june_22.BusinessPackage;

import pooa_june_22.DataAccessPackage.AstroDBAccess;
import pooa_june_22.DataAccessPackage.DAO.AstroBodyDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;

import java.util.ArrayList;

public class AstroBodyManager {
    AstroBodyDataAccess dao;

    public AstroBodyManager() {
        setDao(new AstroDBAccess());
    }

    public void setDao(AstroBodyDataAccess dao) {
        this.dao = dao;
    }

    public void deleteAstroBody(int astroID) throws ConnectionException, DeleteAstroBodyException, GeneralException {
        dao.deleteAstroBody(astroID);
    }

    public void addAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException {
        dao.addAstroBody(astroBody);
    }

    public void updateAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException {
        dao.updateAstroBody(astroBody);
    }

    public ArrayList<AstroBody> getAllAstroBodies() throws ConnectionException, ClimateException, IdException, TypeException, NameException, AllAstroBodiesException, DateException, GravityException, GeneralException {
        return dao.getAllAstroBodies();
    }

}
