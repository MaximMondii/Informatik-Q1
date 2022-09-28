
/**
 * Beschreiben Sie hier die Klasse Bahnhof.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Bahnhof
{
    private Stack<Zug> A;
    private Stack<Zug> B;
    private Stack<Zug> C;
    private int kleinsteZahl;
    public Bahnhof()
    {
        A = new Stack<Zug>();
        B = new Stack<Zug>();
        C = new Stack<Zug>();
    }
    public void aufGleisASchmeißen(Zug Zug)
    {
        A.push(Zug);
    }
    public void sortieren(){
        Stack<Zug> start = A;
        Stack<Zug> rangier = B;
        Stack<Zug> ziel = C;
        
        
        ziel.push(start.top());
        start.pop();
        while(!start.isEmpty())
        {
            while(start.top().getNummer() < ziel.top().getNummer())
            {
                rangier.push(ziel.top());
                ziel.pop();
                if(ziel.isEmpty())
                {
                    ziel.push(start.top());
                    start.pop();  
                }
            }
            if(!rangier.isEmpty())
            {
                if(start.top().getNummer() < rangier.top().getNummer())
                {
                    ziel.push(start.top());
                    start.pop();
                }
            }
            while(!rangier.isEmpty())
            {
                ziel.push(rangier.top());
                rangier.pop();
            }
        }
    }
}
