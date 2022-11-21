
public class Wuerfel
{
    private int seiten;
    
    public Wuerfel(int pSeiten)
    {
        seiten = pSeiten;
    }
    public int wuerfeln()
    {
        int max = seiten;
        int min = 1;
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
    public int gibSeiten()
    {
        return seiten;
    }
}  