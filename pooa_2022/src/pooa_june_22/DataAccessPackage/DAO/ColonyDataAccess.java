package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.ColonyException;
import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ModelPackage.Colony;
import pooa_june_22.ModelPackage.ResearchedColonies;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface ColonyDataAccess {
    public ArrayList<ResearchedColonies> getColonies(String specie) throws ConnectionException, NameException, ColonyException;
}
