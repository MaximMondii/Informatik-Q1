import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Person
{
    private String name;
    private String geschlecht; 
    private Date geburtstag;
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");
    public Person(String pName, String pGeschlecht, String Datum)
    {
        String tag = Datum.substring(0, 2);
        String monat = Datum.substring(3, 5);
        String jahr = Datum.substring(6, 10);
        calendar.set(Calendar.YEAR, Integer.parseInt(jahr));
        calendar.set(Calendar.MONTH, Integer.parseInt(monat));
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tag));
        geburtstag = calendar.getTime();
        name = pName; 
        geschlecht = pGeschlecht;
    }
    public String getName()
    {
        return name;
    }
    public String getGeschlecht()
    {
        return geschlecht;
    }
    public void DateMitPrintln()
    {
        System.out.println(dateFormat.format(calendar.getTime()));
    }
    public Date getDate()
    {
        return geburtstag;
    }
    @Override
    public String toString()
    {
        String m = name + " hat am " + geburtstag + " Geburtstag.";
        return m;
    }
}
