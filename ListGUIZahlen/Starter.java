import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
/**
 * Klasse Starter 
 *
 * @version 2013-02-16 (v 1.0)
 * @author Rudolf Silberberg
 */

public class Starter extends JFrame {
    // Anfang Attribute
    private JLabel lAnzahl = new JLabel();
    private JButton bStart = new JButton();
    public List liste1;
    public List liste2;
    private ButtonGroup jButtonGroup1 = new ButtonGroup();
    private JRadioButton jRadioButton1 = new JRadioButton();
    private JRadioButton jRadioButton2 = new JRadioButton();
  // Ende Attribute
  
  public Starter(String title) { 
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 140; 
    int frameHeight = 170;
    setSize(frameWidth, frameHeight);
    //    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    //    int x = (d.width - getSize().width) / 2;
    //    int y = (d.height - getSize().height) / 2;
    setLocation(10, 10);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    lAnzahl.setBounds(15, 10, 100, 30);
    lAnzahl.setText("Anzahl Listen:");
    cp.add(lAnzahl);
    bStart.setBounds(15, 100, 100, 30);
    bStart.setText("Start");
    bStart.setMargin(new Insets(2, 2, 2, 2));
    bStart.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bStart_ActionPerformed(evt);
      }
    });
    cp.add(bStart);
    jRadioButton1.setBounds(15, 50, 100, 20);
    jRadioButton1.setText("eine");
    jRadioButton1.setOpaque(false);
    jButtonGroup1.add(jRadioButton1);
    
    jRadioButton1.setSelected(true);
    cp.add(jRadioButton1);
    jRadioButton2.setBounds(15, 70, 100, 20);
    jRadioButton2.setText("zwei");
    jRadioButton2.setOpaque(false);
    jButtonGroup1.add(jRadioButton2);
    
    cp.add(jRadioButton2);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Starter
  
  // Anfang Methoden
  public void bStart_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen;
    String wahl = getSelectedRadioButton(jButtonGroup1);
    ListGUI gui1 = new ListGUI(1);
    if (wahl.equals("zwei")) {
      ListGUI gui2 = new ListGUI(2);
      gui1.guiAnmelden(gui2);
      gui2.guiAnmelden(gui1);
    } // end of if
    bStart.setEnabled(false);
  } // end of bStart_ActionPerformed
  
  public String getSelectedRadioButton(ButtonGroup bg) {
    for (java.util.Enumeration<AbstractButton> e = bg.getElements(); e.hasMoreElements();) {
      AbstractButton b = e.nextElement();
      if (b.isSelected()) return b.getText();
    }
    return null;
  }
  
  // Ende Methoden
  
  public static void main(String[] args) {
    new Starter("");
  } // end of main
  
} // end of class Starter
