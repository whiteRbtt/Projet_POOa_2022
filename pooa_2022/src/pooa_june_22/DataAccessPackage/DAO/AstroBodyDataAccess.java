package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;
import pooa_june_22.ModelPackage.ResearchedAstroBodies;
import pooa_june_22.ModelPackage.ResearchedAstroBodiesDate;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface AstroBodyDataAccess {

    public void deleteAstroBody(int astroID) throws ConnectionException, DeleteAstroBodyException, GeneralException;

    public void addAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException;

    public void updateAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException;

    public ArrayList<AstroBody> getAllAstroBodies() throws AllAstroBodiesException, ClimateException, TypeException, GravityException, NameException, DateException, IdException, ConnectionException, GeneralException;

    public ArrayList<ResearchedAstroBodies> getAstroBodiesForType(String type) throws ConnectionException, ClimateException, NameException, DateException, IdException, GravityException;

    public ArrayList<ResearchedAstroBodiesDate> getAstroBodiesForPeriod(GregorianCalendar beginning, GregorianCalendar ending) throws ClimateException, NameException, DateException, ConnectionException;
}
