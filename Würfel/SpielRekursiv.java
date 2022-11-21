
/**
 * Beschreiben Sie hier die Klasse SpielRekursiv.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SpielRekursiv
{
    Wuerfel wuerfel;
    public SpielRekursiv(Wuerfel pWuerfel)
    {
        wuerfel = pWuerfel;
    }

    public Wuerfel getWuerfel()
    {
        return wuerfel;
    }

    public int addierenX(int x)
    {
        if(x == 0)
        {
            return 0;
        }
        else
        {
            return wuerfel.wuerfeln() + addierenX(x-1);
        }
    }

    public int maxFinden(int x)
    {
        if(x == 0)
        {
            return 0;
        }
        else
        {
            int vorher = maxFinden(x - 1);
            int current = wuerfel.wuerfeln();
            if(vorher < current)
            {
                return current;
            }
            else
            {
                return vorher;
            }
        }
    }

    public String gibXErgebnisse(int x)
    {
        String temp = "";
        if(x == 0)
        {
            System.out.println("Done");
        }
        else
        temp = wuerfel.wuerfeln() + ", " + gibXErgebnisse(x - 1);
        return temp;
    }
}
