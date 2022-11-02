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
    // instance variables - replace the example below with your own
   private JTextField [] tEingabefelderWort;
   private JTextField tBuchstabenvorschlag = new JTextField();
   private JTextField tLoesungswortvorschlag = new JTextField();
   private JLabel lBuchstabe = new JLabel("Buchstabe :");
    
    private JButton bPruefenBuchstabe = new JButton("Prüfe Buchstaben");
    private JButton bPruefenWort = new JButton("Prüfe Wortvorschlag");

    private Insets randAbstand  = new Insets(2,2,2,2);

    private Font meinFont = new Font("Arial",Font.BOLD,20);

    private HangmanBild dHangmanbild = new HangmanBild();
   
    private Hangman hangman = new Hangman();

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        super("Hangman");
        setLayout(null); //verhindert den Einsatz des Fenstermanagers
        setSize(800,500);
        tEingabefelderWort = new JTextField[hangman.getBuchstabenAnzahl()];
        for (int i = 0; i < hangman.getBuchstabenAnzahl(); i++){
            tEingabefelderWort[i] = new JTextField();
            tEingabefelderWort[i].setBounds((i+1)*40,150,30,30);
            tEingabefelderWort[i].setFont(meinFont);
            tEingabefelderWort[i].setHorizontalAlignment(JTextField.CENTER);
            tEingabefelderWort[i].setEditable(false);
            add(tEingabefelderWort[i]);
            
        }
        
        
        lBuchstabe.setBounds(75,75,130,30);
        add(lBuchstabe);
        tBuchstabenvorschlag.setBounds(170,75,30,30);
        tBuchstabenvorschlag.setFont(meinFont);
        tBuchstabenvorschlag.setHorizontalAlignment(JTextField.CENTER);
        add(tBuchstabenvorschlag);
        
        bPruefenBuchstabe.setFont(meinFont);
        bPruefenBuchstabe.setMargin(randAbstand);
        bPruefenBuchstabe.setBounds(220,75,250,30);
        bPruefenBuchstabe.addActionListener(this);
        add(bPruefenBuchstabe);
        
        tLoesungswortvorschlag.setBounds(40,200,200,30);
        tLoesungswortvorschlag.setFont(meinFont);
        tLoesungswortvorschlag.setHorizontalAlignment(JTextField.CENTER);
        add(tLoesungswortvorschlag);
        
        bPruefenWort.setFont(meinFont);
        bPruefenWort.setBounds(250,200,250,30);
        bPruefenWort.setMargin(randAbstand);
        //Wenn dieser Button geklickt wird, soll in der aktuellen Klasse 
        // eine Methode aufgerufen werden, die actionPerformed heisst
        bPruefenWort.addActionListener(this);
        add(bPruefenWort);

        dHangmanbild.setBounds(500,10,224,220);
       

        add(dHangmanbild);

        

        getContentPane().setBackground(Color.gray);

        setVisible(true);
    }
    
    public void textfelderFuellen(){
        for (int i = 0; i < hangman.getBuchstabenAnzahl(); i++){
            tEingabefelderWort[i].setText(hangman.getGeraten()[i]+"");
        }
        
    }

    public void actionPerformed(ActionEvent e){
        try{
            Object source = e.getSource();
           

            if(source == bPruefenBuchstabe){
                String buchstabe = tBuchstabenvorschlag.getText();
                buchstabe = buchstabe.trim();
                char bu = buchstabe.charAt(0);
                
                hangman.buchstabenVergleich(bu);
                textfelderFuellen();
                dHangmanbild.setBildnr(hangman.getAnzahlVersuche());
                
               
             

               

            }
            if(source == bPruefenWort){
                //Hier fehlt noch der Quelltext für das Eventhandling beim Klick auf den Button
                //Wer ein falsches Wort vorschlägt, bekommt zwei Striche
                

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
}
