package pooa_june_22.BusinessPackage;

import pooa_june_22.DataAccessPackage.DAO.AstroTypeDBAccess;
import pooa_june_22.DataAccessPackage.TypeDBAccess;
import pooa_june_22.ExceptionPackage.*;
import pooa_june_22.ModelPackage.AstroType;

import java.util.ArrayList;

public class TypeManager {
    AstroTypeDBAccess dao;

    public TypeManager(){
        setDao(new TypeDBAccess());
    }
    public ArrayList<AstroType> getAllTypes() throws ConnectionException, NameException, TypeException, AllTypesException, TypeIDException {
        return dao.getAllTypes();
    }
    public void setDao(AstroTypeDBAccess dao) {
        this.dao = dao;
    }
}
