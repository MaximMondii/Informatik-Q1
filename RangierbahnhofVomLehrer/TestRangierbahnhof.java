

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestRangierbahnhof.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestRangierbahnhof
{
    private RangierProgramm rangierP1;
    private Gleis gleisA;
    private Gleis gleisB;
    private Gleis gleisC;

    /**
     * Default constructor for test class TestRangierbahnhof
     */
    public TestRangierbahnhof()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        rangierP1 = new RangierProgramm();
        gleisA = rangierP1.getGleisA();
        gleisB = rangierP1.getGleisB();
        gleisC = rangierP1.getGleisC();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
