package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ExceptionPackage.TypeIDException;

public class AstroType {
    private Integer typeID;
    private String name;

    public AstroType(Integer typeID, String name) throws NameException, TypeIDException {
        setTypeID(typeID);
        setName(name);
    }

    // getters
    public Integer getTypeID() {
        return typeID;
    }

    public String getName() {
        return name;
    }

    //setters
    public void setTypeID(Integer typeID) throws TypeIDException {
        if (typeID != null && typeID > 0) {
            this.typeID = typeID;
        } else {
            throw new TypeIDException(typeID);
        }
    }

    public void setName(String name) throws NameException {
        if (name != null && name.length() < 45) {
            this.name = name;
        } else {
            throw new NameException(name);
        }
    }
}
