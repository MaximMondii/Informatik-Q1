import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Theseus sucht den Weg aus dem Labyrinth
 */
public class Theseus extends JFrame implements ActionListener
{
    private Labyrinth l;
    private LabyrinthView lv;
    private int dim = 20;
    private int breite = 30;
    private JPanel folie = new JPanel();
    private JButton bNeu, bSuche;
    private JLabel lErgebnis, lGeschw;
    private JTextField tGeschw;

    /**
     * Konstruktor fuer Objekte der Klasse Theseus
     */
    public Theseus(){
        super("Theseus");

        setSize(800, 500);
        setLocation(100, 100);
        getContentPane().add(folie);
        folie.setBackground(Color.yellow);
        folie.setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        bNeu = new JButton("Neu");
        bNeu.setBounds(10, 10, 100, 20);
        folie.add(bNeu);
        bNeu.addActionListener(this);
        bSuche = new JButton("Suche");
        bSuche.setBounds(10, 40, 100, 20);
        folie.add(bSuche);        
        bSuche.addActionListener(this);

        lGeschw = new JLabel("Wartezeit:");
        lGeschw.setBounds(10, 80, 100, 20);
        folie.add(lGeschw);
        tGeschw = new JTextField("50");
        tGeschw.setBounds(10, 100, 100, 20);
        folie.add(tGeschw);

        lErgebnis = new JLabel("");
        lErgebnis.setBounds(10, dim * breite + 20, 500, 20);
        folie.add(lErgebnis);

        l = new Labyrinth(dim);
        l.init(0.3);
        lv = new LabyrinthView(l, breite);        
        lv.setBounds(120, 10, dim * breite + 1, dim * breite + 1);
        lv.setBackground(Color.white);
        folie.add(lv);        

        setVisible(true);
        l.meldeAn(lv);
    }

    /**
     * Es wird ein neues Labyrinth erstellt.
     */
    public void neu(){
        lErgebnis.setText("");
        l.init(0.3);
    }

    /**
     * Theseus sucht den Ausgang
     */
    public void run(){
        lErgebnis.setText("");
        if (sucheRek(0,0)){
            lErgebnis.setText("Es wurde ein Weg gefunden!");
        }
        else{
            lErgebnis.setText("Es wurde leider kein Weg gefunden!");            
        }    
    }

    /**
     * Theseus sucht den Ausgang mittels Rekursion
     */
  private boolean sucheRek(int x, int y) {
    if (x < 0 || x >= dim || y < 0 || y >= dim)
    {
      return false;
    }
    if (l.getStatus(x, y) != 0)
    {
      return false;
    }
    l.setStatus(x, y, 3);
    warte();
    if ((x == dim - 1 && y == dim - 1))
    {
      return true;
    }
    if(sucheRek(x + 1, y))
    {
        return true;
    }
    if(sucheRek(x, y + 1))
    {
        return true;
    }
    if(sucheRek(x - 1, y))
    {
        return true;
    }
    if(sucheRek(x, y - 1))
    {
        return true;
    }
    l.setStatus(x, y, 4);
    warte();
    return false;
  }  

    /**
     * Reagiere auf die Maus
     */
    public void actionPerformed (ActionEvent e) {
        Object quelle = e.getSource();
        if (quelle == bNeu){
            neu();
        }
        if (quelle == bSuche){
            run();
        }
    }

    /**
     * Theseus verharrt kurz!
     */
    private void warte(){
        int zeit;
        try{
            zeit = Integer.parseInt(tGeschw.getText());
        }
        catch (Exception ex){
            zeit = 50;
            tGeschw.setText("" + zeit);
        }
        try{
            Thread.sleep(zeit);
        }
        catch(Exception ex){

        }
    }
}    

