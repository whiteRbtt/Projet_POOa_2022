package pooa_june_22.ExceptionPackage;


public class DateException extends GeneralException {

    public DateException() {
    }

    @Override
    public String getMessage() {
        return "Le calendrier cosmique de notre galaxie commence l'an 1000 et se termine l'an 9999";
    }

    public String getTitle(){
        return "Erreur sur la date ! ";
    }
}
