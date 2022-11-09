
/**
 * Write a description of class Hangman here.
 * 
 * @author (Ruth Bosbach) 
 * @version (1.0)
 */
public class Hangman
{
    private char [] wort;
    private char [] geraten;
    private int anzahlVersuche;
    public static final int maxAnzahlVersuche = 10;

    /**
     * Constructor for objects of class Hangman
     */
    public Hangman()
    {
        // initialise instance variables
       neuesWort();
       geraten = new char[wort.length];
       //Initialisierung von geraten mit ' '
       for (int i = 0; i < geraten.length; i++){
           geraten[i] = ' ';
        }
    }

    /**
     * Holt sich ein zufälliges Wort aus der Textdatei
     * woerterbuch.txt und speichert dieses im Attribut wort als Array
     * In jeder Zeile steht ein Wort.
     */
    public void neuesWort()
    {
        // put your code here
        //WortGeneratorZumTesten w = new WortGeneratorZumTesten();
        WortGeneratorDatei w = new WortGeneratorDatei("woerterbuch.txt");
        String einWort = w.liefereWort();
        wort = einWort.toCharArray();
      
    }
    
    public int getBuchstabenAnzahl(){
        return wort.length;
    }
    
    public char[] getGeraten(){
        return geraten;
    }
    public boolean buchstabenVergleich(char bu){
        //int codezahl = Character.getNumericValue(bu);
        
         boolean buchstabeVorhanden = false;
        
        if (Character.isLetter(bu)){
        
        bu = Character.toUpperCase(bu); //Wrapperklasse wäre nicht für die EF, lieber Zeichenkodierung
    
        for (int i = 0; i < wort.length; i++){
            if (wort[i] == bu){
                geraten[i] = bu;
                buchstabeVorhanden = true;
            }
            
        }
        
        if (!buchstabeVorhanden){
            anzahlVersuche++;
        }
    }
        
        return buchstabeVorhanden;
    }
    
    public boolean istWortGefunden(String pWort){
        char [] rateWort = pWort.toCharArray();
        boolean gleich = true;
        for (int i = 0; i < wort.length -1 && gleich == true; i++){
            if (rateWort[i] != wort[i]){
                gleich = false;
            }
            
        }
        return gleich;
    }
    
    public boolean wortVergleich(){
        String dasWort = new String(wort);
        String dasRatewort = new String(geraten);
        
        return dasWort.equals(dasRatewort);
    }
    
    public boolean istSpielBeendet(){
        if (anzahlVersuche < maxAnzahlVersuche){
            return false;
        }
        else{
            return true;
        }
    }
    
    public int getAnzahlVersuche(){
        return anzahlVersuche;
    }
}
