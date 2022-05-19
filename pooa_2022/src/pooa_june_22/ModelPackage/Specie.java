package pooa_june_22.ModelPackage;

import pooa_june_22.ExceptionPackage.NameException;

public class Specie {

    private String scientificName;
    private String vernacularName;
    private Boolean isIntelligent;
    private Boolean isExtinct;

    public Specie(String scientificName, String vernacularName, Boolean isIntelligent, Boolean isExtinct) throws NameException {
        setScientificName(scientificName);
        setVernacularName(vernacularName);
        setIntelligent(isIntelligent);
        setExtinct(isExtinct);
    }

    // Setters

    public void setScientificName(String scientificName) throws NameException {
        if (scientificName != null && scientificName.length() < 45)
            this.scientificName = scientificName;
        else {
            throw new NameException(scientificName);
        }
    }

    public void setVernacularName(String vernacularName) throws NameException {
        if (vernacularName != null && vernacularName.length() < 45)
            this.vernacularName = vernacularName;
        else {
            throw new NameException(vernacularName);
        }
    }

    public void setIntelligent(Boolean intelligent) {
        isIntelligent = intelligent;
    }

    public void setExtinct(Boolean extinct) {
        isExtinct = extinct;
    }

    // Getters

    public String getVernacularName() {
        return vernacularName;
    }

}
