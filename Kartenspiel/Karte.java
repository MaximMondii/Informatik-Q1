
public class Karte
{
    private String zahl;
    private String farbe;
    
    public Karte(String pZahl, String pFarbe)
    {
        zahl = pZahl;
        farbe = pFarbe;
    }
    public String getZahl()
    {
        return zahl;
    }
    public String getFarbe()
    {
        return farbe;
    }
    @Override
    public String toString()
    {
        String i = zahl + ", " + farbe;
        return i;
    }
}
