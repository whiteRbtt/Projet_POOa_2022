package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.ClimateException;
import pooa_june_22.ExceptionPackage.DateException;
import pooa_june_22.ExceptionPackage.NameException;

import java.util.GregorianCalendar;

public class ResearchedAstroBodiesDate {
    private String astroName;
    private String astroClimate;
    private String typeName;
    private String exploName;
    private GregorianCalendar exploBirth;
    private String specieSName;
    private String specieVName;
    private Boolean isExtinct;

    public ResearchedAstroBodiesDate(String astroName, String astroClimate, String typeName, String exploName, GregorianCalendar exploBirth, String specieSName, String specieVName, Boolean isExtinct) throws NameException, ClimateException, DateException {
        setAstroName(astroName);
        setAstroClimate(astroClimate);
        setTypeName(typeName);
        setExploName(exploName);
        setExploBirth(exploBirth);
        setSpecieSName(specieSName);
        setSpecieVName(specieVName);
        setExtinct(isExtinct);
    }

    // Getters
    public String getAstroName() {
        return astroName;
    }

    public String getExploBirthInString() {
        if(exploBirth != null){
            return exploBirth.get(exploBirth.DAY_OF_MONTH) + "/" + (exploBirth.get(exploBirth.MONTH)+1) + "/" + exploBirth.get(exploBirth.YEAR);
        }else{
            return "inconnu";
        }
    }

    public String getAstroClimate() {
        return astroClimate;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getExploName() {
        return exploName;
    }

    public String getSpecieSName() {
        return specieSName;
    }

    public Boolean isExtinct() {
        return isExtinct;
    }

    public String getSpecieVName() {
        return specieVName;
    }

    // Setters
    public void setAstroName(String astroName) throws NameException {
        if (astroName != null && astroName.length() < 45)
            this.astroName = astroName;
        else {
            throw new NameException(astroName);
        }
    }

    public void setAstroClimate(String astroClimate) throws ClimateException {
        if (astroClimate == null || astroClimate.length() < 45)
            this.astroClimate = astroClimate;
        else {
            throw new ClimateException(astroClimate);
        }
    }

    public void setTypeName(String typeName) throws NameException {
        if (typeName != null && typeName.length() < 45)
            this.typeName = typeName;
        else {
            throw new NameException(typeName);
        }
    }

    public void setExploName(String exploName) throws NameException {
        if (exploName == null || exploName.length() < 45)
            this.exploName = exploName;
        else {
            throw new NameException(exploName);
        }
    }

    public void setExploBirth(GregorianCalendar exploBirth) throws DateException {
        if (exploBirth == null || (exploBirth.get(exploBirth.YEAR) >= 1000 && exploBirth.get(exploBirth.YEAR) <= 9999))
            this.exploBirth = exploBirth;
        else {
            throw new DateException();
        }
    }

    public void setSpecieSName(String specieSName) throws NameException {
        if (specieSName == null || specieSName.length() < 45)
            this.specieSName = specieSName;
        else {
            throw new NameException(specieSName);
        }
    }

    public void setSpecieVName(String specieVName) throws NameException {
        if (specieVName == null || specieVName.length() < 45)
            this.specieVName = specieVName;
        else {
            throw new NameException(specieVName);
        }
    }

    public void setExtinct(Boolean extinct) {
        isExtinct = extinct;
    }
}
