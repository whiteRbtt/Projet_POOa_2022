package pooa_june_22.ExceptionPackage;

public class ClimateException extends Exception {
    private String wrongClimate;

    public ClimateException(String wrongClimate) {
        this.wrongClimate = wrongClimate;
    }

    public String getMessage() {
        return "La valeur " + wrongClimate + " est plus grande que la valeur permise.";
    }
}
