package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;
import pooa_june_22.ModelPackage.ResearchedAstroBodies;
import pooa_june_22.ModelPackage.ResearchedAstroBodiesDate;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface AstroBodyDataAccess {

    public void deleteAstroBody(int astroID) throws GeneralException;

    public void addAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException;

    public void updateAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException, UpdateAstroBodyException;

    public ArrayList<AstroBody> getAllAstroBodies() throws GeneralException;

    public ArrayList<ResearchedAstroBodies> getAstroBodiesForType(String type) throws ConnectionException, ClimateException, NameException, DateException, IdException, GravityException, TypeException;

    public ArrayList<ResearchedAstroBodiesDate> getAstroBodiesForPeriod(GregorianCalendar beginning, GregorianCalendar ending) throws ClimateException, NameException, DateException, ConnectionException;

    public int getMaxId() throws ConnectionException, IdException;
}
