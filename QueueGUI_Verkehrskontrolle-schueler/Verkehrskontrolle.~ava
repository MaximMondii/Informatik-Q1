
/**
 * Die Verkehrskontrolle stellt eine mögliche Lösung der Aufgabenstellung auf Seite 65 dar
 * 
 * @author (Ruth Bosbach) 
 * @version (1.0)
 */
public class Verkehrskontrolle
{
    // instance variables - replace the example below with your own
   
    private int anzahlMangelhafteAutos = 0;
    private int anzahlKontrollierteFahrzeuge = 0;
    private int maxAnzMangelhafteFahrzeuge;
    private int maxAnzKontrollierteFahrzeuge;
    //private int anzKolonnenfahrzeuge = 0; //zusätzliche Variable zur Vereinfachung der Simulation jedes 2. Auto wird herausgewunken
    private int takte = 0;
    private TAKTGEBER taktgeber;
    Queue<Fahrzeug> kolonne;
    Queue<Fahrzeug> seitenstreifen;
    
    /**
     * Constructor for objects of class Verkehrskontrolle
     */
    public Verkehrskontrolle(int maxUntauglichkeit, int maxKontrolliert)
    {
        // initialise instance variables
        maxAnzMangelhafteFahrzeuge = maxUntauglichkeit;
        maxAnzKontrollierteFahrzeuge = maxKontrolliert;
        
        kolonne = new Queue<Fahrzeug>();
        seitenstreifen = new Queue<Fahrzeug>();
    }
    
    public void setTaktgeber(TAKTGEBER t){
        taktgeber = t;
    }

    public void einreihen(Fahrzeug pFahrzeug)
    {
        kolonne.enqueue(pFahrzeug);
    }

    public Fahrzeug kolonneHead()
    {
        return kolonne.front();
    }
    
    public Fahrzeug seitenstreifenHead()
    {
        return seitenstreifen.front();
    }
    
    /**
     * taktimpulsAusfuehren
     */
    public void taktimpulsAusfuehren(){
        //Neues Fahrzeug stellt sich an der Hauptkolonne an.
        takte++;
        
    }
    
    public void herauswinken()
    {
        Fahrzeug f = kolonneHead();
        kolonne.dequeue();
        seitenstreifen.enqueue(f);
    }
    
    public void kolonneAufloesen(){
        while(kolonneHead() != null)
        {
            kolonne.dequeue();
        }
    }
    
    public void seitenstreifenAufloesen()
    {
        while(seitenstreifenHead() != null)
        {
            seitenstreifen.dequeue();
        }   
    }
}
