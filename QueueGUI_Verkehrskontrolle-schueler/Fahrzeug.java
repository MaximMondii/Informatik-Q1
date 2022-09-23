
/**
 * Beschreiben Sie hier die Klasse Fahrzeug.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Fahrzeug
{
    private boolean besch�digt;
    private String modell;
    private String farbe;
    private String kennzeichen;
    
    public Fahrzeug(){
        modell = "Pablo"; //Polizisten m�gen keine Automarke namens Pablo
        randomFarbe();
        farbe = randomFarbe();
        randomKennzeichen();
        kennzeichen = randomKennzeichen();
        randomBesch�digt();
        besch�digt = randomBesch�digt();
    }
    
    public String getModell()
    {
        return modell;
    }
    
    public String getFarbe()
    {
        return farbe;
    }
    
    public String getKennzeichen()
    {
        return kennzeichen;
    }
    
    public String randomFarbe()
    {
        int n = 1 + (int)(Math.random() * ((5 - 1) + 1));
        if(n == 1){farbe = "Blau";}
        if(n == 2){farbe = "Gr�n";}
        if(n == 3){farbe = "Gelb";}
        if(n == 4){farbe = "Lila";}
        if(n == 5){farbe = "Rot";}
        return farbe;
    }
    
    public String randomKennzeichen()
    {
        int n = 1 + (int)(Math.random() * ((3 - 1) + 1));
        if(n == 1){kennzeichen = "Gl ";}
        if(n == 2){kennzeichen = "F ";}
        if(n == 3){kennzeichen = "K ";}
        int w = 1 + (int)(Math.random() * ((1000 - 1) + 1));
        return kennzeichen = kennzeichen + w;
    }
    
    public boolean randomBesch�digt()
    {
        boolean b = false;
        int n = 1 + (int)(Math.random() * ((5 - 1) + 1));
        if (n == 2)
        {
            b = true;
        }
        return b;
    }
    
    @Override
    public String toString()
    {
        return kennzeichen;
    }
}
