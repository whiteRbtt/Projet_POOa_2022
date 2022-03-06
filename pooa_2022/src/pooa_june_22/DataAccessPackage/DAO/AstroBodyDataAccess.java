package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;

import java.util.ArrayList;

public interface AstroBodyDataAccess {

    public void deleteAstroBody(int astroID) throws ConnectionException, DeleteAstroBodyException, GeneralException;

    public void addAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException;

    public void updateAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException;

    public ArrayList<AstroBody> getAllAstroBodies() throws AllAstroBodiesException, ClimateException, TypeException, GravityException, NameException, DateException, IdException, ConnectionException, GeneralException;
}
