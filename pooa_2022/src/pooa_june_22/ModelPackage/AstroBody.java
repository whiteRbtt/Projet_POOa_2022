package pooa_june_22.ModelPackage;

import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import pooa_june_22.ExceptionPackage.*;

public class AstroBody {

    private Integer astroId;
    private String name;
    private Explorer firstExplorer;
    private AstroType type;
    private String climate;
    private Integer gravity;
    private Boolean hasLifeform;
    private GregorianCalendar firstExploDate;

    public AstroBody(Integer astroId, String name, Explorer firstExplorer, AstroType type, String climate, Integer gravity, Boolean hasLifeform, GregorianCalendar firstExploDate) throws IdException, NameException, TypeException, ClimateException, GravityException, DateException, TypeIDException {
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
        if (name != null && Pattern.matches("^[A-zÀ-ú\\\\s' )(]+", name) && !Pattern.matches(" *", name) && name.length() < 45)
            this.name = name;
        else {
            throw new NameException(name);
        }
    }

    public void setFirstExplorer(Explorer firstExplorer) {
        this.firstExplorer = firstExplorer;
    }

    public void setType(AstroType type) throws TypeIDException {
        if (type != null && type.getTypeID() > 0)
            this.type = type;
        else {
            throw new TypeIDException(type.getTypeID());
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

    public Explorer getFirstExplorer() {
        return firstExplorer;
    }

    public AstroType getType() {
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

    public String getFirstExploDateInString() {
        if (firstExploDate != null) {
            return firstExploDate.get(firstExploDate.DAY_OF_MONTH) + "/" + (firstExploDate.get(firstExploDate.MONTH) + 1) + "/" + firstExploDate.get(firstExploDate.YEAR);
        } else {
            return "inconnu";
        }
    }

}
