import java.util.*;
/**
 * Die Klasse stellt Methoden zur Verwaltung der Wortlisten zur Verfuegung.
 * 
 * @author Udo Strang
 * @version 201529-2101
 */
public class WortGeneratorEinfach {

    /**
     * Konstruktor für Objekte der Klasse WortLieferant.
     * Als Dateiname wird "Standard.wli" gesetzt und der ueberlade Konstruktor aufgerufen.
     */
    public WortGeneratorEinfach() {
    }
    
    public String liefereWort() {
        String[] woerter = new String[] {"Stein","Maus","Adler","Kuh","Igel","Marder","Meise","Schwein","Hund","Katze"};
        int wert = new Random().nextInt(woerter.length);
        return(woerter[wert].toUpperCase());
    }
}
