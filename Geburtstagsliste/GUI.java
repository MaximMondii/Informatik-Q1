import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI extends JFrame implements ActionListener
{
    //Instanzvariablen für das Fenster

    private JTextField tKontaktname = new JTextField();
    private JTextField tKontaktmail = new JTextField();
    private JLabel lKontakt = new JLabel("Kontakt :");

    private JButton bZeigeKontakt = new JButton("Zeige Kontakt");
    private JButton bHinzufuegenKontakt = new JButton("Füge Kontakt hinzu");

    private Insets randAbstand  = new Insets(2,2,2,2);

    private Font meinFont = new Font("Arial",Font.BOLD,20);

    private Personenmanager liste = new Personenmanager();

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        super("Kontaktliste");
        setLayout(null); //verhindert den Einsatz des Fenstermanagers
        setSize(800,500);

        liste.testlisteAnlegen();

        lKontakt.setBounds(75,75,130,30);
        add(lKontakt);

        tKontaktname.setBounds(40,120,200,30);
        tKontaktname.setFont(meinFont);
        tKontaktname.setHorizontalAlignment(JTextField.CENTER);
        add(tKontaktname);

        bZeigeKontakt.setFont(meinFont);
        bZeigeKontakt.setMargin(randAbstand);
        bZeigeKontakt.setBounds(40,220,200,30);
        bZeigeKontakt.addActionListener(this);
        add(bZeigeKontakt);

        tKontaktmail.setBounds(40,170,200,30);
        tKontaktmail.setFont(meinFont);
        tKontaktmail.setHorizontalAlignment(JTextField.CENTER);
        add(tKontaktmail);

        bHinzufuegenKontakt.setFont(meinFont);
        bHinzufuegenKontakt.setBounds(250,220,200,30);
        bHinzufuegenKontakt.setMargin(randAbstand);
        //Wenn dieser Button geklickt wird, soll in der aktuellen Klasse 
        // eine Methode aufgerufen werden, die actionPerformed heisst
        bHinzufuegenKontakt.addActionListener(this);
        add(bHinzufuegenKontakt);

        //Ersten Eintrag der Kontaktliste anzeigen

        liste.toFirst();
        if (liste.hasAccess()){
            Person k = liste.getContent();
            tKontaktname.setText(k.getName());
            tKontaktmail.setText(k.geburtstagAlsString());
        }


        getContentPane().setBackground(Color.white);
        setVisible(true);
    }

    public void textfelderFuellen(){

    }
    //hier findet das Eventhandling statt
    public void actionPerformed(ActionEvent e){
       
        try{
            Object source = e.getSource();

            if(source == bZeigeKontakt){
                 
                if(!liste.isEmpty())
                {

                    liste.toFirst();
                    if (liste.hasAccess()){
                        tKontaktname.setText(liste.getList().getContent().getName());
                        System.out.println(liste.getList().getContent().getName());
                        }
                    
                }
                    System.out.println("Listenelement: " + liste.getContent().getName());
            }
            if(source == bHinzufuegenKontakt){

            }
            //Die GUI bitten sich neu zu zeichnen
            SwingUtilities.updateComponentTreeUI(this);
            this.invalidate();
            this.validate();
            this.repaint();
        }
        catch(RuntimeException re){
            System.out.println(re);
        }

    }
    
    public Personenmanager getPersonenmanager(){
        return liste;
    }
}
