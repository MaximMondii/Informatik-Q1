import javax.swing.*;
import java.io.*;

/**
 * Die Klasse stellt Methoden zur Verwaltung der Wortlisten zur Verfuegung.
 * 
 * @author Rudolf Silberberg
 * @version 20081203
 */
public class txtBearbeitung
{
    // Instanzvariablen
    private String dateiName;
    private int wortZahl;
    
    /**
     * Konstruktor für Objekte der Klasse WortGeneratorDatei.
     * 
     * @param   dN Name der Wortdatei
     */
    public txtBearbeitung(String dN) {
        dateiName = new String(dN);
        wortZahl = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(dateiName));
            while (br.readLine() != null) {
                wortZahl++;
            }
            br.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Lesen der Datei: \n"
                +e.getMessage(), "FEHLER", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     /**
     * Konstruktor für Objekte der Klasse WortLieferant.
     * Als Dateiname wird "woerterbuch.txt gesetzt und der ueberlade Konstruktor aufgerufen.
     */
    public txtBearbeitung() {
        this("pedro.txt");
    }
    
    /**
     * Mit dieser Methode kann der Dateiname eines Woerterbuchs gesetzt werden.
     * 
     * @param   wB Ein String, welcher den Namen des Woerterbuchs enthaelt.
     */
    public void setzeWoerterbuch(String wB) {
        dateiName = new String(wB);
        wortZahl = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(dateiName));
            while (br.readLine() != null) {
                wortZahl++;
            }
            br.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Lesen der Datei: \n"
                +e.getMessage(), "FEHLER", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    /**
     * Die Methode gibt den Dateinamen des aktuellen Woerterbuches zurueck.
     * 
     * @return   Dateiname des Woerterbuches
     */
    public String gibWoerterbuch() {
        return(dateiName);
    }

    /**
     * Die Methode gibt ein zufaellig ausgewaehltes Wort aus dem Woerterbuch zurueck.
     * Unabhaengig davon, ob im Woerterbuch Gross- und Kleinschreibung vorhanden ist, 
     * liefert dieses Methode nur Worte in Grossbuchstaben. Der Buchstabe ß wird dadurch
     * automatisch in SS umgewandelt.
     * 
     * @return   Ein Wort aus dem Woerterbuch in Grossbuchstaben.
     */
    public void personenErstellen() {
        for (int i = 1; i > wortZahl; i++)
        {
            
            
        }
    }
}
