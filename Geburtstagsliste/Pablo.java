

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
    private Personenmanager Personenmanager;
    private ListGUI ListenGui;
    private List<Person> Geburtstagsliste;

    
    
    
    
    
    

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
        Personenmanager = new Personenmanager();
        ListenGui = new ListGUI();
        Geburtstagsliste = Personenmanager.getList();
        ListenGui.listAnmelden(Geburtstagsliste);
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
