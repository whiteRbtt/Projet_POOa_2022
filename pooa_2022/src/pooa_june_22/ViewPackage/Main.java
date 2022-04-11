package pooa_june_22.ViewPackage;


import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        //MenuWindow menuWindow = new MenuWindow();
        GregorianCalendar calendar1 = new GregorianCalendar(2022, 05, 8);
        GregorianCalendar calendar2 = new GregorianCalendar(2022, 04,8);
        long a = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
        System.out.println(a);
        System.out.println(a/(24*60*60*1000));
    }
}
