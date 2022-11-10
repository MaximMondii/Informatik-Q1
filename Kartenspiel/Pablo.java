

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse Pablo.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Pablo
{
    private Kartendealer kartende1;
    private ListGUI listGUI1;
    private List<Karte> list1;

    /**
     * Konstruktor fuer die Test-Klasse Pablo
     */
    public Pablo()
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
        kartende1 = new Kartendealer();
        listGUI1 = new ListGUI();
        list1 = kartende1.getDeck();
        listGUI1.listAnmelden(list1);
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
