package pooa_june_22.BusinessPackage;

import pooa_june_22.DataAccessPackage.DAO.SpecieDataAccess;
import pooa_june_22.DataAccessPackage.SpecieDBAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Era;
import pooa_june_22.ModelPackage.Specie;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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

    public String bestSpecie(String vernacularName1, String vernacularName2) throws DateException, ConnectionException, AllEraException, ColonyException {
        double lifeSpan1 = lifeSpan(vernacularName1);
        double lifeSpan2 = lifeSpan(vernacularName2);
        int nbColony1 = getNbColonyForSpecie(vernacularName1);
        int nbColony2 = getNbColonyForSpecie(vernacularName2);
        double ratio1 = lifeSpan1*0.35 + nbColony1*0.74;
        double ratio2 = lifeSpan2*0.35 + nbColony2*0.74;

        return (ratio1 > ratio2 ? vernacularName1 : vernacularName2);
    }

    public int getNbColonyForSpecie(String vernacularName) throws ConnectionException, ColonyException {
        return dao.getNbColonyForSpecie(vernacularName);
    }
    public ArrayList<Era> getBestEras(String vernacularName) throws DateException, AllEraException, ConnectionException {
        return dao.getBestEras(vernacularName);
    }
    public double lifeSpan(String vernacularName) throws DateException, AllEraException, ConnectionException {
        ArrayList<Era> eras = getBestEras(vernacularName);
        double lifeSpan = 0;
        for(Era era : eras){
            long diff;
            if(era.getEnding()!=null){
                diff = era.getEnding().getTimeInMillis() - era.getBeginning().getTimeInMillis();
            }else{
                GregorianCalendar calendar = new GregorianCalendar(10000, 01,01);
                diff = calendar.getTimeInMillis() - era.getBeginning().getTimeInMillis();
            }

            float res = (diff / (24*60*60*1000));
            lifeSpan += res;

        }
        return lifeSpan/eras.size();
    }
}
