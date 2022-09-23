
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
    /*public void sortieren()
    {
        C.push(A.top());
        A.pop();
        while(!A.isEmpty())
        {   
            if(C.isEmpty())
            {
                C.push(A.top());
                A.pop();
            }
            if(A.top().getNummer() < C.top().getNummer())
            {
                C.push(A.top());
                A.pop();
            }
            else
            {
                B.push(C.top());
                C.pop();
                while(!B.isEmpty())
                {
                    C.push(B.top());
                    B.pop();
                }
            }
        }
    }
        **/
}
