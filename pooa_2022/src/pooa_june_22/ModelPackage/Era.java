package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.DateException;
import pooa_june_22.ExceptionPackage.NameException;

import java.util.GregorianCalendar;

public class Era {

    private String name;
    private GregorianCalendar beginning;
    private GregorianCalendar ending;

    public Era(String name, GregorianCalendar beginning, GregorianCalendar ending) throws DateException, NameException {
        setName(name);
        setBeginning(beginning);
        setEnding(ending);
    }

    public Era(String name, GregorianCalendar beginning) throws DateException {
        this.name = name;
        setBeginning(beginning);
    }

    // Setters

    public void setName(String name) throws NameException {
        if (name != null && name.length() < 45)
            this.name = name;
        else {
            throw new NameException(name);
        }
    }

    public void setBeginning(GregorianCalendar beginning) throws DateException {
        if (beginning != null && (beginning.get(beginning.YEAR) >= 1000 && beginning.get(beginning.YEAR) <= 9999))
            this.beginning = beginning;
        else {
            throw new DateException();
        }
    }

    public void setEnding(GregorianCalendar ending) throws DateException {
        if (ending == null || (ending.get(ending.YEAR) >= 1000 && ending.get(ending.YEAR) <= 9999))
            this.ending = ending;
        else {
            throw new DateException();
        }
    }

    // Getters

    public String getName() {
        return name;
    }

    public GregorianCalendar getBeginning() {
        return beginning;
    }

    public GregorianCalendar getEnding() {
        return ending;
    }

    public String getBeginningInString() {
        if (beginning != null) {
            return beginning.get(beginning.DAY_OF_MONTH) + "/" + (beginning.get(beginning.MONTH) + 1) + "/" + beginning.get(beginning.YEAR);
        } else {
            return "inconnu";
        }
    }

    public String getEndingInString() {
        if (ending != null) {
            return ending.get(ending.DAY_OF_MONTH) + "/" + (ending.get(ending.MONTH) + 1) + "/" + ending.get(ending.YEAR);
        } else {
            return "inconnu";
        }
    }


}
