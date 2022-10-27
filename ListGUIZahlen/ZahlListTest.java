

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse ZahlListTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ZahlListTest
{
    private ListGUI listGUI1;
    private ZahlList zahlList1;
    private List<Zahl> list1;

    
    
    

    /**
     * Konstruktor fuer die Test-Klasse ZahlListTest
     */
    public ZahlListTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp()
    {
        listGUI1 = new ListGUI();
        zahlList1 = new ZahlList();
        list1 = zahlList1.getList();
        listGUI1.listAnmelden(list1);
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown()
    {
    }
}
