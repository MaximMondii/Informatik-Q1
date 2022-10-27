import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class test
{
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");
    Person Pablo = new Person("Pablo", "Männlich", "20.01.2006");
    public test()
    {
        calendar.set(Calender.second, Pablo.getDate());
        
    }
    public void test()
    {
        
        System.out.println(calendar.getTime());
    
    }
}
