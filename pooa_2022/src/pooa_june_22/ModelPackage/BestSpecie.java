package pooa_june_22.ModelPackage;

import java.util.GregorianCalendar;

public class BestSpecie {
    private String scientificName;
    private String vernacularName;
    private Boolean isIntelligent;
    private String location;
    private String period;
    private GregorianCalendar beginning;
    private GregorianCalendar ending;
    private Integer nbColony;

    public BestSpecie(String scientificName, String vernacularName, Boolean isIntelligent, String location, String period, GregorianCalendar beginning, GregorianCalendar ending, Integer nbColony) {
        this.scientificName = scientificName;
        this.vernacularName = vernacularName;
        this.isIntelligent = isIntelligent;
        this.location = location;
        this.period = period;
        this.beginning = beginning;
        this.ending = ending;
        this.nbColony = nbColony;
    }

    public GregorianCalendar getEnding() {
        return ending;
    }

    public void setEnding(GregorianCalendar ending) {
        this.ending = ending;
    }

    public void setBeginning(GregorianCalendar beginning) {
        this.beginning = beginning;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public void setIntelligent(Boolean intelligent) {
        isIntelligent = intelligent;
    }

    public void setVernacularName(String vernacularName) {
        this.vernacularName = vernacularName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public GregorianCalendar getBeginning() {
        return beginning;
    }

    public String getPeriod() {
        return period;
    }

    public String getLocation() {
        return location;
    }

    public Boolean getIntelligent() {
        return isIntelligent;
    }

    public String getVernacularName() {
        return vernacularName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public Integer getNbColony() {
        return nbColony;
    }

    public void setNbColony(Integer nbColony) {
        this.nbColony = nbColony;
    }
}
