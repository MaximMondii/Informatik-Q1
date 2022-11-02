/**
 * Die Klasse stellt Methoden zur Verwaltung der Wortlisten zur Verfuegung.
 * 
 * @author Udo Strang
 * @version 201529-2101
 */
public class WortGeneratorZumTesten {

    /**
     * Konstruktor für Objekte der Klasse WortLieferant.
     * Als Dateiname wird "Standard.wli" gesetzt und der ueberlade Konstruktor aufgerufen.
     */
    public WortGeneratorZumTesten() {
    }
    
    public String liefereWort() {
        String wort = "Testwort";
        return(wort.toUpperCase());
    }
}
