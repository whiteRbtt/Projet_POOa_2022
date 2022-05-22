package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.*;

import java.util.GregorianCalendar;

public class ResearchedAstroBodies {
    private String specie;
    private String explorer;
    private GregorianCalendar firstExploDate;
    private Integer gravity;
    private String climate;
    private Integer astroId;
    private String astroName;

    public ResearchedAstroBodies(String specie, String explorer, GregorianCalendar firstExploDate, Integer gravity, String climate, Integer astroId, String astroName) throws NameException, GravityException, DateException, IdException, ClimateException {
        setAstroId(astroId);
        setClimate(climate);
        setExplorer(explorer);
        setGravity(gravity);
        setFirstExploDate(firstExploDate);
        setAstroName(astroName);
        setSpecie(specie);
    }

    // Getters
    public String getSpecie() {
        return specie;
    }

    public String getFirstExploDateInString() {
        if (firstExploDate != null) {
            return firstExploDate.get(firstExploDate.DAY_OF_MONTH) + "/" + (firstExploDate.get(firstExploDate.MONTH) + 1) + "/" + firstExploDate.get(firstExploDate.YEAR);
        } else {
            return "inconnu";
        }
    }

    public String getExplorer() {
        return explorer;
    }

    public Integer getGravity() {
        return gravity;
    }

    public String getClimate() {
        return climate;
    }

    public int getAstroId() {
        return astroId;
    }

    public String getAstroName() {
        return astroName;
    }

    // Setters
    public void setSpecie(String specie) throws NameException {
        if (specie != null && specie.length() < 45)
            this.specie = specie;
        else {
            throw new NameException(specie);
        }
    }

    public void setFirstExploDate(GregorianCalendar firstExploDate) throws DateException {
        if (firstExploDate == null || (firstExploDate.get(firstExploDate.YEAR) >= 1000 && firstExploDate.get(firstExploDate.YEAR) <= 9999))
            this.firstExploDate = firstExploDate;
        else {
            throw new DateException();
        }
    }

    public void setExplorer(String explorer) throws NameException {
        if (explorer == null || explorer.length() < 45)
            this.explorer = explorer;
        else {
            throw new NameException(explorer);
        }
    }

    public void setGravity(Integer gravity) throws GravityException {
        if (gravity == null || gravity > 0)
            this.gravity = gravity;
        else {
            throw new GravityException(gravity);
        }
    }

    public void setClimate(String climate) throws ClimateException {
        if (climate == null || climate.length() < 45)
            this.climate = climate;
        else {
            throw new ClimateException(climate);
        }
    }

    public void setAstroId(Integer astroId) throws IdException {
        if (astroId != null && astroId > 0)
            this.astroId = astroId;
        else {
            throw new IdException(astroId);
        }
    }

    public void setAstroName(String astroName) throws NameException {
        if (astroName != null && astroName.length() < 45)
            this.astroName = astroName;
        else {
            throw new NameException(astroName);
        }
    }
}
