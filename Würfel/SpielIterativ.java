

public class SpielIterativ
{
    Wuerfel wuerfel;
    public SpielIterativ(Wuerfel pWuerfel)
    {
        wuerfel = pWuerfel;
    }
    public Wuerfel getWuerfel()
    {
        return wuerfel;
    }
    public int addierenX(int x)
    {
        int L = 0;
        for(int i = 0; i < x; x--)
        {
            L = wuerfel.wuerfeln() + L;
        }
        return L;
    }
    public int maxFinden(int x)
    {
        int max = 0;
        for(int i = 0; i < x; x--)
        {
            int o = wuerfel.wuerfeln();
            if(max < o)
            {
                max = o;
            }
        }
        return max;
    }
    public void gibXErgebnisse(int x)
    {
        for(int i = 0; i < x; x--)
        {
            System.out.println(wuerfel.wuerfeln());
        }
    }
}
