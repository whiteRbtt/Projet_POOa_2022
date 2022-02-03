package pooa_june_22.ModelPackage;

import java.util.GregorianCalendar;

import pooa_june_22.ExceptionPackage.*;

public class AstroBody {

    private Integer astroId;
    private String name;
    private Integer firstExplorer;
    private String type;
    private String climate;
    private Integer gravity;
    private Boolean hasLifeform;
    private GregorianCalendar firstExploDate;

    public AstroBody(Integer astroId, String name, Integer firstExplorer, String type, String climate, Integer gravity, Boolean hasLifeform, GregorianCalendar firstExploDate) throws IdException, NameException, TypeException, ClimateException, GravityException, DateException {
        setAstroId(astroId);
        setName(name);
        setFirstExplorer(firstExplorer);
        setType(type);
        setClimate(climate);
        setGravity(gravity);
        setHasLifeform(hasLifeform);
        setFirstExploDate(firstExploDate);
    }

    // setters

    public void setAstroId(Integer astroId) throws IdException {
        if (astroId != null && astroId > 0)
            this.astroId = astroId;
        else {
            throw new IdException(astroId);
        }
    }

    public void setName(String name) throws NameException {
        if (name != null && name.length() < 45)
            this.name = name;
        else {
            throw new NameException(name);
        }
    }

    public void setFirstExplorer(Integer firstExplorer) {
        this.firstExplorer = firstExplorer;
    }

    public void setType(String type) throws TypeException {
        if (type != null && type.length() < 45)
            this.type = type;
        else {
            throw new TypeException(type);
        }
    }

    public void setClimate(String climate) throws ClimateException {
        if (climate == null || climate.length() < 45)
            this.climate = climate;
        else {
            throw new ClimateException(climate);
        }
    }

    public void setGravity(Integer gravity) throws GravityException {
        if (gravity == null || gravity > 0)
            this.gravity = gravity;
        else {
            throw new GravityException(gravity);
        }
    }

    public void setHasLifeform(Boolean hasLifeform) {
        this.hasLifeform = hasLifeform;
    }

    public void setFirstExploDate(GregorianCalendar firstExploDate) throws DateException {
        if (firstExploDate == null || (firstExploDate.get(firstExploDate.YEAR) >= 1000 && firstExploDate.get(firstExploDate.YEAR) <= 9999))
            this.firstExploDate = firstExploDate;
        else {
            throw new DateException();
        }
    }

    // getters

    public Integer getAstroId() {
        return astroId;
    }

    public String getName() {
        return name;
    }

    public Integer getFirstExplorer() {
        return firstExplorer;
    }

    public String getType() {
        return type;
    }

    public String getClimate() {
        return climate;
    }

    public Integer getGravity() {
        return gravity;
    }

    public Boolean getHasLifeform() {
        return hasLifeform;
    }

    public GregorianCalendar getFirstExploDate() {
        return firstExploDate;
    }

}
