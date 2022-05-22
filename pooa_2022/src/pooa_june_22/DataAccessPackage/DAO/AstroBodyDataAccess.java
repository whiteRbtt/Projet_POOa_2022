package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;
import pooa_june_22.ModelPackage.ResearchedAstroBodiesDate;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface AstroBodyDataAccess {

    void deleteAstroBody(int astroID) throws GeneralException;

    void addAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException;

    void updateAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException, UpdateAstroBodyException;

    ArrayList<AstroBody> getAllAstroBodies() throws GeneralException;

    ArrayList<AstroBody> getAstroBodiesForType(String type) throws ConnectionException, ClimateException, NameException, DateException, IdException, GravityException, TypeException;

    ArrayList<ResearchedAstroBodiesDate> getAstroBodiesForPeriod(GregorianCalendar beginning, GregorianCalendar ending) throws ClimateException, NameException, DateException, ConnectionException;

    int getMaxId() throws ConnectionException, IdException;
}
