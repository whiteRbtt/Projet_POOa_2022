package pooa_june_22.DataAccessPackage.DAO;

import pooa_june_22.ExceptionPackage.ColonyException;
import pooa_june_22.ExceptionPackage.ConnectionException;
import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ModelPackage.Colony;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface ColonyDataAccess {
    public ArrayList<Colony> getColonies(GregorianCalendar date, String specie) throws ConnectionException, NameException, ColonyException;
}
