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

    //-----------------------------------Use DAO's methods-----------------------------------
    public int getNbColonyForSpecie(String vernacularName) throws ConnectionException, ColonyException {
        return dao.getNbColonyForSpecie(vernacularName);
    }

    public ArrayList<Era> getBestEras(String vernacularName) throws DateException, AllEraException, ConnectionException {
        return dao.getBestEras(vernacularName);
    }

    public ArrayList<Integer> getGravityOfColony(String vernacularName) throws ConnectionException, GravityException {
        return dao.getGravityOfColony(vernacularName);
    }

    //-----------------------------------Business tasks-----------------------------------
    public String bestSpecie(String vernacularName1, String vernacularName2) throws DateException, ConnectionException, AllEraException, ColonyException, GravityException {
        double ratio1 = calcMetaScore(lifeSpan(vernacularName1), getNbColonyForSpecie(vernacularName1), gravityAverage(vernacularName1));
        double ratio2 = calcMetaScore(lifeSpan(vernacularName2), getNbColonyForSpecie(vernacularName2), gravityAverage(vernacularName2));
        return (ratio1 > ratio2 ? vernacularName1 : (ratio1 == ratio2 ? "égalité" : vernacularName2));
    }

    public double lifeSpan(String vernacularName) throws DateException, AllEraException, ConnectionException {
        ArrayList<Era> eras = getBestEras(vernacularName);
        double lifeSpan = 0;
        for (Era era : eras) {
            long diff;
            if (era.getEnding() != null) {
                diff = diffDate(era.getBeginning(), era.getEnding());
            } else {
                GregorianCalendar calendar = new GregorianCalendar(10000, 01, 01);
                diff = diffDate(era.getBeginning(), calendar);
            }

            double res = conversion(diff);
            lifeSpan += res;

        }
        return lifeSpan / eras.size();
    }

    //-----------------------------------Calculation (eligible to JUnit)-----------------------------------
    public double calcMetaScore(double lifeSpan, int nbColony, double average) {
        return lifeSpan * 0.35 + nbColony * 0.40 + average * 0.45;
    }

    public long diffDate(GregorianCalendar begin, GregorianCalendar ending) {
        return ending.getTimeInMillis() - begin.getTimeInMillis();
    }

    public double conversion(double diff) {
        return (diff / (24 * 60 * 60 * 1000));
    }

    public double calcAverage(ArrayList<Integer> gravities) {
        double average = 0;
        for (Integer gravity : gravities) {
            average += gravity;
        }
        return average / gravities.size();
    }

    public double gravityAverage(String vernacularName) throws ConnectionException, GravityException {
        ArrayList<Integer> gravities = getGravityOfColony(vernacularName);
        return calcAverage(gravities);
    }
}
