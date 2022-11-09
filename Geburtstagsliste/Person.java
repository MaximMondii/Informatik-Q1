import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Person
{
    private String name; 
    private Date geburtstag;
<<<<<<< HEAD
    private String geburtstagString;
=======
>>>>>>> effb5b8b9b4957d5dab08fa387990d2e90452f85
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");
    public Person(String pName, String Datum)
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
<<<<<<< HEAD
        geburtstagString = Datum;
=======
>>>>>>> effb5b8b9b4957d5dab08fa387990d2e90452f85
    }
    public String getName()
    {
        return name;
    }
    public void DateMitPrintln()
    {
        System.out.println(dateFormat.format(calendar.getTime()));
    }
<<<<<<< HEAD
    public String geburtstagAlsString()
    {
        return geburtstagString;
    }
=======
>>>>>>> effb5b8b9b4957d5dab08fa387990d2e90452f85
    public Date getDate()
    {
        return geburtstag;
    }
    @Override
    public String toString()
    {
        String newline = System.getProperty("line.separator");
        String m = name + newline + dateFormat.format(calendar.getTime());
        return m;
    }
}
