
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
        C.push(A.top());
        A.pop();
        while(!A.isEmpty())
        {
            while(A.top().getNummer() < C.top().getNummer())
            {
                A.push(C.top());
                C.pop();
                if(C.isEmpty())
                {
                    C.push(A.top());
                    A.pop();  
                }
            }
            if(!B.isEmpty())
            {
                if(A.top().getNummer() < B.top().getNummer())
                {
                    C.push(A.top());
                    A.pop();
                }
            }
            while(!B.isEmpty())
            {
                C.push(B.top());
                B.pop();
            }
        }
    }
}
