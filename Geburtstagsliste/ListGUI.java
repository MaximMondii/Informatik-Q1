import java.awt.*;  
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.reflect.*;
import java.util.*; 
import java.io.*;
// Importe fuer innere Klasse Parser
import java.util.Scanner;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
  
/**
 * Klasse ListGUI
 * 
 * @version 2015-01-29 (v 3.1)
 * 
 * @author Rudolf Silberberg
 */

public class ListGUI extends JFrame {   

    /*
     * Die verschiedenen Panels
     */
    // Das Kommandopanel
    private JPanel panCommands = new JPanel(null, true);
    // Das Ergebnispanel im Kommandopanel
    private JPanel panErgebnis = new JPanel(null, true);
    // Das Objektpanel im Ergebnispanel im Kommandopanel
    private JPanel panObject = new JPanel(null, true); 
    // Das Methodenpanel
    private JPanel panMethods = new JPanel(null, true);
    // Das Knotenpanel
    private JNodePanel panNodes = new JNodePanel();
    // Das Parameterpanel 1
    private JPanel panParameters1 = new JPanel(null, true);
    // Das Parameterpanel 2
    private JPanel panParameters2 = new JPanel(null, true);

    /*
     * Die Inhalte der Panels
     */
    // Inhalte des Ergebnispanels
    // --- Das Objektpanel und
    private JTextField tErgebnis = new JTextField(); 
    private JButton bErgebnisLoeschen = new JButton();
    // Inhalte des Kommandopanels
    // --- Das Ergebnispanel und
    private JCheckBox chkVisibleObjects = new JCheckBox();
    private JSpinner spGroesse = new JSpinner(); 
    private JScrollBar scroller = new JScrollBar();
    private JCheckBox chkAutoRefresh = new JCheckBox();
    private JButton bRefresh = new JButton();
    // Inhalte des Methodenpanels
    private JButton bNewList = new JButton();
    private JButton bListNull = new JButton();
    private JComboBox cBLists = new JComboBox();
    private JList lstMethods = new JList();
    private DefaultListModel lstMethodsModel = new DefaultListModel();
    private JScrollPane jListScrollPane = new JScrollPane(lstMethods);
    // Inhalt des Parameterpanels 1
    private JTextField tParameters1 = new JTextField();
    // Inahlt des Parameterpanels 2
    private JTextField tParameters2 = new JTextField();

    // Andere Attribute
    private List myList;
    private ListGUI andereGUI;
    private java.util.Timer gTimer;
    private java.util.TimerTask gTimerTask;

    /**
     * Ein Konstruktor ohne Parameter<br>
     * Intern wird ein anderer Konstruktor aufgerufen,
     * der einen Parameter erwartet. Diesem wird der
     * Parameter 0 uebergeben.
     */
    public ListGUI() {
        this(0);
    }

    /**
     * Ein Konstruktor mit Parameter<br>
     * Der Parameter gibt an, wo die GUI auf dem
     * Bildschirm erscheinen soll.<br>
     * Wenn der Parameter 0 ist, erscheint die GUI in der Bildschirmmitte.<br>
     * Wenn der Parameter 1 ist, erscheint die GUI oben auf dem Bildschirm.<br>
     * Wenn der Parameter 2 ist, erscheint die GUI unten auf dem Bildschirm.
     * 
     * @param nr die Kennzahl fuer die Bildschirmposition
     */
    public ListGUI(int nr) {
        /*
         * In diesem Konstruktor werden alle Fensterelemente initialisiert.
         * Der Konstrutor ist mithilfe des Java-Editors erstellt worden.
         * Die meisten Befehle sprechen fuer sich und werden daher nicht
         * weiter kommentiert.
         */

        // Frame-Initialisierung
        super("ListGUI");
        nr %= 3;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.addWindowListener(new MyWindowAdapter());
        int frameWidth = 1000; 
        int frameHeight = 400;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = 165;
        int y = 0;
        if (nr == 0) {
            y = (d.height - getSize().height) / 2;  
        }
        else {
            y = 10 + 415*(nr-1);
        }
        setLocation(x, y);
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        //-------------------
        // Anfang Komponenten
        //-------------------

        /*
         * Das Kommandopanel
         */
        panCommands.setBounds(10, 10, 125, 350);
        panCommands.setOpaque(false);
        panCommands.setBorder(new javax.swing.border.LineBorder(new Color(0xC0C0C0)));
        cp.add(panCommands);

        // Die Checkbox zum Ein- und Ausschalten der Inhaltsobjektdarstellung
        chkVisibleObjects.setBounds(10, 5, 105, 40);
        chkVisibleObjects.setOpaque(false);
        chkVisibleObjects.setFont(new Font("Arial", Font.BOLD, 10));
        chkVisibleObjects.setText("<html><center>Inhaltsobjekte verbergen</html>");
        chkVisibleObjects.addItemListener(new ItemListener() { 
                public void itemStateChanged(ItemEvent evt) { 
                    chkVisibleObjects_ItemStateChanged(evt);
                }
            });
        panCommands.add(chkVisibleObjects);

        // Das Panel zur Darstellung von Methodenergebnissen
        panErgebnis.setBounds(10, 50, 105, 155);
        panErgebnis.setOpaque(false);
        panErgebnis.setBorder(BorderFactory.createTitledBorder("Ergebnis"));
        panCommands.add(panErgebnis);

        // Das Textfeld zur Darstellung von Methodenergebnissen
        tErgebnis.setBounds(10, 24, 85, 25);
        tErgebnis.setOpaque(false);
        tErgebnis.setHorizontalAlignment(SwingConstants.CENTER);
        tErgebnis.setEditable(false);
        panErgebnis.add(tErgebnis);

        // Das Objektpanel zur zeichnerischen Darstellung von Methodenergebnissen
        panObject.setBounds(10, 60, 85, 85);
        panObject.setOpaque(false);
        panObject.setBorder(new javax.swing.border.LineBorder(new Color(0xC0C0C0)));
        panErgebnis.add(panObject);

        // Der Button zum Loeschen des Methodenergebnisses
        bErgebnisLoeschen.setBounds(77, 0, 18, 18);
        bErgebnisLoeschen.setOpaque(false);
        bErgebnisLoeschen.setText("x");
        bErgebnisLoeschen.setMargin(new Insets(2,2,2,2));
        bErgebnisLoeschen.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    bErgebnisLoeschen_ActionPerformed(evt);
                }
            });
        bErgebnisLoeschen.setFont(new Font("Arial", Font.BOLD, 14));
        panErgebnis.add(bErgebnisLoeschen);

        // Der Spinner zur Wahl der Darstellungsgroesse
        spGroesse.setBounds(10, 215, 50, 30);
        spGroesse.setOpaque(false);
        String[] values = new String[] {"5","7","10","14","20"};
        SpinnerModel model = new SpinnerListModel(values);
        spGroesse.setModel(model);
        spGroesse.setFont(new Font("Arial", Font.BOLD, 24));
        spGroesse.addChangeListener(new ChangeListener() { 
                public void stateChanged(ChangeEvent evt) { 
                    spGroesse_StateChanged(evt);
                }
            });
        panCommands.add(spGroesse);

        // Der Scroller zum Scrollen der Knotendarstellung
        scroller.setBounds(10, 250, 105, 30);
        scroller.setOpaque(false);
        scroller.setOrientation(Scrollbar.HORIZONTAL);
        scroller.addAdjustmentListener(new AdjustmentListener() { 
                public void adjustmentValueChanged(AdjustmentEvent evt) { 
                    scroller_AdjustmentValueChanged(evt);
                }
            });
        scroller.setValues(0,5,0,5);
        panCommands.add(scroller);

        // Die Checkbox zum Ein- und Ausschalten des Auto-Refresh
        chkAutoRefresh.setBounds(6, 285, 105, 30);
        chkAutoRefresh.setOpaque(false);
        chkAutoRefresh.setFont(new Font("Arial", Font.BOLD, 12));
        chkAutoRefresh.setText("auto refresh");
        chkAutoRefresh.setOpaque(false);
        chkAutoRefresh.addItemListener(new ItemListener() { 
                public void itemStateChanged(ItemEvent evt) { 
                    chkAutoRefresh_ItemStateChanged(evt);
                }
            });
        panCommands.add(chkAutoRefresh);

        // Der Button refresh
        bRefresh.setBounds(10, 310, 105, 30);
        bRefresh.setOpaque(false);
        bRefresh.setText("refresh");
        bRefresh.setMargin(new Insets(2, 2, 2, 2));
        bRefresh.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    bRefresh_ActionPerformed(evt);
                }
            });
        panCommands.add(bRefresh);

        /*
         * Das Methodenpanel
         */

        panMethods.setBounds(800, 10, 185, 350);
        panMethods.setOpaque(false);
        panMethods.setBorder(new javax.swing.border.LineBorder(new Color(0xC0C0C0)));
        cp.add(panMethods);

        // Der Button zur Erzeugung der Liste, der Queue, des Stack, etc.
        bNewList.setBounds(10, 10, 80, 30);
        bNewList.setOpaque(false);
        bNewList.setText("new ...");
        bNewList.setMargin(new Insets(2, 2, 2, 2));
        bNewList.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    bNewList_ActionPerformed(evt);
                }
            });
        panMethods.add(bNewList);

        // Der Button zum Loeschen der Liste, der Queue, des Stack, etc.
        bListNull.setBounds(95, 10, 80, 30);
        bListNull.setOpaque(false);
        bListNull.setText("... = null");
        bListNull.setMargin(new Insets(2, 2, 2, 2));
        bListNull.addActionListener(new 
            ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    bListNull_ActionPerformed(evt);
                }
            });
        panMethods.add(bListNull);

        // Die Combobox, in der alle Klassen angezeigt werden.
        cBLists.setBounds(10, 45, 165, 30);
        cBLists.setOpaque(false);
        cBLists.setModel(new DefaultComboBoxModel(gibListKlassen()));
        panMethods.add(cBLists);

        // Die ScrollPane, in welche die Liste der Methodensignaturen gezeigt wird
        jListScrollPane.setBounds(10, 85, 165, 255);
        jListScrollPane.setOpaque(false);
        panMethods.add(jListScrollPane);

        // Die Liste zur Darstellung aller Methodensignaturen
        lstMethods.setModel(lstMethodsModel);
        lstMethods.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        MouseListener mouseListener = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String b = (String) lstMethods.getSelectedValue();
                    if (b != null) {
                        interpretiere(b);
                    }
                }
            };
        lstMethods.addMouseListener(mouseListener);

        /*
         * Die zwei Panels mit den beiden Eingabezeilen
         */

        // Das erste Panel
        panParameters1.setBounds(145, 305, 320, 55);
        panParameters1.setOpaque(false);
        panParameters1.setBorder(BorderFactory.createTitledBorder("Parameter 1"));
        cp.add(panParameters1);

        // Das Textfeld der ersten Eingabezeile
        tParameters1.setBounds(10, 20, 300, 25);
        tParameters1.setOpaque(false);
        tParameters1.setHorizontalAlignment(SwingConstants.LEFT);
        panParameters1.add(tParameters1);

        // Das zweite Panel
        panParameters2.setBounds(470, 305, 320, 55);
        panParameters2.setOpaque(false);
        panParameters2.setBorder(BorderFactory.createTitledBorder("Parameter 2"));
        cp.add(panParameters2);

        // Das Textfeld der zweiten Eingabezeile
        tParameters2.setBounds(10, 20, 300, 25);
        tParameters2.setOpaque(false);
        tParameters2.setHorizontalAlignment(SwingConstants.LEFT);
        panParameters2.add(tParameters2);

        /*
         * Das Knotenpanel
         */
        panNodes.setBounds(145, 10, 645, 290);
        panNodes.setOpaque(false);
        panNodes.setBackground(new Color(239,228,176));
        panNodes.setBorder(new javax.swing.border.LineBorder(new Color(0xC0C0C0)));
        panNodes.setFont(new Font("Arial", Font.BOLD, 16));
        cp.add(panNodes);

        //-----------------
        // Ende Komponenten
        //-----------------

        setVisible(true);

        // Andere Attribute

        gTimer = new java.util.Timer();

    } // end of public ListGUI

    //----------------
    // Anfang Methoden
    //----------------

    /*
     * Die verschiedenen ActionListener werden implementiert.
     */

    /*
     * Eine neue leere Liste wird erzeugt und ihre
     * Methoden werden im Methodenpanel dargestellt.
     * Im Knotenpanel wird der Text "leere Liste" angezeigt.
     * Letzteres geschieht indirekt durch Aufruf der
     * repaint()-Methode des Knotenpanels.
     * Schliesslich werden eventuell noch angezeigte
     * Ergebnisse im Ergebnispanel geloescht.
     */
    private void bNewList_ActionPerformed(ActionEvent evt) {
        try{
            String listName = (String)(cBLists.getSelectedItem());
            Class listClass = Class.forName(listName); 
            List dieList = (List)(listClass.newInstance());
            listAnmelden(dieList);
        } 
        catch (ClassNotFoundException cNFE) {
            JOptionPane.showMessageDialog(this, cNFE.getMessage(), "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
        } 
        catch (InstantiationException iE) {
            JOptionPane.showMessageDialog(this, iE.getMessage(), "InstantiationException", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalAccessException iAE) {
            JOptionPane.showMessageDialog(this, iAE.getMessage(), "IllegalAccessException", JOptionPane.ERROR_MESSAGE);
        }
        // Paranoia
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "Fehler beim Ausfuehren des Befehls! " + e.getMessage(), "Unbekannter Fehler", JOptionPane.ERROR_MESSAGE);
        }
        panNodes.repaint();
        tErgebnis.setText("");
        for (Component comp : panObject.getComponents()) {
            panObject.remove(comp);
        }
        panObject.repaint();
    } // end of bNewList_ActionPerformed

    /*
     * Eine existierende Liste wird geloescht.
     * Dazu wird das entsprechnede Attribut gleich null gesetzt
     * und alle Methoden in der Methodenliste werden geloescht.
     * Im Kontenpanel werden alle Knoten geloescht.
     * Letzteres geschieht indirekt durch Aufruf der
     * repaint()-Methode des Knotenpanels.
     * Schliesslich werden eventuell noch angezeigte
     * Ergebnisse im Ergebnispanel geloescht.
     */
    private void bListNull_ActionPerformed(ActionEvent evt) {
        lstMethods.setListData((new ArrayList<String>()).toArray());
        myList = null;
        panNodes.repaint();
        tErgebnis.setText("");
        for (Component comp : panObject.getComponents()) {
            panObject.remove(comp);
        }
        panObject.repaint();
    } // end of bListNull_ActionPerformed

    /*
     * Wenn die Liste von einem externen Programm veraendert wird, 
     * wird sie im Knotenpanel nicht automatisch neu gezeichnet, 
     * wenn die Checkbox zum auto-refresh nicht markiert ist.
     * Mit einem Klick auf den Button refresh wird
     * die Methode repaint des ganzen Fensters 
     * aufgerufen und ein Neuzeichnen auch der Knoten
     * im Knotenpanel erzwungen.
     */
    private void bRefresh_ActionPerformed(ActionEvent evt) {
        this.repaint();
    } // end of bRefresh_ActionPerformed

    /*
     * Die Darstellungen des Methodenergebnisses
     * im Ergebnisfeld und im Objektpanel werden geloescht.
     */
    private void bErgebnisLoeschen_ActionPerformed(ActionEvent evt) {
        tErgebnis.setText("");
        for (Component comp : panObject.getComponents()) {
            panObject.remove(comp);
        }
        panObject.repaint();
    } // end of bErgebnisLoeschen_ActionPerformed

    /*
     * Wenn diese Checkbox markiert ist, werden die Inhaltsobjekte 
     * der Knoten nicht dargestellt.
     * Die Entscheidung, ob die Inhalsobjekte dargestellt oder nicht
     * dargestellt werden, wird in der paintComponent()-Methode
     * des Knotenpanels (panNodes) getroffen. Dort wird der
     * Zusstand der Checkbox abgefragt. 
     * Mit dieser Methode wird lediglich das Neuzeichnen der
     * Knoten durch Aufruf der repaint()-Methode erzwungen.
     */
    private void chkVisibleObjects_ItemStateChanged(ItemEvent evt) {
        panNodes.repaint();
    } // end of chkVisibleObjects_ItemStateChanged

    /*
     * Wenn der zugehoerige Scroller veraendert wird, 
     * wird die Anzeige der Liste im Knotenpanel gescrollt 
     * und neu dargestellt.
     * Das eigentliche Scrollen und die Auswahl der zu
     * zeichnenden Knoten wird in der paintComponent()-Methode
     * des Knotenpanels (panNodes) vorgenommen.
     * Mit dieser Methode wird lediglich das Neuzeichnen der
     * Knoten durch Aufruf der repaint()-Methode erzwungen.
     */
    private void scroller_AdjustmentValueChanged(AdjustmentEvent evt) {
        panNodes.repaint();
    } // end of scroller_AdjustmentValueChanged

    /*
     * In dem zugehoerigen Spinner wird die Darstellungsgroesse eingestellt.
     * Bei jeder Aenderung am Spinner, werden die Inhaltsobjekte neu dargestellt.
     * Die Entscheidung, wie gross die Inhalsobjekte dargestellt werden,
     * wird in der paintComponent()-Methode des Knotenpanels (panNodes) getroffen. 
     * Dort wird der angezeigte Inhalt des Spinner abgefragt. 
     * Mit dieser Methode wird lediglich das Neuzeichnen der
     * Knoten durch Aufruf der repaint()-Methode erzwungen.
     */
    private void spGroesse_StateChanged(ChangeEvent evt) {
        panNodes.repaint();
    } // end of spGroesse_StateChanged

    /*
     * Wenn diese Checkbox markiert wird,
     * wird alle 100 ms automatisch ein refresh aufgerufen.
     * Dies geschieht durch eine Timer-Task, welche alle 100 ms
     * ein Neuzeichnen des Knotenpanels erzwingt.
     * Wenn die Markierung der Checkbox wieder geloescht wird, 
     * wird die Timer-Task beendet.
     */
    private void chkAutoRefresh_ItemStateChanged(ItemEvent evt) {
        if (chkAutoRefresh.isSelected()) {
            bRefresh.setEnabled(false);
            gTimerTask = new GTimerTask(panNodes);
            gTimer.schedule(gTimerTask,100,100);
        } else {
            bRefresh.setEnabled(true);
            gTimerTask.cancel();
        }
    } // end of chkAutoRefresh_ItemStateChanged

    /*
     * Die Methode gibt die Namen aller Klassen, die von List erben
     * und sich im Projektordner befinden als Stringarray zurueck.
     * 
     * @return ein Stringarray von Klassennamen
     */
    private String[] gibListKlassen() {
        File fileDir = new File(".");
        String[] fileNames = fileDir.list();
        ArrayList<String> listNames = new ArrayList<String>();
        listNames.add("List");
        for (int i = 0; i < fileNames.length; i++) {
            if (fileNames[i].endsWith(".class")) {
                try {
                    fileNames[i]=fileNames[i].replace(".class","");
                    Class cls = Class.forName(fileNames[i]);
                    if (erbtVonList(cls)) {
                        if (!fileNames[i].equals("List")) {
                            listNames.add(fileNames[i]);
                        } 
                    }
                } 
                catch (ClassNotFoundException cNFE) {
                    JOptionPane.showMessageDialog(null, cNFE.getMessage(), "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
                }
                //Just Paranoia
                catch (Exception e){
                    JOptionPane.showMessageDialog(this, "Fehler beim Ausfuehren des Befehls! " + e.getMessage(), "Unbekannter Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        String[] listKlassen = new String[listNames.size()];
        for (int i = 0; i < listNames.size(); i++) {
            listKlassen[i]=listNames.get(i);
        }
        return(listKlassen);
    }

    /*
     * In dieser Methode wird der als Parameter uebergebene String
     * interpretiert. Es handelt sich um einen Stringdarstellung
     * der im Methodenpanel angelickten Methode. Per Reflection
     * wird die entsprechende Methode ausgefuehrt. Die Parameter
     * der Methode werden aus den beiden Textfeldern bestimmt.
     *
     * @param ausdruck ein Methoden-String
     */ 
    private void interpretiere(String ausdruck){
        /*
         * Der Parameterstring wird in einzelne Tokens zerlegt.
         * Trennzeichen der Zerlegung sind die drei Zeichen ,()
         */
        StringTokenizer st = new StringTokenizer(ausdruck,",()");
        String[] s = new String[10]; //maximal 9 Parameter
        int anz = 0;
        while (st.hasMoreTokens()) {
            s[anz]=st.nextToken();
            anz++;
        }

        // Aus dem 0-ten Token wird der Methodenname und der Rueckgabetyp bestimmt.
        String methodName = s[0].substring(s[0].indexOf(' ')+1);
        String returnType = s[0].substring(0,s[0].indexOf(' ')); 

        /*
         * klasse wird aus myList.getClass() bestimmt,
         * ist also die Klasse der einfach verketteten Liste.
         * types ist ein Array, welches die Parametertypen der Methode aufnimmt.
         * arguments ist ein Array, welches die Parameter-Objekte der Methode aufnimmt.
         */  
        Class klasse=null;
        Class[] types;
        Object[] arguments;
        try { 
            /*
             * Die verwendbaren Klassen fuer die Methodenparameter sind:
             * 1. Die Wrapperklassen der primitiven Datentypen
             *    Boolean, Character, Byte, Short, Integer, long, float und double
             * 2. Die Stringklasse
             * 3. Die Klasse Object
             * 4. Die Klasse List und alle davon abgeleitete Klassen
             */

            Class cBoolean = Boolean.TYPE;
            Class cCharacter = Character.TYPE;
            Class cByte = Byte.TYPE;
            Class cShort = Short.TYPE;
            Class cInteger = Integer.TYPE;
            Class cLong = Long.TYPE;
            Class cFloat = Float.TYPE;
            Class cDouble = Double.TYPE;
            Class cString = Class.forName("java.lang.String");
            Class cObject = Class.forName("java.lang.Object");
            Class cList = Class.forName("List");
            klasse = myList.getClass();

            // Maximal 2 Methodenparameter sind erlaubt.
            int parameteranzahl = anz-1;
            if (parameteranzahl > 2) {
                JOptionPane.showMessageDialog(this, "Erlaubt sind maximal 2 Parameter", "Fehler", JOptionPane.ERROR_MESSAGE);   
            } // end of if

            // Hier werden die Arrays types und arguments initialisiert
            // Immer daran denken: Evtl. auftretende Exceptions werden weiter unten gefangen.
            types = new Class[parameteranzahl];
            arguments = new Object[parameteranzahl];

            // types und arguments bestimmen
            for (int k = 0; k < parameteranzahl; k++){
                // mindestens ein Parameter, ab index 1, index 0 ist der Methodenname
                if (s[k+1].equals("boolean")) {
                    types[k] = cBoolean;
                    if (k+1 == 1) {
                        arguments[k] = new Parser().parseBoolean(tParameters1.getText().trim());
                    } // end of if
                    else {
                        arguments[k] = new Parser().parseBoolean(tParameters2.getText().trim());
                    } // end of if-else
                } // end of if
                else if (s[k+1].equals("char")) {
                    types[k] = cCharacter;
                    if (k+1 == 1) {
                        arguments[k] = new Parser().parseCharacter(tParameters1.getText());
                    } // end of if
                    else {
                        arguments[k] = new Parser().parseCharacter(tParameters2.getText());
                    } // end of if-else
                } // end of if
                else if (s[k+1].equals("byte")) {
                    types[k] = cByte;
                    if (k+1 == 1) {
                        arguments[k] = new Parser().parseByte(tParameters1.getText().trim());
                    } // end of if
                    else {
                        arguments[k] = new Parser().parseByte(tParameters2.getText().trim());
                    } // end of if-else
                } // end of if
                else if (s[k+1].equals("short")) {
                    types[k] = cShort;
                    if (k+1 == 1) {
                        arguments[k] = new Parser().parseShort(tParameters1.getText().trim());
                    } // end of if
                    else {
                        arguments[k] = new Parser().parseShort(tParameters2.getText().trim());
                    } // end of if-else
                } // end of if
                else if (s[k+1].equals("int")) {
                    types[k] = cInteger;
                    if (k+1 == 1) {
                        arguments[k] = new Parser().parseInteger(tParameters1.getText().trim());
                    } // end of if
                    else {
                        arguments[k] = new Parser().parseInteger(tParameters2.getText().trim());
                    } // end of if-else
                } // end of if
                else if (s[k+1].equals("long")) {
                    types[k] = cLong;
                    if (k+1 == 1) {
                        arguments[k] = new Parser().parseLong(tParameters1.getText().trim());
                    } // end of if
                    else {
                        arguments[k] = new Parser().parseLong(tParameters2.getText().trim());
                    } // end of if-else
                } // end of if
                else if (s[k+1].equals("float")) {
                    types[k] = cFloat;
                    if (k+1 == 1) {
                        arguments[k] = new Parser().parseFloat(tParameters1.getText().trim());
                    } // end of if
                    else {
                        arguments[k] = new Parser().parseFloat(tParameters2.getText().trim());
                    } // end of if-else
                } // end of if
                else if (s[k+1].equals("double")) {
                    types[k] = cDouble;
                    if (k+1 == 1) {
                        arguments[k] = new Parser().parseDouble(tParameters1.getText().trim());
                    } // end of if
                    else {
                        arguments[k] = new Parser().parseDouble(tParameters2.getText().trim());
                    } // end of if-else
                } // end of if
                else if(s[k+1].equals("String")){
                    types[k] = cString;
                    if (k+1 == 1) {
                        arguments[k] = tParameters1.getText();
                    } // end of if
                    else {
                        arguments[k] = tParameters2.getText();
                    } // end of if-else
                }
                else if(s[k+1].equals("Object")){
                    types[k] = cObject;
                    String arg;
                    if (k+1 == 1) {
                        arg = tParameters1.getText();
                    } // end of if
                    else {
                        arg = tParameters2.getText();
                    } // end of if-else
                    if (arg.trim().equals("null")) {
                        arguments[k] = null;
                    }
                    else if (arg.trim().startsWith("new")) {
                        // Es wird versucht, eine neue Instanz zu bilden.
                        arguments[k] = new Parser().parseNewObjectString(arg.trim());
                    }
                    else {
                        arguments[k] = arg;
                    }
                }
                else if(s[k+1].equals("List")){
                    /*
                     * Achtung! Der Parameter List nimmt eine
                     * Sonderstellung ein. Folgendes ist in dieser
                     * Reihenfolge zu beachten:
                     * 1. Wenn im entsprechenden Textfenster null
                     *    steht, wird auch null als Parameter durchgereicht.
                     * 2. Sonst, wenn im entsprechenden Textfenster new ...
                     *    steht, wird versucht, eine neue List anzulegen
                     *    und als Parameter durchzureichen.
                     * 3. Sonst, wenn keine andere GUI angemeldet ist, wird
                     *    als Parameter null durchgereicht.
                     * 4. Sonst wird von der anderen GUI die List
                     *    geholt und als Parameter durchgereicht.
                     */
                    types[k] = cList;
                    String arg;
                    if (k+1 == 1) {
                        arg = tParameters1.getText();
                    } // end of if
                    else {
                        arg = tParameters2.getText();
                    } // end of if-else
                    if (arg.trim().equals("null")) {
                        arguments[k] = null;
                    }
                    else if (arg.trim().startsWith("new")) {
                        // Es wird versucht, eine neue Instanz zu bilden.
                        arguments[k] = new Parser().parseNewObjectString(arg.trim());
                    }
                    else if (andereGUI == null) {
                        arguments[k] = null;
                    } // end of if
                    else {
                        arguments[k] = andereGUI.gibMyList();
                    } // end of if-else
                }
                else {
                    JOptionPane.showMessageDialog(this, "Erlaubte Parameter sind zur Zeit: alle primitiven Datentypen, String, Object und List", "Fehler", JOptionPane.ERROR_MESSAGE);   
                }
            } // end of for

            /*
             * Jetzt wird die gewuenschte Methode mit den zugehoerigen
             * Parametern aufgerufen. Wenn die Methode ein
             * Rueckgabeobjekt hat, wird dieses mithilfe seiner 
             * Methode toString im Ergebnis-Textfeld dargestellt.
             * Wenn darueber hinaus die Objektklasse oder ein ihrer
             * Superklassen das Interface IPaintable implementiert,
             * wird das Objekt in einem Panel unter dem Ergebnis-
             * Textfeld dargestellt.
             */
            Method methode=klasse.getMethod(methodName,types);
            Object returned = methode.invoke(myList, arguments);
            // Falls eine Methode ein Anfrage ist:
            if (!returnType.equals("void")) {
                // Falls das Methodenergebnis null ist
                if (returned == null) {
                    tErgebnis.setText("null");
                    for (Component comp : panObject.getComponents()) {
                        panObject.remove(comp);
                    }
                    panObject.repaint();
                }// end of if
                // Falls das Methodenergebnis nicht null ist:
                else {
                    tErgebnis.setText(returned.toString());
                    /* 
                     * Nachsehen, ob die Klasse des Objekts oder eine ihrer
                     * Superklassen das Interface IPaintable implementiert.
                     */
                    Class c = returned.getClass();
                    boolean implementsPaintable = false;
                    while (c != null && !implementsPaintable) { 
                        for (Class inter : c.getInterfaces()) {
                            if (inter.getName().equals("IPaintable")) {
                                implementsPaintable = true;
                                break;
                            }
                        }
                        c = c.getSuperclass();
                    }   
                    /*
                     * Falls das Interface IPaintable implementiert wird,
                     * wird ein JContentPanel zur Darstellung verwendet.
                     */
                    if (implementsPaintable) {
                        for (Component comp : panObject.getComponents()) {
                            panObject.remove(comp);
                        }
                        JContentPanel jCP = new JContentPanel();
                        jCP.setBounds(0,0,85,85);
                        jCP.setBackground(Color.lightGray);
                        jCP.setBorder(new javax.swing.border.LineBorder(Color.black));
                        jCP.setVisibility(true);
                        panObject.add(jCP);
                        jCP.zeichne(returned);

                    } // end of if
                    else {
                        for (Component comp : panObject.getComponents()) {
                            panObject.remove(comp);
                        }
                        panObject.repaint();
                    }
                }// end of if-else

            } // end of if
        }
        catch (ParserException pE) {
            JOptionPane.showMessageDialog(this, pE.getMessage(), "ParserException", JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException cNFE) {
            JOptionPane.showMessageDialog(this, cNFE.getMessage(), "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
        }
        catch (NoSuchMethodException nSME) {
            JOptionPane.showMessageDialog(this, nSME.getMessage(), "NoSuchMethodException", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalAccessException iAE) {
            JOptionPane.showMessageDialog(this, iAE.getMessage(), "IllegalAccessException", JOptionPane.ERROR_MESSAGE);
        }
        catch (InvocationTargetException iTE) {
            JOptionPane.showMessageDialog(this, iTE.getMessage(), "InvocationTargetException", JOptionPane.ERROR_MESSAGE);
        }
        //Just Paranoia
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "Fehler beim Ausfuehren des Befehls! " + e.getMessage(), "Unbekannter Fehler", JOptionPane.ERROR_MESSAGE);
        }
        panNodes.repaint(); 
        if (andereGUI != null) {
            andereGUI.panNodes.repaint();
        } // end of if
        tParameters1.setText("");
        tParameters2.setText("");
    }//interpretiere

    /**
     * Mit dieser Methode kann eine andere Instanz der
     * Klasse ListGUI angemeldet werden.<br>
     * Damit ist es moeglich, zwei Listen zu verwalten
     * und mithilfe der Methode concat zu verknuepfen.
     * 
     * @param gui die andere GUI
     */
    public void guiAnmelden(ListGUI gui) {
        andereGUI = gui;
    }

    /**
     * Mit dieser Methode kann eine extern erzeugte
     * Liste angemeldet werden.<br>
     * Die Liste wird in dem Attribut myList gespeichert.<br>
     * Die Methoden der Implementation werden eingelesen und 
     * im Methodenpanel dargestellt.
     * 
     * @param list die Liste, die angemeldet werden soll.
     */

    public void listAnmelden(List list) { 
        myList = list;
        ArrayList<String> listdata = new ArrayList<String>();
        lstMethods.setListData(listdata.toArray());
        Class classOfMyList = myList.getClass();
        if (erbtVonList(classOfMyList)) {
            cBLists.setSelectedItem(classOfMyList.getName());
            ArrayList<Method> methods = getMethods(classOfMyList);
            Method[] methoden = new Method[methods.size()];
            for (int i = 0; i < methods.size(); i++) {
                methoden[i] = methods.get(i);
            }
            for(int i=0; i<methoden.length;i++){
                String methode = methoden[i].toString();
                String klasse = methoden[i].getDeclaringClass().getName();
                methode = methode.replaceFirst(klasse + ".","");
                methode = methode.replaceFirst("public ","");
                methode = methode.replaceAll("java.lang.","");
                methode.trim();
                if (!listdata.contains(methode)){
                    listdata.add(methode);
                }
            }//for
            Collections.sort(listdata);
            lstMethods.setListData(listdata.toArray());
        }
        panNodes.repaint();
        tErgebnis.setText("");
        for (Component comp : panObject.getComponents()) {
            panObject.remove(comp);
        }
        panObject.repaint();
    }

    private boolean erbtVonList(Class thisClass) {
        if (thisClass == null) {
            return false;
        } else if (thisClass.getName().equals("List")) {
            return true;
        } else if (thisClass.getName().equals("java.lang.Object")) {
            return false;
        } else {
            return erbtVonList(thisClass.getSuperclass());
        }
    }

    private ArrayList<Method> getMethods(Class thisClass) {
        if (thisClass.getName().equals("List")) {
            ArrayList<Method> methods = new ArrayList<Method>();
            Method[] meth = thisClass.getDeclaredMethods(); 
            for(Method m : meth) {
                if (!m.toString().contains("private")) {
                    methods.add(m);
                }
            }
            return methods;
        } else {
            ArrayList<Method> methods = getMethods(thisClass.getSuperclass());
            Method[] meth = thisClass.getDeclaredMethods(); 
            for(Method m : meth) {
                if (!m.toString().contains("private")) {
                    methods.add(m);
                }
            }
            return methods;
        }
    }

    /**
     * Das ganze GUI-Fenster wird neu gezeichnet.<br>
     * Damit wird auch die Darstellung im Knotenpanel erneuert.
     */
    public void refresh() {
        this.repaint();    
    }

    /**
     * Die einfach verkettete Liste dieser GUI wird zurueck gegeben.
     * 
     * @return die Liste
     */
    public List gibMyList() {
        return(myList);
    }

    // Ende Methoden

    /**
     * Die main-Methode ruft den parameterlosen Konstruktor
     * der Klasse ListGUI auf. Das Fenster erscheint in der
     * Mitte des Bildschirms.
     */
    public static void main(String[] args) {
        new ListGUI();
    } // end of main

    //------------------------------------------------------
    // Die inneren Klassen:
    //------------------------------------------------------
    // MyWindowAdapter extends WindowAdapter
    // JNodePanel extends JPanel
    // GTimerTask extends java.util.TimerTask
    //------------------------------------------------------

    /*
     * Vor dem Schliessen des Fensters soll in einem eventuell
     * vorhandenem anderen Fenster dieses Fenster abgemeldet werden.
     * Eine Instanz dieses WindowsAdapters wird als WindowListener
     * in das JFrame eingefuegt.
     */
    private class MyWindowAdapter extends WindowAdapter {
        public void windowClosing( WindowEvent event )
        {
            if (andereGUI != null) {
                andereGUI.guiAnmelden(null);
            } // end of if

            if (chkAutoRefresh.isSelected()) {
                chkAutoRefresh.setSelected(false);
                chkAutoRefresh.doClick();
                bRefresh.setEnabled(true);
                gTimerTask.cancel();
            } else {
                bRefresh.setEnabled(true); //redundant
            }
        } 
    }

    /*
     * JNodePanel - hier wird gezeichnet
     */
    private class JNodePanel extends JPanel
    {
        /*
         * Die Methode paintComponent muss ueberschrieben werden.
         * Die eigentlichen Zeichenfunktionen sind in weitere
         * Methoden ausgelagert.
         */
        protected void paintComponent(Graphics g) {
            for (Component comp : getComponents()) {
                remove(comp);
            }
            g.setColor(new Color(239,228,176));
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            if (myList != null) {
                if (myList.current == null && myList.first == null) {
                    g.setColor(Color.black);
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    int x = this.getWidth() / 2 - 200;
                    int y = this.getHeight() / 2;
                    g.drawString("Leere Liste ohne Dummy-Elemente",x,y);
                } // end of if
                else {
                    zeigeListe(g);
                } // end of if-else
            } // end of if
            else {
                g.setColor(Color.black);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                int x = this.getWidth() / 2 - 75;
                int y = this.getHeight() / 2;
                g.drawString("Keine Liste",x,y);
            } // end of if-else
        }

        /*
         * Ein einzelner Knoten wird gezeichnet.
         * Die Position des Knotens auf dem Panel wird aus
         * seiner Position im ArrayList<node> nodes und
         * aus dem Wert des Scrollers berechnet. 
         * Die Darstellungsgroesse wird durch den Wert 
         * des Spinners bestimmt.
         */
        private void zeigeNode(List.ListNode node, ArrayList<List.ListNode> nodes, double faktor, Graphics g) {
            int startx = 20 + (nodes.indexOf(node)-scroller.getValue())*(int)(125*faktor);
            int starty = this.getHeight()-50;
            /*
             * Verschiebungen nach oben oder unten
             * deltaYKn verschiebt die Knoten, deltaYIn die Inhaltsobjekte.
             * Die Verschiebung ist in Pixeln bei Darstellungsfaktor 1 angegeben.
             * Fuer andere Darstellungsfaktoren werden die Verschiebungen entsprechend skaliert.
             */
            int deltaYKn = -10;
            int deltaYIn = 0;

            /*
             * Die Knoten werden gezeichnet.
             * Die Berechnungen der Postitionen sind meist selbsterklaerend.
             */

            // Hintergrund fuer besondere Knoten (tail?)/(Dummy?)
            if (node.getContentObject() == null) { 
                if (node.getNextNode() == null) { // Liste ohne tail - Listenende
                    g.setColor(new Color(255,174,201));
                    g.fillRect(startx-(int)(0*faktor),starty-(int)(90*faktor)+(int)(deltaYKn*faktor),(int)(101*faktor),(int)(90*faktor));
                    g.setColor(Color.black);
                    g.setFont(new Font("Arial", Font.BOLD, (int)(16*faktor)));
                    g.drawString("Dummy?",startx+(int)(20*faktor),starty-(int)(74*faktor)+(int)(deltaYKn*faktor));
                }
                else if (node.getNextNode().getNextNode() == node) { // Liste mit tail - Dummyelement tail
                    g.setColor(new Color(255,174,201));
                    g.fillRect(startx-(int)(0*faktor),starty-(int)(90*faktor)+(int)(deltaYKn*faktor),(int)(101*faktor),(int)(90*faktor));
                    g.setColor(Color.black);
                    g.setFont(new Font("Arial", Font.BOLD, (int)(16*faktor)));
                    g.drawString("Dummy?",startx+(int)(20*faktor),starty-(int)(74*faktor)+(int)(deltaYKn*faktor));    
                }
            } // end of if

            // Der ganze Knoten
            if (myList.current == node) {
                g.setColor(new Color(255,127,39));  // current (orange)
            } // end of if
            else {
                g.setColor(new Color(255,242,0));  //  andere (gelb)
            } // end of if-else
            g.fillRect(startx,starty-(int)(70*faktor)+(int)(deltaYKn*faktor),(int)(100*faktor),(int)(70*faktor));
            // Der Teil unten rechts
            g.setColor(new Color(153,217,234));
            g.fillRect(startx,starty-(int)(35*faktor)+(int)(deltaYKn*faktor),(int)(100*faktor),(int)(35*faktor));
            // Jetzt die Umrisse
            // Der ganze Knoten
            g.setColor(Color.black);
            g.drawRect(startx,starty-(int)(70*faktor)+(int)(deltaYKn*faktor),(int)(100*faktor),(int)(70*faktor));
            // Der Teil unten rechts
            g.drawRect(startx,starty-(int)(35*faktor)+(int)(deltaYKn*faktor),(int)(100*faktor),(int)(35*faktor));
            // Texte, falls noetig (null)
            if (node.getContentObject() == null) {
                g.setColor(Color.red);
                g.setFont(new Font("Arial", Font.BOLD, (int)(30*faktor)));
                g.drawString("null",startx+(int)(25*faktor),starty-(int)(40*faktor)+(int)(deltaYKn*faktor));
            } // end of if
            if (node.getNextNode() == null) {
                g.setColor(Color.red);
                g.setFont(new Font("Arial", Font.BOLD, (int)(24*faktor)));
                g.drawString("null",startx+(int)(55*faktor),starty-(int)(10*faktor)+(int)(deltaYKn*faktor));
            } // end of if

            // current wird markiert (Text "current" unter dem Knotenbild)
            if (node == myList.current) {
                g.setColor(Color.black);
                g.setFont(new Font("Arial", Font.BOLD, (int)(30*faktor)));
                g.drawString("current",startx+(int)(0*faktor),starty-(int)(-45*faktor));
            } // end of if

            // Das Inhaltsobjekt wird gezeichnet.
            Object inhalt = node.getContentObject();
            // Nur Inhaltsobjekte != null werden gezeichnet.
            if (inhalt != null) {
                // Hintergrund
                if (myList.current == node) {
                    g.setColor(new Color(255,127,39));  // current (Orange)
                } // end of if
                else {
                    g.setColor(new Color(255,242,0));  //  andere (Gelb)
                } // end of if-else
                /* 
                 * Nachsehen, ob die Klasse des Inhaltsobjekts oder eine
                 * der Superklassen das Interface IPaintable implementiert.
                 */
                Class c = inhalt.getClass();
                boolean implementsPaintable = false;
                while (c != null && !implementsPaintable) { 
                    for (Class inter : c.getInterfaces()) {
                        if (inter.getName().equals("IPaintable")) {
                            implementsPaintable = true;
                            break;
                        }
                    }
                    c = c.getSuperclass();
                }   
                /*
                 * Falls das Interface IPaintable implementiert wird,
                 * werden entsprechende JContentPanels verwendet
                 */
                if (implementsPaintable) {
                    try { 
                        JContentPanel jCP = new JContentPanel();
                        jCP.setBounds(startx,starty-(int)(220*faktor)+(int)(deltaYIn*faktor),(int)(100*faktor),(int)(100*faktor));
                        jCP.setBackground(g.getColor());
                        jCP.setBorder(new javax.swing.border.LineBorder(Color.black));
                        jCP.setVisibility(!chkVisibleObjects.isSelected());
                        this.add(jCP);
                        Method methode=inhalt.getClass().getMethod("contentPanelAnmelden",Class.forName("JContentPanel"));
                        methode.invoke(inhalt, jCP);
                        methode=inhalt.getClass().getMethod("zeige");
                        methode.invoke(inhalt);
                    }
                    catch (ClassNotFoundException cNFE) {
                        JOptionPane.showMessageDialog(this, cNFE.getMessage(), "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (NoSuchMethodException nSME) {
                        JOptionPane.showMessageDialog(this, nSME.getMessage(), "NoSuchMethodException", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (IllegalAccessException iAE) {
                        JOptionPane.showMessageDialog(this, iAE.getMessage(), "IllegalAccessException", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (InvocationTargetException iTE) {
                        JOptionPane.showMessageDialog(this, iTE.getMessage(), "InvocationTargetException", JOptionPane.ERROR_MESSAGE);
                    }
                    // Just Paranoia
                    catch (Exception e){
                        JOptionPane.showMessageDialog(this, "Fehler beim Ausfuehren des Befehls! " + e.getMessage(), "Unbekannter Fehler", JOptionPane.ERROR_MESSAGE);
                    }

                } // end of if
                /*
                 * Falls das Interface nicht implementiert wird, 
                 * werden die toString()-Methoden zur Darstellung verwendet.
                 */
                else {
                    g.fillRect(startx,starty-(int)(220*faktor)+(int)(deltaYIn*faktor),(int)(100*faktor),(int)(100*faktor));
                    g.setColor(Color.black);
                    g.drawRect(startx,starty-(int)(220*faktor)+(int)(deltaYIn*faktor),(int)(100*faktor),(int)(100*faktor));
                    if (!chkVisibleObjects.isSelected()) {
                        FontMetrics fm;
                        int w, h;
                        double v = 40.0;
                        g.setFont(new Font("Arial", Font.BOLD, (int)(v*faktor)));
                        fm = g.getFontMetrics();
                        w = fm.stringWidth(inhalt.toString());
                        h = fm.getHeight();
                        while (w > (int)(100*faktor) && (v*faktor) > 8.0) { 
                            v *= 0.75;
                            g.setFont(new Font("Arial", Font.BOLD, (int)(v*faktor)));
                            fm = g.getFontMetrics();
                            w = fm.stringWidth(inhalt.toString());
                            h = fm.getHeight();
                        }
                        // Zur Darstellung wird die toString()-Methode verwendet.
                        g.drawString(inhalt.toString(),startx+(int)((50*faktor)-w/2.0),starty-(int)(220*faktor)+(int)(50*faktor)+(int)(h/4.0)+(int)(deltaYIn*faktor));
                    } // end of if
                }
                // Bis hier sind Aenderungen fuer beliebige Objekte erforderlich.

                // Die Pfeile zu den Inhaltsobjekten
                if (myList.current == node) {
                    g.setColor(new Color(237,28,36));  // current (rot)
                } // end of if
                else {
                    g.setColor(new Color(255,127,38)); //  andere (orange)
                } // end of if-else
                g.fillOval(startx+(int)(43*faktor),starty-(int)(60*faktor)+(int)(deltaYKn*faktor),(int)(14*faktor),(int)(14*faktor));
                g.drawLine(startx+(int)(50*faktor),starty-(int)(53*faktor)+(int)(deltaYKn*faktor),startx+(int)(50*faktor),starty-(int)(120*faktor)+(int)(deltaYIn*faktor));
                int[] xKoors = new int[3];
                int[] yKoors = new int[3];
                xKoors[0] = startx+(int)(50*faktor);
                yKoors[0] = starty-(int)(120*faktor)+(int)(deltaYIn*faktor);
                xKoors[1] = xKoors[0] + (int)(7*faktor);
                yKoors[1] = yKoors[0] + (int)(14*faktor);
                xKoors[2] = xKoors[1] - (int)(14*faktor);
                yKoors[2] = yKoors[1];
                g.fillPolygon(xKoors,yKoors,3);
            } // end of if

            // Die Pfeile zu den Nachfolgern
            if (node.getNextNode() != null) {
                g.setColor(new Color(63,72,204)); // blau
                g.fillOval(startx+(int)(70*faktor),starty-(int)((35.0/4.0*2.0)*faktor)-(int)(5*faktor)+(int)(deltaYKn*faktor),(int)(10*faktor),(int)(10*faktor));
                List.ListNode nextNode = node.getNextNode();
                if (nodes.indexOf(nextNode) > nodes.indexOf(node)) { // alle "normalen" Knoten
                    int startNextx = 20 + (nodes.indexOf(nextNode)-scroller.getValue())*(int)(125*faktor);
                    int startNexty = this.getHeight()-50;
                    g.drawLine(startx+(int)(75*faktor),starty-(int)((35.0/4.0*2.0)*faktor)+(int)(deltaYKn*faktor),startNextx,startNexty-(int)(35.0/4.0*2.0*faktor)+(int)(deltaYKn*faktor));
                    int[] xKoors = new int[3];
                    int[] yKoors = new int[3];
                    xKoors[0] = startNextx;
                    yKoors[0] = startNexty-(int)(35.0/4.0*2.0*faktor)+(int)(deltaYKn*faktor);
                    xKoors[1] = xKoors[0] - (int)(14*faktor);
                    yKoors[1] = yKoors[0] + (int)(7*faktor);
                    xKoors[2] = xKoors[1];
                    yKoors[2] = yKoors[1]-(int)(14*faktor);
                    g.fillPolygon(xKoors,yKoors,3);
                }
                else { // Dummy-Knoten (tail?)
                    int startNextx = 20 + (nodes.indexOf(nextNode)-scroller.getValue())*(int)(125*faktor);
                    int startNexty = this.getHeight()-50;
                    g.drawLine(startx+(int)(75*faktor),starty+(int)(20.0*faktor)+(int)(deltaYKn*faktor),startNextx+(int)(25*faktor),startNexty+(int)(20.0*faktor)+(int)(deltaYKn*faktor));
                    g.drawLine(startx+(int)(75*faktor),starty-(int)((35.0/4.0*2.0)*faktor)+(int)(deltaYKn*faktor),startx+(int)(75*faktor),starty+(int)(20.0*faktor)+(int)(deltaYKn*faktor));
                    g.drawLine(startNextx+(int)(25*faktor),startNexty+(int)(20.0*faktor)+(int)(deltaYKn*faktor),startNextx+(int)(25*faktor),startNexty+(int)(deltaYKn*faktor));
                    //           int[] xKoors = new int[3];
                    //           int[] yKoors = new int[3];
                    //           xKoors[0] = startNextx;
                    //           yKoors[0] = startNexty-(int)(35.0/4.0*2.0*faktor)+(int)(deltaYKn*faktor);
                    //           xKoors[1] = xKoors[0] - (int)(14*faktor);
                    //           yKoors[1] = yKoors[0] + (int)(7*faktor);
                    //           xKoors[2] = xKoors[1];
                    //           yKoors[2] = yKoors[1]-(int)(14*faktor);
                    //           g.fillPolygon(xKoors,yKoors,3);  
                    int[] xKoors = new int[3];
                    int[] yKoors = new int[3];
                    xKoors[0] = startNextx+(int)(25*faktor);
                    yKoors[0] = startNexty+(int)(deltaYKn*faktor);
                    xKoors[1] = xKoors[0] + (int)(7*faktor);
                    yKoors[1] = yKoors[0] + (int)(14*faktor);
                    xKoors[2] = xKoors[1] - (int)(14*faktor);
                    yKoors[2] = yKoors[1];
                    g.fillPolygon(xKoors,yKoors,3);
                }
            } // end of if

        }

        /*
         * Die ganze Liste oder ein moeglichst grosser Teil sollen gezeichnet werden.
         * In dieser Methode wird der darstellbare Bereich berechnet und
         * anschliessend wird die Methode zeigeNode aufgerufen, um 
         * die einzelnen Knoten zu zeichnen.
         */
        private void zeigeListe(Graphics g) { 
            //Eine ArrayList zum Speichern der Knoten
            ArrayList<List.ListNode> nodes = new ArrayList<List.ListNode>();

            /*
             * Alle Knoten der Liste werden in dem
             * Arraylist<List.ListNode> nodes gespeichert.
             */ 
            List.ListNode travers = myList.first;
            nodes.add(travers);
            while (travers.getNextNode() != null && !nodes.contains(travers.getNextNode())) { 
                travers = travers.getNextNode();
                nodes.add(travers);
            }

            /*
             * Die Daten fuer den Scroller werden angepasst:
             * Das Maximum des Scrollermodells ist gleich
             * der Anzahl aller darzustellender Knoten.
             * Der Wert im Spinner (Anzahl gleichzeitig
             * darstellbarer Knoten) wird als Extent 
             * des Scrollers gesetzt.
             */
            scroller.getModel().setMaximum(nodes.size());
            int extent = Integer.parseInt((String)(spGroesse.getValue()));
            scroller.getModel().setExtent(extent);

            /*
             * Es wird der notwendige Faktor fuer die gewuenschte
             * Darstellungsgroesse berechnet. Der Unterschied von einer 
             * Groesse zu der naechsten Groesse ist der Faktor 1/sqrt(2).
             */
            double faktor = 1.0;
            if (extent == 5) {
                faktor = 1.0;
            } 
            else if (extent == 7) {
                faktor /= Math.sqrt(2.0);
            }
            else if (extent == 10){
                faktor /= 2.0;
            } 
            else if (extent == 14){
                faktor /= (2.0*Math.sqrt(2.0));
            }
            else if (extent == 20){
                faktor /= 4.0;
            }
            else {
                JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Darstellungsgroesse", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            /*
             * Jetzt werden die Knoten unter Beruecksichtigung ihrer
             * Darstellungsgroesse und dem Scrolloffset gezeichnet.
             */
            for (List.ListNode node : nodes) {
                if (nodes.indexOf(node)>=scroller.getValue()-1 && nodes.indexOf(node)<=scroller.getValue()+extent) {
                    zeigeNode(node, nodes, faktor, g);
                } // end of if
            } // end of for
        }
    } // end of inner class JNodePanel

    // inner class GTimerTask
    private class GTimerTask extends java.util.TimerTask {
        private JNodePanel nP;

        public GTimerTask(JNodePanel pNP) {
            super();
            nP = pNP;
        }

        public void run() {
            nP.repaint();
        }
    }
    // end of innter class GTimerTask

    // inner class Parser
    private class Parser {
        /**
         * Die Methode parst den als Parameter übergebenen String und 
         * erzeugt ein entsprechendes Objekt von Typ Boolean, falls möglich.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom Typ Boolean
         */
        public Object parseBoolean(String s) throws ParserException {
            Boolean erg = null;
            if (s.equalsIgnoreCase("true")){
                erg = true;
            } // end of if
            else if (s.equalsIgnoreCase("false")){
                erg = false; 
            } // end of if-else
            else {
                throw (new ParserException(s + " ist kein Boolean."));
            } // end of if-else
            return erg;
        }

        /**
         * Die Methode parst den als Parameter übergebenen String und 
         * erzeugt ein entsprechendes Objekt von Typ Character, falls möglich.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom Typ Character
         */
        public Object parseCharacter(String s) throws ParserException {
            Character erg = null;
            if (s != null && s.length() == 1) {
                erg = new Character(s.charAt(0));
            } // end of if
            else {
                throw (new ParserException(s + " ist kein Character."));
            } // end of if-else
            return (erg);
        }

        /**
         * Die Methode parst den als Parameter übergebenen String und 
         * erzeugt ein entsprechendes Objekt von Typ Byte, falls möglich.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom Typ Byte
         */
        public Object parseByte(String s) throws ParserException {
            Byte erg = null;
            try {
                erg = Byte.decode(s);
            } 
            catch (NumberFormatException e) {
                throw (new ParserException(s + " ist kein Byte."));
            }
            return (erg);
        }

        /**
         * Die Methode parst den als Parameter übergebenen String und 
         * erzeugt ein entsprechendes Objekt von Typ Short, falls möglich.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom Typ Short
         */
        public Object parseShort(String s) throws ParserException {
            Short erg = null;
            try {
                erg = Short.decode(s);
            } 
            catch (NumberFormatException e) {
                throw (new ParserException(s + " ist kein Short."));
            }
            return (erg);
        }

        /**
         * Die Methode parst den als Parameter übergebenen String und 
         * erzeugt ein entsprechendes Objekt von Typ Integer, falls möglich.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom Typ Integer
         */
        public Object parseInteger(String s) throws ParserException {
            Integer erg = null;
            try {
                erg = Integer.decode(s);
            } 
            catch (NumberFormatException e) {
                throw (new ParserException(s + " ist kein Integer."));
            }
            return (erg);
        }

        /**
         * Die Methode parst den als Parameter übergebenen String und 
         * erzeugt ein entsprechendes Objekt von Typ Long, falls möglich.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom Typ Long
         */
        public Object parseLong(String s) throws ParserException {
            Long erg = null;
            try {
                erg = Long.decode(s);
            } 
            catch (NumberFormatException e) {
                throw (new ParserException(s + " ist kein Long."));
            }
            return (erg);
        }

        /**
         * Die Methode parst den als Parameter übergebenen String und 
         * erzeugt ein entsprechendes Objekt von Typ Float, falls möglich.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom Typ Float
         */
        public Object parseFloat(String s) throws ParserException {
            Float erg = null;
            try {
                erg = new Float(s);
            } 
            catch (NumberFormatException e) {
                throw (new ParserException(s + " ist kein Float."));
            }
            return (erg);
        }

        /**
         * Die Methode parst den als Parameter übergebenen String und 
         * erzeugt ein entsprechendes Objekt von Typ Double, falls möglich.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom Typ Double
         */
        public Object parseDouble(String s) throws ParserException {
            Double erg = null;
            try {
                erg = new Double(s);
            } 
            catch (NumberFormatException e) {
                throw (new ParserException(s + " ist kein Double."));
            }
            return (erg);
        }

        /**
         * Die Methode parst den als Parameter übergebenen String und 
         * erzeugt ein entsprechendes Objekt von Typ Color, falls möglich.
         * Der String kann in Groß- und Kleinschreibung vorliegen.
         * Er muss von einer der beiden Formen "java.awt.Color.black" oder
         * der Kurzform "black" sein.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom Typ Color
         */
        public Object parseColor(String s) throws ParserException {
            java.awt.Color erg = null;
            if (s.equalsIgnoreCase("black")) {
                erg = java.awt.Color.black;
            } else if (s.equalsIgnoreCase("blue")) {
                erg = java.awt.Color.blue;
            } else if (s.equalsIgnoreCase("cyan")) {
                erg = java.awt.Color.cyan;
            } else if (s.equalsIgnoreCase("gray")) {
                erg = java.awt.Color.gray;
            } else if (s.equalsIgnoreCase("green")) {
                erg = java.awt.Color.green;
            } else if (s.equalsIgnoreCase("lightGray")) {
                erg = java.awt.Color.lightGray;
            } else if (s.equalsIgnoreCase("magenta")) {
                erg = java.awt.Color.magenta;
            } else if (s.equalsIgnoreCase("orange")) {
                erg = java.awt.Color.orange;
            } else if (s.equalsIgnoreCase("pink")) {
                erg = java.awt.Color.pink;
            } else if (s.equalsIgnoreCase("red")) {
                erg = java.awt.Color.red;
            } else if (s.equalsIgnoreCase("white")) {
                erg = java.awt.Color.white;
            } else if (s.equalsIgnoreCase("yellow")) {
                erg = java.awt.Color.yellow;
            } else if (s.equalsIgnoreCase("java.awt.Color.black")) {
                erg = java.awt.Color.black;
            } else if (s.equalsIgnoreCase("java.awt.Color.blue")) {
                erg = java.awt.Color.blue;
            } else if (s.equalsIgnoreCase("java.awt.Color.cyan")) {
                erg = java.awt.Color.cyan;
            } else if (s.equalsIgnoreCase("java.awt.Color.gray")) {
                erg = java.awt.Color.gray;
            } else if (s.equalsIgnoreCase("java.awt.Color.green")) {
                erg = java.awt.Color.green;
            } else if (s.equalsIgnoreCase("java.awt.Color.lightGray")) {
                erg = java.awt.Color.lightGray;
            } else if (s.equalsIgnoreCase("java.awt.Color.magenta")) {
                erg = java.awt.Color.magenta;
            } else if (s.equalsIgnoreCase("java.awt.Color.orange")) {
                erg = java.awt.Color.orange;
            } else if (s.equalsIgnoreCase("java.awt.Color.pink")) {
                erg = java.awt.Color.pink;
            } else if (s.equalsIgnoreCase("java.awt.Color.red")) {
                erg = java.awt.Color.red;
            } else if (s.equalsIgnoreCase("java.awt.Color.white")) {
                erg = java.awt.Color.white;
            } else if (s.equalsIgnoreCase("java.awt.Color.yellow")) {
                erg = java.awt.Color.yellow;
            }
            else {
                throw (new ParserException("Farbe " + s + " wurde nicht gefunden."));
            } // end of if-else
            return(erg);
        }

        /**
         * Die Methode parst den als Parameter übergebenen String
         * und interpretiert diesen als Klassenname.
         * Die Methode versucht ein entsprechendes Klassenobjekt 
         * zu erzeugen. Falls die zu Grunde liegende Klasse nicht 
         * gefunden wird, wirft die Methode eine Exception.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom Typ der durch den String beschriebenen Klasse
         */
        public Object parseClass(String s) throws ParserException {
            Class erg = null;
            try {
                erg = Class.forName(s);
            } 
            catch (ClassNotFoundException e) {
                throw (new ParserException("Klasse " + s + " wurde nicht gefunden"));
            }
            return (erg);
        }

        /**
         * Die Methode parst den als Parameter übergebenen String und 
         * erzeugt ein neues Objekt nach der Beschreibung des Strings.
         * Der String kann die Form "new <Klassenname>(Parameterliste)" haben.
         * Wenn in der Parameterliste wiederum ein neues Objekt 
         * mit "new <Klassenname2>(Parameterliste)" gebildet wird,
         * ruft sich die Methode rekursiv auf.
         * 
         * @param s der zu parsende String
         * @return ein Objekt vom durch den String beschriebenen Typ
         */
        public Object parseNewObjectString(String s) throws ParserException {
            Object erg = null;

            /* Zunächst wird geprüft, ob die Anzahl offener Klammern
             * gleich der Anzahl geschlossener Klammern ist.
             * Das letzte Zeichen des Strings muss eine geschlossene
             * Klammer sein. Damit ist auch gesichert, dass es
             * mindestens eine offene Klammer geben  muss.
             */
            char[] cA = s.toCharArray();
            int countAuf = 0;
            int countZu = 0;
            for (char c : cA) {
                if (c == '(') {
                    countAuf++;
                } // end of if
                if (c == ')') {
                    countZu++;
                } // end of if
            } // end of for
            if (countAuf > countZu) {
                throw (new ParserException("Zu viele offene Klammern in: " + s));
            } // end of if
            if (countAuf < countZu) {
                throw (new ParserException("Zu viele geschlossene Klammern in: " + s));
            } // end of if
            if (s.trim().lastIndexOf(')') != s.trim().length()-1) {
                throw (new ParserException("Fehler: Letztes Zeichen in " + s + " muss ) sein."));
            } // end of if

            // Der String, welcher den Klassennamen darstellt, wird bestimmt.
            String className = null;
            try { 
                className = s.substring(s.indexOf(" "),s.indexOf("(")).trim();
            } catch (IndexOutOfBoundsException iOBE) {
                throw (new ParserException("Fehler in der Signatur des Construktors: " + s));
            } // end of try

            // Die Klasse wird bestimmt.
            Class<?> theClass = (Class)parseClass(className);

            // Ein String, welcher die Parameteraufzählung enthält, wird bestimmt.
            String paramString = null;
            try { 
                paramString = s.substring(s.indexOf("(")+1,s.lastIndexOf(")")).trim();
            } catch(IndexOutOfBoundsException iOBE) {
                throw (new ParserException("Fehler: \")\" fehlt."));
            } // end of try

            // Wenn die Parameteraufzählung leer ist, sollte es einen Konstruktor ohne Parameter geben.
            if (paramString.length() == 0) {
                try { 
                    Constructor derConstructor = theClass.getConstructor();
                    return(derConstructor.newInstance());
                } catch(NoSuchMethodException nSME) {
                    throw (new ParserException("Fehler: Ein parameterloser Konstruktor für " + theClass.getName() + " existiert nicht."));
                } catch(Exception e) {
                    throw (new ParserException("Fehler beim Ezeugen einer Instanz der Klasse " + theClass.getName() + " mithilfe des parameterlosen Konstruktors"));
                }// end of try
            } // end of if

            // Suche nach Konstruktoren mit Parametern
            String[] parameters = splitParameters(paramString);
            Constructor<?>[] theConstructors = theClass.getConstructors();
            if (theConstructors.length == 0) {
                throw (new ParserException("Fehler: Kein passender Konstruktor für " + theClass + "(" + paramString + ") gefunden."));
            } // end of if
            boolean gefunden = false;
            Object[] arguments = new Object[0];
            // Jetzt werden alle Konstruktoren getestet.
            for (Constructor constructor : theConstructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length == parameters.length) {
                    arguments = new Object[parameterTypes.length];
                    gefunden = true;
                    for (int i = 0; i < parameterTypes.length; i++) {
                        // Zuerst werden die primitiven Datentypen getestet.
                        if (parameterTypes[i] == Boolean.TYPE) {
                            try { 
                                arguments[i] = parseBoolean(parameters[i]);
                            } catch(Exception e) {
                                gefunden = false;
                            } // end of try
                        } // end of if
                        else if (parameterTypes[i] == Character.TYPE) {
                            try { 
                                arguments[i] = parseCharacter(parameters[i]);
                            } catch(Exception e) {
                                gefunden = false;
                            } // end of try
                        } // end of if
                        else if (parameterTypes[i] == Byte.TYPE) {
                            try { 
                                arguments[i] = parseByte(parameters[i]);
                            } catch(Exception e) {
                                gefunden = false;
                            } // end of try
                        } // end of if
                        else if (parameterTypes[i] == Short.TYPE) {
                            try { 
                                arguments[i] = parseShort(parameters[i]);
                            } catch(Exception e) {
                                gefunden = false;
                            } // end of try
                        } // end of if
                        else if (parameterTypes[i] == Integer.TYPE) {
                            try { 
                                arguments[i] = parseInteger(parameters[i]);
                            } catch(Exception e) {
                                gefunden = false;
                            } // end of try
                        } // end of if
                        else if (parameterTypes[i] == Long.TYPE) {
                            try { 
                                arguments[i] = parseLong(parameters[i]);
                            } catch(Exception e) {
                                gefunden = false;
                            } // end of try
                        } // end of if
                        else if (parameterTypes[i] == Float.TYPE) {
                            try { 
                                arguments[i] = parseFloat(parameters[i]);
                            } catch(Exception e) {
                                gefunden = false;
                            } // end of try
                        } // end of if
                        else if (parameterTypes[i] == Double.TYPE) {
                            try { 
                                arguments[i] = parseDouble(parameters[i]);
                            } catch(Exception e) {
                                gefunden = false;
                            } // end of try
                        } // end of if
                        /*
                         * Jetzt werden die nicht primitiven Datentypen
                         * String, Color und Object getestet.
                         */
                        else {
                            Class cString = null;
                            try { 
                                cString = Class.forName("java.lang.String");
                            } catch(Exception e) {
                                throw (new ParserException("Unerwarteter Fehler, kann die Klasse java.lang.String nicht finden."));
                            } // end of try
                            if (parameterTypes[i] == cString) {
                                arguments[i] = parameters[i];
                            } // end of if
                            else {
                                Class cColor = null;
                                try { 
                                    cColor = Class.forName("java.awt.Color");
                                } catch(Exception e) {
                                    throw (new ParserException("Unerwarteter Fehler, kann die Klasse java.awt.Color nicht finden."));
                                } // end of try
                                if (parameterTypes[i] == cColor) {
                                    try { 
                                        arguments[i] = parseColor(parameters[i]);
                                    } catch(ParserException pE) {
                                        try { 
                                            arguments[i] = parseNewObjectString(parameters[i]);
                                        } catch(Exception e) {;
                                            throw (new ParserException("Falsche Farbdefinition: " + s));
                                        } // end of try
                                    } // end of try
                                } // end of if
                                else {
                                    try { 
                                        arguments[i] = parseNewObjectString(parameters[i]);
                                    } catch(Exception e) {
                                        gefunden = false;
                                    } // end of try
                                } // end of if-else
                            } // end of if-else
                        } // end of if-else
                        if (!gefunden) {
                            break;
                        } // end of if
                    } // end of for
                    if (gefunden) {
                        try { 
                            Object o = constructor.newInstance(arguments);
                            return(o);
                        } catch(Exception e) {
                            gefunden = false;
                        } // end of try
                    } // end of if
                } // end of if
            } // end of for
            throw (new ParserException("Konnte das Objekt nicht erzeugen: " + s));
        }

        /*
         * Diese Methode zerteilt einen String, der eine Parameterliste
         * enthält in die einzelnen Parametertoken. Das Ergebnis ist 
         * ein Array von Strings mit den einzelnen Tokens.
         */
        private String[] splitParameters(String pS) {
            ArrayList<String> stringArrayList = new ArrayList<String>();
            /*
             * Zunächst wird der String in einzelne Tokens zerlegt.
             * Delimiter sind dabei Komma, Klammer auf und Klammer zu.
             * Später werden dann die Tokens, die sich zwischen
             * einer offenen und einer geschlossenen Klammer befinden wieder
             * zusammengesetzt. Dadurch wird aus der Parameterliste:
             *      22, new java.awt.Color(200,0,0), true
             * ein String-Array mit folgenden Elementen:
             *      22
             *      new java.awt.Color(200,0,0)
             *      true
             */
            StringTokenizer st = new StringTokenizer(pS,",()",true);
            String token = "";
            int klammerzahl = 0;
            while (st.hasMoreTokens()) {
                String momentan = st.nextToken();
                if (momentan.equals("(")) {
                    klammerzahl++;
                    token = token.concat(momentan);
                } // end of if
                else if (momentan.equals(")")) {
                    klammerzahl--;
                    token = token.concat(momentan);
                } // end of if
                else if (klammerzahl != 0) {
                    token = token.concat(momentan);
                } 
                else if (!momentan.equals(",")) {
                    token = token.concat(momentan);
                }
                else {
                    stringArrayList.add(token);
                    token="";
                } // end of if-else
            }
            stringArrayList.add(token);
            String[] ergebnis = new String[stringArrayList.size()];
            for (int i = 0; i < stringArrayList.size(); i++ ) {
                ergebnis[i] = (stringArrayList.get(i)).trim();
            } // end of for
            return(ergebnis);
        }
    }
    // end of inner class Parser

    // inner Class ParserException
    private class ParserException extends Exception
    {
        public ParserException(String message) {
            super(message);  
        }
    }
    // end of inner class ParserException

    // inner Class JContentPanel
    private final class JContentPanel extends JPanel     
    {
        private boolean visible = false;
        private Object o;

        public final void zeichne(Object o) {
            this.o = o;
            this.repaint();
        }

        public final void setVisibility(boolean v) {
            visible = v;
        }

        public void paintComponent(Graphics g) {
            g.setColor(this.getBackground());
            g.fillRect(0,0,this.getWidth(),this.getHeight());
        }
    }
    // end of inner class JContentPanel
    
} // end of class GUI
