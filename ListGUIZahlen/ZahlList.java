
/**
 * Write a description of class ZahlList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZahlList
{
    // instance variables - replace the example below with your own
    private List<Zahl> zahlenliste;

    /**
     * Constructor for objects of class ZahlList
     */
    public ZahlList()
    {
        // initialise instance variables
        zahlenliste = new List<Zahl>();
    }

    /**
     * Methode, die eine Referenz auf die in der ZahlList gespeicherte
     * Liste zurückgibt, damit diese an der ListGUI angemeldet werden kann
     */
    public List<Zahl> getList()
    {
        // put your code here
        return zahlenliste;
    }

    /**
     * Fülle die List zahlenliste mit diesem Methodenaufrufe
     * mit den Werten 3, 5, 6 ,7 in dieser Reihenfolge
     */
    public void zahlenlisteFuellen(){
        zahlenliste.append(new Zahl(3));
        zahlenliste.append(new Zahl(5));
        zahlenliste.append(new Zahl(6));
        zahlenliste.append(new Zahl(7));
    }

    /**
     * Wir wollen auf der gefüllten Zahlen Liste den Wert 
     * 4 zwischen die 3 und die 5 einsortieren.
     * Diese Aufgabe kann mit einem unterschiedlichen Schwierigkeitsgrad
     * gelöst werden
     * a) einfach: die 4 wird an die zweite Stelle gesetzt
     * /
     public void zahlSortiertEinfuegenA(){
        
     }
    /**
     * b) schwieriger: die Liste wird durchsucht, bis der richtige Platz zum 
     * einsortieren in der Liste gefunden wurde. 
     * 
     * Dazu muss der Wert als Parameter übergeben werden.
     */
    public void zahlSortiertEinfuegenB(Zahl z){
       

        
    }

    /**
     * Die als Parameter übergebene Zahl z soll gelöscht werden.
     * Erstellen Sie hierzu ein Struktogramm (Buch S. 361)oder einen Ablaufplan
     * Tipp: remove() löscht den current, falls es einen current gibt. Ansonsten bleibt die Liste unverändert.
     */
    public void loescheZahl(Zahl z){
    }

    /**
     * Alle Zahlen sollen in ihrer Reihenfolge auf der Konsole ausgegeben werden
     * Benutzen Sie hier die Methode System.out.println
     */
    public void listeAusgeben(){
    }

}
