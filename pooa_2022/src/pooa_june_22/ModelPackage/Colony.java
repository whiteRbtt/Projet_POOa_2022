package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.NameException;

public class Colony {

    private String specie;
    private Integer location;
    private String period;
    private String name;

    public Colony(String specie, Integer location, String period, String name) throws NameException {
        setSpecie(specie);
        setLocation(location);
        setPeriod(period);
        setName(name);
    }

    // Setters

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setName(String name) throws NameException {
        if (name == null || name.length() < 45)
            this.name = name;
        else {
            throw new NameException(name);
        }
    }

    // Getters

    public String getSpecie() {
        return specie;
    }

    public Integer getLocation() {
        return location;
    }

    public String getPeriod() {
        return period;
    }

    public String getName() {
        return name;
    }

}
