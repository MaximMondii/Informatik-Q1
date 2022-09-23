import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

/**
 * Zeitkontrolle der Queue
 * 
 * @author Klaus van Dijkstran und Barbara Leidorn
 * @version 1.0
 */
class TAKTGEBER implements ActionListener
{
    /** Timerobjekt f&uuml;r die zentrale Zeitverwaltung */
    Timer timer;
    /** zu steuerndes Objekt, muss von jedem Takt benachrichtigt werden. */
    //Hierher kommen die Attribute für die zu benachrichtigenden Objekte
    Verkehrskontrolle sim;
    
    /**
     * Erzeugt den Taktgeber und tr&auml;gt als Parameter den Taktempf&auml;nger ein.
     * @param planSys der zu bedienende Taktempf&auml;nger
     */
    TAKTGEBER (Verkehrskontrolle obj)
    {
        timer = new Timer(1000, this); //1000 Millisekunden
        this. sim = obj;
        QueueGUI gui = new QueueGUI(0);
        gui.setTitle("Strasse");
        //gui.queueAnmelden(sim.getKolonne());
        QueueGUI guiKontrollkolonne = new QueueGUI(1);
        guiKontrollkolonne.setTitle("Kontrollkolonne - Seitenstrefen");
        
        sim.setTaktgeber(this);
        
    }
    
    TAKTGEBER(){
        this(new Verkehrskontrolle(5,12));
    }

    /**
     * vom Timer aufgerufen
     * erh&ouml;ht die Simulationszeit und benachrichtigt alle eingetragenen Objekte
     * @param evt der Timerevent
     */
    public void actionPerformed (ActionEvent evt)
    {
        // hierher kommen die Aufrufe f&uuml;r die zu benachrichtigenden Objekte
        sim.taktimpulsAusfuehren ();
    }

    /**
     * Timer starten
     */
    void Starten ()
    {
        timer. start ();
    }

    /**
     * Timer anhalten
     */
    void Anhalten ()
    {
        timer. stop ();
    }
    
    /**
     * Ablaufgeschwindigkeit einstellen
     * 
     * @param dauer: Angabe in Millisekunden
     */
    void TaktdauerSetzen (int dauer)
    {
        timer. setDelay(dauer);
    }
    
    
}
