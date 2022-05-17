package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.ResearchedColonies;

import java.util.ArrayList;

public interface ColonyDataAccess {
    public ArrayList<ResearchedColonies> getColonies(String specie) throws ConnectionException, NameException, ColonyException, DateException, AllColoniesException;
}
