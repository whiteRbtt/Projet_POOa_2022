package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Era;
import pooa_june_22.ModelPackage.Specie;

import java.util.ArrayList;

public interface SpecieDataAccess {
    public int getNbColonyForSpecie(String vernacularName) throws ConnectionException, ColonyException;
    public ArrayList<Era> getBestEras(String vernacularName) throws DateException, AllEraException, ConnectionException;
    public ArrayList<Specie> getAllSpecies() throws AllSpeciesException, ConnectionException, NameException;
    public ArrayList<Integer> getGravityOfColony(String vernacularName) throws ConnectionException, GravityException;
}
