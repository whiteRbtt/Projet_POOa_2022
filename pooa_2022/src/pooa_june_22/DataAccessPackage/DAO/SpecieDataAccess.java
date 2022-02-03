package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.AllSpeciesException;
import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ModelPackage.Specie;

import java.util.ArrayList;

public interface SpecieDataAccess {
    public ArrayList<Specie> getAllSpecies() throws AllSpeciesException, ConnectionException, NameException;
}
