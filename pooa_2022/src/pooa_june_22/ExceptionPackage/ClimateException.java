package pooa_june_22.ExceptionPackage;

public class ClimateException extends Exception {
    private String wrongClimate;

    public ClimateException(String wrongClimate) {
        this.wrongClimate = wrongClimate;
    }

    public String getMessage() {
        return "The value " + wrongClimate + " is above the length allowed.";
    }
}
