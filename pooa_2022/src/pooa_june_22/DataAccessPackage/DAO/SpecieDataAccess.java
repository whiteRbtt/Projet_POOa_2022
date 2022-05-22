package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.Era;
import pooa_june_22.ModelPackage.Specie;

import java.util.ArrayList;

public interface SpecieDataAccess {
    int getNbColonyForSpecie(String vernacularName) throws ConnectionException, ColonyException;

    ArrayList<Era> getBestEras(String vernacularName) throws DateException, AllEraException, ConnectionException;

    ArrayList<Specie> getAllSpecies() throws AllSpeciesException, ConnectionException, NameException;

    ArrayList<Integer> getGravityOfColony(String vernacularName) throws ConnectionException, GravityException;
}
