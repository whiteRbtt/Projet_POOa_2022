package pooa_june_22.BusinessPackage;

import pooa_june_22.DataAccessPackage.AstroDBAccess;
import pooa_june_22.DataAccessPackage.DAO.AstroBodyDataAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroBody;
import pooa_june_22.ModelPackage.ResearchedAstroBodies;
import pooa_june_22.ModelPackage.ResearchedAstroBodiesDate;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AstroBodyManager {
    AstroBodyDataAccess dao;

    public AstroBodyManager() {
        setDao(new AstroDBAccess());
    }

    public void setDao(AstroBodyDataAccess dao) {
        this.dao = dao;
    }

    //-----------------------------------MethodDBAccess-----------------------------------
    public void deleteAstroBody(int astroID) throws  GeneralException {
        dao.deleteAstroBody(astroID);
    }

    public void addAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException {
        dao.addAstroBody(astroBody);
    }

    public void updateAstroBody(AstroBody astroBody) throws ConnectionException, AddAstroBodyException, UpdateAstroBodyException {
        dao.updateAstroBody(astroBody);
    }

    public ArrayList<AstroBody> getAllAstroBodies() throws GeneralException {
        return dao.getAllAstroBodies();
    }
    public ArrayList<ResearchedAstroBodies> getAstroBodiesForType(String type) throws ConnectionException, ClimateException, NameException, DateException, IdException, GravityException, TypeException {
        return dao.getAstroBodiesForType(type);
    }

    public ArrayList<ResearchedAstroBodiesDate> getAstroBodiesForPeriod(GregorianCalendar beginning, GregorianCalendar ending) throws ClimateException, NameException, DateException, ConnectionException{
        return dao.getAstroBodiesForPeriod(beginning, ending);
    }

    public int getMaxId() throws ConnectionException, IdException{
        return dao.getMaxId();
    }

    //-----------------------------------MethodBusiness-----------------------------------
    public double getGravityAverage(String type) throws ClimateException, NameException, DateException, TypeException, IdException, ConnectionException, GravityException, GravityAverageException {
        ArrayList<ResearchedAstroBodies> astroBodies = dao.getAstroBodiesForType(type);
        if(astroBodies.size() != 0){
            double average = 0;
            for(ResearchedAstroBodies a : astroBodies){
                if(a.getGravity() != null)
                    average += a.getGravity();
            }
            if(average != 0){
                return average/astroBodies.size();
            }else{
                throw new GravityAverageException();
            }
        }
        else{
            throw new GravityAverageException();
        }
    }

}
