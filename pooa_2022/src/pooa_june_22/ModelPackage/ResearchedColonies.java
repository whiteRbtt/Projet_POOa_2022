package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.DateException;
import pooa_june_22.ExceptionPackage.NameException;

import java.util.GregorianCalendar;

public class ResearchedColonies {
    private String eraName;
    private GregorianCalendar eraBeginning;
    private GregorianCalendar eraEnding;
    private String astroName;
    private String tribalName;

    public ResearchedColonies(String eraName, GregorianCalendar eraBeginning, GregorianCalendar eraEnding, String astroName, String tribalName) throws NameException, DateException {
        setAstroName(astroName);
        setEraBeginning(eraBeginning);
        setEraEnding(eraEnding);
        setEraName(eraName);
        setTribalName(tribalName);
    }

    public void setAstroName(String astroName) throws NameException {
        if (astroName != null && astroName.length() < 45)
            this.astroName = astroName;
        else {
            throw new NameException(astroName);
        }
    }

    public void setEraBeginning(GregorianCalendar eraBeginning) throws DateException {
        if (eraBeginning != null && (eraBeginning.get(eraBeginning.YEAR) >= 1000 && eraBeginning.get(eraBeginning.YEAR) <= 9999))
            this.eraBeginning = eraBeginning;
        else {
            throw new DateException();
        }
    }

    public void setEraEnding(GregorianCalendar eraEnding) throws DateException {
        if (eraEnding == null || (eraEnding.get(eraEnding.YEAR) >= 1000 && eraEnding.get(eraEnding.YEAR) <= 9999))
            this.eraEnding = eraEnding;
        else {
            throw new DateException();
        }
    }

    public void setEraName(String eraName) throws NameException {
        if (eraName != null && eraName.length() < 45)
            this.eraName = eraName;
        else {
            throw new NameException(eraName);
        }
    }

    public void setTribalName(String tribalName) throws NameException {
        if (tribalName == null || tribalName.length() < 45)
            this.tribalName = tribalName;
        else {
            throw new NameException(tribalName);
        }
    }

    public String getAstroName() {
        return astroName;
    }

    public String getEraName() {
        return eraName;
    }

    public String getTribalName() {
        return tribalName;
    }

    public String getEraBeginningInString() {
        return eraBeginning.get(eraBeginning.DAY_OF_MONTH) +"/" + (eraBeginning.get(eraBeginning.MONTH)+1) +"/" + eraBeginning.get(eraBeginning.YEAR);
    }

    public String getEraEndingInString() {
        if(eraEnding != null) {
            return eraEnding.get(eraEnding.DAY_OF_MONTH) + "/" + (eraEnding.get(eraBeginning.MONTH) + 1) + "/" + eraEnding.get(eraEnding.YEAR);
        }else{
            return "inconnu";
        }
    }
}
