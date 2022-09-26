
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
        while(!A.isEmpty() && !B.isEmpty()){
            kleinsteZahl = A.top().getNummer();
            B.push(A.top());
            A.pop();
            while(!A.isEmpty()){
                if(kleinsteZahl > A.top().getNummer())
                {
                    kleinsteZahl = A.top().getNummer();
                    B.push(A.top());
                    A.pop();
                }else
                {   
                    B.push(A.top()); 
                    A.pop();
                }
            }
            while(!B.isEmpty()){
                if(kleinsteZahl == B.top().getNummer())
                {
                    C.push(B.top());
                    B.pop();
                }else
                {
                 A.push(B.top());
                 B.pop();
                }
            }
        }
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
