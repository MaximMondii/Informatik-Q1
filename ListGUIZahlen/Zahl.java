
/**
 * Write a description of class Zahlen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zahl
{
    // instance variables - replace the example below with your own
    private int zahl;

    /**
     * Constructor for objects of class Zahlen
     */
    public Zahl(int z)
    {
        // initialise instance variables
        zahl = z;
    }
    /**
     * Methode, um den Zahlenwert von zahl zurückzugeben.
     */
    public int getZahl(){
        return zahl;
    }
    

    
    public String toString()
    {
        // Konvertierung von zahl in einen String
        return Integer.toString(zahl);
    }
}
