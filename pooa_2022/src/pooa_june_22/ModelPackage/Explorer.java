package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.DateException;
import pooa_june_22.ExceptionPackage.IdException;
import pooa_june_22.ExceptionPackage.NameException;

import java.util.GregorianCalendar;

public class Explorer {
    private Integer explorerId;
    private String name;
    private Boolean isAlive;
    private GregorianCalendar birthDate;
    private String specie;

    public Explorer(Integer explorerId, String name, Boolean isAlive, GregorianCalendar birthDate, String specie) throws IdException, NameException, DateException {
        setExplorerId(explorerId);
        setName(name);
        setAlive(isAlive);
        setBirthDate(birthDate);
        setSpecie(specie);
    }

    public Explorer(int exploId) throws IdException {
        setExplorerId(exploId);
    }


    // Setters

    public void setExplorerId(Integer explorerId) throws IdException {
        if (explorerId != null && explorerId > 0)
            this.explorerId = explorerId;
        else {
            throw new IdException(explorerId);
        }
    }

    public void setName(String name) throws NameException {
        if (name != null && name.length() < 45)
            this.name = name;
        else {
            throw new NameException(name);
        }
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public void setBirthDate(GregorianCalendar birthDate) throws DateException {
        if (birthDate == null || (birthDate.get(birthDate.YEAR) >= 1000 && birthDate.get(birthDate.YEAR) <= 9999))
            this.birthDate = birthDate;
        else {
            throw new DateException();
        }
    }
    public void setSpecie(String specie) {
        this.specie = specie;
    }

    // Getters
    public Integer getExplorerId() {
        return explorerId;
    }

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

}
