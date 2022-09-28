

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse RangierTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class RangierTest
{
    private Bahnhof bahnhof1;
    private Zug zug1;
    private Zug zug2;
    private Zug zug3;
    private Zug zug4;
    private Zug zug5;
    private Zug zug6;
    private Zug zug7;
    private Zug zug8;

    
    
    
    

    /**
     * Konstruktor fuer die Test-Klasse RangierTest
     */
    public RangierTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @BeforeEach
    public void setUp()
    {
        bahnhof1 = new Bahnhof();
        zug1 = new Zug(10);
        zug2 = new Zug(2);
        zug3 = new Zug(1);
        bahnhof1.aufGleisASchmeißen(zug1);
        bahnhof1.aufGleisASchmeißen(zug2);
        bahnhof1.aufGleisASchmeißen(zug3);
        zug4 = new Zug(10);
        zug5 = new Zug(24);
        zug6 = new Zug(3);
        zug7 = new Zug(56);
        zug8 = new Zug(16);
        bahnhof1.aufGleisASchmeißen(zug4);
        bahnhof1.aufGleisASchmeißen(zug5);
        bahnhof1.aufGleisASchmeißen(zug6);
        bahnhof1.aufGleisASchmeißen(zug7);
        bahnhof1.aufGleisASchmeißen(zug8);
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
