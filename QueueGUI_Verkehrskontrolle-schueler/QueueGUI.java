import java.awt.*;  
import java.awt.event.*;
import javax.swing.*;
import java.lang.reflect.*;
import java.util.*; 
@SuppressWarnings("unchecked")

/**
 * Klasse QueueGUI
 * 
 * @version 2015-01-29 (v 3.1)
 * 
 * @author Rudolf Silberberg
 */

public class QueueGUI extends JFrame {   

    // Das Knotenpanel
    private JNodePanel panNodes = new JNodePanel();

    // Andere Attribute
    private Queue myQueue;
    private QueueGUI andereGUI;
    private java.util.Timer gTimer;
    private java.util.TimerTask gTimerTask;
    private boolean contentVisibility;

    /**
     * Ein Konstruktor ohne Parameter<br>
     * Intern wird ein anderer Konstruktor aufgerufen,
     * der einen Parameter erwartet. Diesem wird der
     * Parameter 0 uebergeben.
     */
    public QueueGUI() {
        this(0);
    }

    /**
     * Ein Konstruktor mit Parameter<br>
     * Der Parameter gibt an, wo die GUI auf dem
     * Bildschirm erscheinen soll.<br>
     * Wenn der Parameter 0 ist, erscheint die GUI oben.<br>
     * Wenn der Parameter 1 ist, erscheint die GUI in der Mitte.<br>
     * Wenn der Parameter 2 ist, erscheint die GUI unten.
     * 
     * @param nr die Kennzahl fuer die Bildschirmposition
     */
    public QueueGUI(int nr) {

        super("QueueGUI");
        nr %= 3;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.addWindowListener(new MyWindowAdapter());

        panNodes.setOpaque(false);
        panNodes.setBackground(new Color(239,228,176));
        panNodes.setBorder(new javax.swing.border.LineBorder(new Color(0xC0C0C0)));
        panNodes.setFont(new Font("Arial", Font.BOLD, 16));
        panNodes.setPreferredSize(new Dimension(630,280));
        this.add(panNodes);

        this.pack();
        this.setLocation(10,10+nr*340);
        this.setResizable(false);
        this.setVisible(true);

        // Andere Attribute

        gTimer = new java.util.Timer();
        gTimerTask = new GTimerTask(panNodes);
        gTimer.schedule(gTimerTask,100,100);
        contentVisibility = true;

    } // end of public Queue

    //----------------
    // Anfang Methoden
    //----------------

    /**
     * Mit dieser Methode kann eine andere Instanz der
     * Klasse QueueGUI angemeldet werden.<br>
     * Damit ist es z.B. moeglich, zwei Queues zu verwalten
     * und mithilfe einer Methode zu verknuepfen.
     * 
     * @param gui die andere GUI
     */
    public void guiAnmelden(QueueGUI gui) {
        andereGUI = gui;
    }

    /**
     * Mit dieser Methode kann eine extern erzeugte
     * Queue angemeldet werden.<br>
     * Die Queue wird in dem Attribut myQueue gespeichert.
     * 
     * @param queue die Queue, die angemeldet werden soll.
     */
    public void queueAnmelden(Queue queue) { 
        myQueue = queue;
    }

    public void inhalteAnzeigen(boolean pContentVisibility) {
        contentVisibility = pContentVisibility;
    }

    //--------------
    // Ende Methoden
    //--------------

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

            gTimerTask.cancel();
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
            if (myQueue != null) {
                if (myQueue.head == null) {
                    g.setColor(Color.black);
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    int x = this.getWidth() / 2 - 80;
                    int y = this.getHeight() / 2;
                    g.drawString("Leere Queue",x,y);
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
                g.drawString("Keine Queue",x,y);
            } // end of if-else
        }

        /*
         * Ein einzelner Knoten wird gezeichnet.
         * Die Position des Knotens auf dem Panel wird aus
         * seiner Position im ArrayList<node> nodes berechnet. 
         * Die Darstellungsgroesse wird automatisch bestimmt.
         */
        private void zeigeNode(Queue.QueueNode node, ArrayList<Queue.QueueNode> nodes, double faktor, Graphics g) {
            int startx = 20 + (nodes.indexOf(node))*(int)(125*faktor);
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

            // Hintergrund fuer besondere Knoten
            // Dieser Fall darf bei einer Queue gar nicht auftreten.
            // Bleibt als Relikt der Listenprogrammierung aber stehen.
            if (node.getContent() == null) { 
                if (node.getNext() == null) { // Liste ohne tail - Listenende
                    g.setColor(new Color(255,174,201));
                    g.fillRect(startx-(int)(0*faktor),starty-(int)(90*faktor)+(int)(deltaYKn*faktor),(int)(101*faktor),(int)(90*faktor));
                    g.setColor(Color.black);
                    g.setFont(new Font("Arial", Font.BOLD, (int)(16*faktor)));
                    g.drawString("tail?",startx+(int)(35*faktor),starty-(int)(74*faktor)+(int)(deltaYKn*faktor));
                }
                else if (node.getNext().getNext() == node) { // Liste mit tail - Dummyelement tail
                    g.setColor(new Color(255,174,201));
                    g.fillRect(startx-(int)(0*faktor),starty-(int)(90*faktor)+(int)(deltaYKn*faktor),(int)(101*faktor),(int)(90*faktor));
                    g.setColor(Color.black);
                    g.setFont(new Font("Arial", Font.BOLD, (int)(16*faktor)));
                    g.drawString("tail?",startx+(int)(35*faktor),starty-(int)(74*faktor)+(int)(deltaYKn*faktor));    
                }
            } // end of if

            // Der ganze Knoten
            if (myQueue.head == node) {
                g.setColor(new Color(255,127,39));  // head (orange)
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
            if (node.getContent() == null) {
                g.setColor(Color.red);
                g.setFont(new Font("Arial", Font.BOLD, (int)(30*faktor)));
                g.drawString("null",startx+(int)(25*faktor),starty-(int)(40*faktor)+(int)(deltaYKn*faktor));
            } // end of if
            if (node.getNext() == null) {
                g.setColor(Color.red);
                g.setFont(new Font("Arial", Font.BOLD, (int)(24*faktor)));
                g.drawString("null",startx+(int)(55*faktor),starty-(int)(10*faktor)+(int)(deltaYKn*faktor));
            } // end of if

            // head wird markiert (Text "head" unter dem Knotenbild)
            if (node == myQueue.head) {
                g.setColor(Color.black);
                g.setFont(new Font("Arial", Font.BOLD, (int)(30*faktor)));
                if (myQueue.tail == myQueue.head){
                    //startx+=(int)(32*faktor);
                     g.setFont(new Font("Arial", Font.BOLD, (int)(20*faktor)));
                     g.drawString(" head",startx+(int)(0*faktor),starty-(int)(-40*faktor));
                }
                else{
                g.drawString("  head",startx+(int)(0*faktor),starty-(int)(-40*faktor));
            }
            } // end of if
            
            if(node == myQueue.tail){
                 g.setColor(Color.black);
                g.setFont(new Font("Arial", Font.BOLD, (int)(30*faktor)));
                if (myQueue.tail == myQueue.head){
                    //startx+=(int)(32*faktor);
                     g.setFont(new Font("Arial", Font.BOLD, (int)(20*faktor)));
                     g.drawString("/ tail",startx+(int)(55*faktor),starty-(int)(-40*faktor));
                }
                else{
                g.drawString("  tail",startx+(int)(55*faktor),starty-(int)(-40*faktor));
            }
            }

            // Das Inhaltsobjekt wird gezeichnet.
            Object inhalt = node.getContent();
            // Nur Inhaltsobjekte != null werden gezeichnet.
            if (inhalt != null) {
                // Hintergrund
                if (myQueue.head == node) {
                    g.setColor(new Color(255,127,39));  // head (Orange)
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
                        jCP.setVisibility(contentVisibility);
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
                    //Just Paranoia
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
                    if (contentVisibility) {
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
                if (myQueue.head == node) {
                    g.setColor(new Color(237,28,36));  // head (rot)
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
            if (node.getNext() != null) {
                g.setColor(new Color(63,72,204)); // blau
                g.fillOval(startx+(int)(70*faktor),starty-(int)((35.0/4.0*2.0)*faktor)-(int)(5*faktor)+(int)(deltaYKn*faktor),(int)(10*faktor),(int)(10*faktor));
                Queue.QueueNode nextNode = node.getNext();
                if (nodes.indexOf(nextNode) > nodes.indexOf(node)) { // alle "normalen" Knoten
                    int startNextx = 20 + (nodes.indexOf(nextNode))*(int)(125*faktor);
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
                    int startNextx = 20 + (nodes.indexOf(nextNode))*(int)(125*faktor);
                    int startNexty = this.getHeight()-50;
                    g.drawLine(startx+(int)(75*faktor),starty+(int)(20.0*faktor)+(int)(deltaYKn*faktor),startNextx+(int)(25*faktor),startNexty+(int)(20.0*faktor)+(int)(deltaYKn*faktor));
                    g.drawLine(startx+(int)(75*faktor),starty-(int)((35.0/4.0*2.0)*faktor)+(int)(deltaYKn*faktor),startx+(int)(75*faktor),starty+(int)(20.0*faktor)+(int)(deltaYKn*faktor));
                    g.drawLine(startNextx+(int)(25*faktor),startNexty+(int)(20.0*faktor)+(int)(deltaYKn*faktor),startNextx+(int)(25*faktor),startNexty+(int)(deltaYKn*faktor));
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
         * Die ganze Queue oder ein moeglichst grosser Teil sollen gezeichnet werden.
         * In dieser Methode wird der darstellbare Bereich berechnet und
         * anschliessend wird die Methode zeigeNode aufgerufen, um 
         * die einzelnen Knoten zu zeichnen.
         */
        private void zeigeListe(Graphics g) { 
            //Eine ArrayList zum Speichern der Knoten
            ArrayList<Queue.QueueNode> nodes = new ArrayList<Queue.QueueNode>();

            /*
             * Alle Knoten der Queue werden in dem
             * Arraylist<Queue.QueueNode> nodes gespeichert.
             */  
            Queue.QueueNode travers = myQueue.head;
            nodes.add(travers);
            while (travers.getNext() != null) { 
                travers = travers.getNext();
                nodes.add(travers);
            }

            /*
             * Es wird der notwendige Faktor fuer die gewuenschte
             * Darstellungsgroesse berechnet. Der Unterschied von einer 
             * Groesse zu der naechsten Groesse ist der Faktor 1/sqrt(2).
             */
            int extent;
            double faktor = 1.0;
            if (nodes.size() <= 5) {
                faktor = 1.0;
                extent = 5;
            } 
            else if (nodes.size() <= 7) {
                faktor /= Math.sqrt(2.0);
                extent = 7;
            }
            else if (nodes.size() <= 10){
                faktor /= 2.0;
                extent = 10;
            } 
            else if (nodes.size() <= 14){
                faktor /= (2.0*Math.sqrt(2.0));
                extent = 14;
            }
            else {
                faktor /= 4.0;
                extent = 20;
            }

            /*
             * Jetzt werden die Knoten unter Beruecksichtigung ihrer
             * Darstellungsgroesse und dem Scrolloffset gezeichnet.
             */
            for (Queue.QueueNode node : nodes) {
                if (nodes.indexOf(node)>=-1 && nodes.indexOf(node)<=extent) {
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
    // od of innter class GTimerTask

    // innere Klasse JContentPanel
    private final class JContentPanel extends JPanel     
    {
        private boolean visible = false;
        private Object o;

        private final void zeichne(Object o) {
            this.o = o;
            this.repaint();
        }

        private final void setVisibility(boolean v) {
            visible = v;
        }

        public void paintComponent(Graphics g) {
            g.setColor(this.getBackground());
            g.fillRect(0,0,this.getWidth(),this.getHeight());
        }
    }
} // end of class GUI
