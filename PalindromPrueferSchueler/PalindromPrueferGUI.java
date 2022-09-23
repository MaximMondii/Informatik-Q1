import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class PalindromPrueferGUI here.
 * 
 * @author () 
 * @version (1.0)
 */
public class PalindromPrueferGUI extends JFrame
{
    // instance variables - replace the example below with your own
    private JTextField eingabe;
    private JTextField ausgabe;
    private JButton umdrehen;
    private JPanel pEingabe;
    private JPanel pAusgabe;
    private JPanel pUmdrehen;
    private JLabel isPalindrom;

    /**
     * Constructor for objects of class PalindromPrueferGUI
     */
    public PalindromPrueferGUI()
    {
        super("Palindromprüfer");
        
        //Elemente der GUI anlegen
        eingabe = new JTextField(20);
        ausgabe = new JTextField(20);
        ausgabe.setEditable(false);
        umdrehen = new JButton("Umdrehen");
        
        pEingabe = new JPanel();
        pAusgabe = new JPanel();
        pUmdrehen = new JPanel();
        
        isPalindrom = new JLabel("Noch nicht getestet!");
        
        pEingabe.add(new JLabel("Eingabe: "));
        pEingabe.add(eingabe);
        
        pAusgabe.add(new JLabel("Ausgabe: "));
        pAusgabe.add(ausgabe);
        
        pUmdrehen.add(umdrehen);
        pUmdrehen.add(isPalindrom);
        
        
        
        
        
     
        //setPosition(20,20);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        contentPane.add(pEingabe,BorderLayout.NORTH);
        contentPane.add(pUmdrehen,BorderLayout.CENTER);
        contentPane.add(pAusgabe,BorderLayout.SOUTH);
        
        umdrehen.addActionListener(new MyActionListener());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          pack();
        setVisible(true);
        
    }
    
    
    public void setEingabe(String text){
        eingabe.setText(text);
    }

    
    class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //Hier müsste nun programmiert werden, was passieren soll
            //Überlegen Sie sich die einzelnen Schritte. Verwenden Sie die Klasse Stack<ContentType>
            
            String text = eingabe.getText(); //Wir holen uns den Text aus dem Eingabefeld
            
            String umgekehrt = ""; //ist die Variable für den umgekehrten Text
           
            boolean palindrom = false; //zunächst einmal sind alle Texte keine Palindrome
           
            //Ausgabe
            if (palindrom){
            isPalindrom.setText("Ist ein Palindrom");
        }
        else{
            isPalindrom.setText("Ist kein Palindrom");
        }
        
        ausgabe.setText(umgekehrt);
            
            
            
            
        }
    }

}
