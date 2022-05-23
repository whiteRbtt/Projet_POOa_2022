package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.NameException;

public class Colony {
    private String name;
    private Specie specie;
    private AstroBody location;
    private Era era;


    public Colony(Specie specie, AstroBody location, Era period, String name) throws NameException {
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

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public void setLocation (AstroBody location) throws NameException {
        if (location != null && location.getName().length() < 45)
            this.location = location;
        else {
            throw new NameException(location.getName());
        }
    }

    public void setEra(Era era) {
        this.era = era;
    }


    // Getters

    public Specie getSpecie() {
        return specie;
    }

    public String getName() {
        return name;
    }

    public AstroBody getLocation() {
        return location;
    }

    public Era getEra() {
        return era;
    }
}
