package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.NameException;
import pooa_june_22.ExceptionPackage.TypeException;

public class AstroType {
    private Integer typeID;
    private String name;

    public AstroType(Integer typeID, String name) throws TypeException, NameException {
        setTypeID(typeID);
        setName(name);
    }

    public Integer getTypeID() {
        return typeID;
    }

    public String getName(){;return name;
    }

    public void setTypeID(Integer typeID) throws TypeException {
        if(typeID != null && typeID > 0){
            this.typeID = typeID;
        }else{
            throw new TypeException(typeID);
        }
    }

    public void setName(String name) throws NameException {
        if(name != null && name.length() < 45){
            this.name = name;
        }else{
            throw new NameException(name);
        }
    }
}
