package pooa_june_22.ExceptionPackage;


public class DateException extends Exception {

    public DateException() {
    }

    @Override
    public String getMessage() {
        return "Le calendrier cosmique de notre galaxie commence l'an 1000 et se termine l'an 9999";
    }
}
