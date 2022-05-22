package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.NameException;

public class Colony {
    private String name;
    private String specie;
    private String location;
    private Era era;


    public Colony(String specie, String location, Era period, String name) throws NameException {
        setSpecie(specie);
        setLocation(location);
        setEra(period);
        setName(name);
    }

    // Setters

    public void setName(String name) throws NameException {
        if (name == null || name.length() < 45)
            this.name = name;
        else {
            throw new NameException(name);
        }
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public void setLocation (String location) throws NameException {
        if (location != null && location.length() < 45)
            this.location = location;
        else {
            throw new NameException(location);
        }
    }

    public void setEra(Era era) {
        this.era = era;
    }
    

    // Getters

    public String getSpecie() {
        return specie;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Era getEra() {
        return era;
    }
}
