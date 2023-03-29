import java.awt.event.WindowAdapter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import java.sql.*;
import javax.swing.JFrame;

public class Motto extends JFrame {
    // Anfang Attribute
    private DatabaseConnector dbConnector;
    private JTextField tVorname = new JTextField();
    private JTextField tName = new JTextField();
    private JTextField tMotto = new JTextField();
    private JTextField tCountName = new JTextField("Gesuchten Vornamen eintragen");
    private JButton jbEintragen = new JButton();
    private JTextArea jtaAusgabe = new JTextArea("");
    private JScrollPane jtaAusgabeScrollPane = new JScrollPane(jtaAusgabe);
    private JLabel jLabel1 = new JLabel();
    private JButton jbVerbinden = new JButton();
    private JButton jbUebersicht = new JButton("Übersicht anzeigen");
    private JButton jbRangliste = new JButton("Rangliste");
    private JButton jbVorschlagAendern = new JButton("Vorschlag ändern");
    private JButton jbSQLInjection = new JButton("Wie oft gibt es den Vornamen?");
    Font f = new Font(Font.SERIF, Font.PLAIN, 28);
    String vorname, name, motto;
    // Ende Attribute

    public Motto(String title) {
        // Frame-Initialisierung
        super(title);

        int frameWidth = 797;
        int frameHeight = 378;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        Container cp = getContentPane();
        cp.setLayout(null);
        // Anfang Komponenten

        tVorname.setBounds(16, 72, 105, 24);
        tVorname.setText("Vorname");
        cp.add(tVorname);
        tName.setBounds(136, 72, 105, 24);
        tName.setText("Name");
        cp.add(tName);
        tMotto.setBounds(16, 104, 225, 24);
        tMotto.setText("Mottovorschlag");
        cp.add(tMotto);
        jbEintragen.setBounds(16, 136, 227, 25);
        jbEintragen.setText("Mottovorschlag einreichen/wählen");
        jbEintragen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbEintragen_ActionPerformed(evt);
            }
        });
        cp.add(jbEintragen);
        jtaAusgabeScrollPane.setBounds(272, 48, 489, 281);
        cp.add(jtaAusgabeScrollPane);
        

        setTitle("AbiMotto");
        jLabel1.setBounds(16, 8, 682, 20);
        jLabel1.setText("Bitte prüfen Sie: DB-Treiber in Bibliotheken vorhanden, DB abimotto.db vorhanden");
        cp.add(jLabel1);
        jbVerbinden.setBounds(16, 32, 227, 25);
        jbVerbinden.setText("Datenbankverbindung");
        jbVerbinden.setMargin(new Insets(2, 2, 2, 2));
        jbVerbinden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbVerbinden_ActionPerformed(evt);
            }
        });
        cp.add(jbVerbinden);
        // Ende Komponenten

        jbUebersicht.setBounds(16 , 136 + 32, 227, 25);
        jbUebersicht.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbUebersicht_ActionPerformed(evt);
            }
        });
        cp.add(jbUebersicht);
        jbRangliste.setBounds(16, 136 + (2*32), 227, 25);
        jbRangliste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbRangliste_ActionPerformed(evt);
            }
        });
        cp.add(jbRangliste);

        jbVorschlagAendern.setBounds(16,136 + (3*32), 227 , 25 );
        jbVorschlagAendern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbVorschlagAendern_ActionPerformed(evt);
            }
        });
        cp.add(jbVorschlagAendern);

        tCountName.setBounds(16 , 136 + (4*32),227 , 25);
        cp.add(tCountName);

        jbSQLInjection.setBounds(16, 136 + (5*32), 227 , 25 );
        
        cp.add(jbSQLInjection);
        // Ende Komponenten



        //Anfügen des Schliessen des Fensters
        addWindowListener(new WindowHandler());
        setResizable(true);
        setVisible(true);
    }

    // Anfang Methoden
    public void jbVorschlagAendern_ActionPerformed(ActionEvent evt)
    {
        boolean datensatzVorhanden = false;

        int mottoID = -1, userID = -1;
        int neuesMottoID = -1;
        String neuesMotto;
        //Das alte Motto aus den Textfeldern lesen 
        //Dies geschieht auch durch einen Dreierclick auf 
        vorname = tVorname.getText().trim();
        name = tName.getText().trim();
        motto = tMotto.getText().trim();

        dbConnector.executeStatement("SELECT user._id,motto._mottoid,vorname, name, motto FROM user JOIN vorschlag USING (_id) JOIN motto USING (_mottoid) where name = '" + name + "' and vorname = '" + vorname +"' and motto = '" + motto + "'");
        QueryResult r = dbConnector.getCurrentQueryResult();
        if (r.getRowCount() != 0){
            //Wir haben einen Datensatz gefunden
            datensatzVorhanden = true;
            mottoID = Integer.parseInt(r.getData()[0][1]);
            userID = Integer.parseInt(r.getData()[0][0]);
            System.out.println("Datensatz vorhanden: " + mottoID + " " + motto + " " + userID + " " + vorname + " " + name);
        }
        //System.out.println("Datensatz vorhanden: " + mottoID + " " + motto + " " + userID + " " + vorname + " " + name + " " + neuesMotto);

        if (datensatzVorhanden){
            //Frage nach dem neuen Motto
            neuesMotto = JOptionPane.showInputDialog(this,"Bitte das neue Motto eingeben!");

            dbConnector.executeStatement("SELECT _mottoid FROM motto WHERE motto='"+neuesMotto+"'");
            r = dbConnector.getCurrentQueryResult();
            //Das neue Motto wird geprüft
            //Das neue Motto gibt es noch nicht und es wird eingefügt
            if (r.getRowCount() == 0) {
                dbConnector.executeStatement("INSERT INTO motto(motto) VALUES('"+neuesMotto+"')");
                dbConnector.executeStatement("SELECT _mottoid FROM motto WHERE motto='"+neuesMotto+"'");
                r = dbConnector.getCurrentQueryResult();
                neuesMottoID = Integer.parseInt(r.getData()[0][0]);
                System.out.println("Motto : " + neuesMotto + " " + neuesMottoID + " gab es noch nicht");
            } else {//das Motto gibt es schon und wir speichern uns die ID
                neuesMottoID = Integer.parseInt(r.getData()[0][0]);
                System.out.println("Motto : " + neuesMotto + " " + neuesMottoID + " gibt es schon");
            }

            dbConnector.executeStatement("UPDATE vorschlag SET _mottoid = " + neuesMottoID + " WHERE _id = " + userID + " AND _mottoid = " + mottoID);

            //dbConnector.executeStatement("SELECT user._id,motto._mottoid,vorname, name, motto FROM user JOIN vorschlag USING (_id) JOIN motto USING (_mottoid) where name = '" + name + "' and vorname = '" + vorname +"' and motto = '" + motto + "'");
            
            dbConnector.executeStatement("SELECT user._id,motto._mottoid,vorname, name, motto FROM user JOIN vorschlag USING (_id) JOIN motto USING (_mottoid) where name = '" + name + "' and vorname = '" + vorname +"' and motto = '" + neuesMotto + "'");
            
            r = dbConnector.getCurrentQueryResult();
            if (r.getRowCount() != 0){
                JOptionPane.showMessageDialog(this,"Der Eintrag von " + r.getData()[0][2] + " " + r.getData()[0][3] + " wurde aktualisiert!","Eintrag wurde aktualisiert.",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void jbEintragen_ActionPerformed(ActionEvent evt) {
        String vorname = tVorname.getText().trim();
        String name = tName.getText().trim();
        String motto = tMotto.getText().trim();

        //Hier Quelltext einfügen
        int userid = -1;
        dbConnector.executeStatement("SELECT _id FROM user WHERE name='" + name + "' AND vorname='" + vorname + "'");
        QueryResult r = dbConnector.getCurrentQueryResult();

        if (r != null && r.getRowCount() == 0) {
            dbConnector.executeStatement("INSERT INTO user(name,vorname) VALUES('" + name + "','" + vorname + "')");
            dbConnector.executeStatement("SELECT _id FROM user WHERE name='" + name + "' AND vorname='" + vorname + "'");
            r = dbConnector.getCurrentQueryResult();
            //[Zeile des Resultsets][Spalte des Resultsets]
            userid = Integer.parseInt(r.getData()[0][0]);
        } else {
            userid = Integer.parseInt(r.getData()[0][0]);
        }
        //Eintragen des Mottos
        int mottoid = -1;
        dbConnector.executeStatement("SELECT _mottoid FROM motto WHERE motto='" + motto + "'");
        //dbConnector.executeStatement("SELECT _mottoid from motto where motto='" + motto + "'");
        r = dbConnector.getCurrentQueryResult();
        if (r.getRowCount() == 0) { //dieses Motto gab es noch nicht
            dbConnector.executeStatement("INSERT INTO motto(motto) VALUES('" + motto + "')");
            dbConnector.executeStatement("SELECT _mottoid FROM motto WHERE motto='" + motto + "'");
            r = dbConnector.getCurrentQueryResult();
            mottoid = Integer.parseInt(r.getData()[0][0]);
        } else {
            mottoid = Integer.parseInt(r.getData()[0][0]);
        }
        //Eintragen des Vorschlags
        //dbConnector.executeStatement("INSERT INTO vorschlag(_mottoid,_id) VALUES(" + mottoid + "," + userid+")");
        dbConnector.executeStatement("SELECT _mottoid FROM vorschlag WHERE _id='" + userid + "' AND _mottoid='" + mottoid + "'");
        r = dbConnector.getCurrentQueryResult();
        if (r.getRowCount() != 0) {
            JOptionPane.showMessageDialog(this, "Eintrag schon vorhanden.", "Fehler", JOptionPane.ERROR_MESSAGE);
        } else {
            dbConnector.executeStatement("INSERT INTO vorschlag(_id,_mottoid) VALUES('" + userid + "','" + mottoid + "')");
        }

        //Ausgabe des Resultsets in der JTextArea

        jtaAusgabe.setText("Vorname\tMottovorschlag\n-------------------------------------------------------------------\n");
        dbConnector.executeStatement(("SELECT  vorname,motto from motto join vorschlag on motto._mottoid = vorschlag._mottoid join user on vorschlag._id = user._id"));
        r = dbConnector.getCurrentQueryResult();
        for (int i = 0; i < r.getRowCount(); i++) {
            jtaAusgabe.append(r.getData()[i][0] + "\t" + r.getData()[i][1] + "\n");
        }
        //...

        //Ende des eigenen Quelltextes

    }

    public void jbVerbinden_ActionPerformed(ActionEvent evt) {
        //dbConnector = new DatabaseConnector("127.0.0.1", 3306, "abimotto", "", "");
        //Bei sqlite muss nur der Name der Datenbank angegeben werden, der Port wird ignoriert.
        dbConnector = new DatabaseConnector("",3306, "abimotto.db.db", "", "");
        String fehler = dbConnector.getErrorMessage();
        if (fehler == null) {
            jLabel1.setText("Datenbank wurde erfolgreich verbunden!");
        } else {
            jLabel1.setText("Fehlermeldung: " + fehler);
        }
   
    }

    // Ende Methoden


    /**
     * Innere Klasse zum WindowEventhandling, um die Datenbankverbindung beim Schliessen des Fensters zu trennen
     *
     * @author Ruth Bosbach
     * @version 2021
     */

    class WindowHandler extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            //Wenn wir tatsächlich eine Verbindung zu SQLite-Datenbank aufgebaut haben, schliessen wir diese beim Schliessen des Fensters
            if (dbConnector != null)
                dbConnector.close();
            System.exit(0); // Ende der Anwendung
        }
    }

    public void jbUebersicht_ActionPerformed(ActionEvent evt){
        jtaAusgabe.setText("Vorname\tMottovorschlag\n-------------------------------------------------------------------\n");
        //Alternative Query: SELECT user.Vorname,user.Name,motto.Motto FROM vorschlag,user,motto where vorschlag.ID = user.ID and motto.MottoID = vorschlag.MottoID
        dbConnector.executeStatement("SELECT vorname, name,motto FROM user JOIN vorschlag USING (_id) JOIN motto USING (_mottoid)");
        //dbConnector.executeStatement(" SELECT user.Vorname,user.Name,motto.Motto FROM vorschlag,user,motto where vorschlag.ID = user.ID and motto.MottoID = vorschlag.MottoID");
        QueryResult r = dbConnector.getCurrentQueryResult();
        if(r == null){
            jtaAusgabe.setText(dbConnector.getErrorMessage());
        }
        else{
            String [] cols = r.getColumnNames();
            jtaAusgabe.setText(cols[0] + "\t" + cols[1]+ "\t" + cols[2] +"\n");
            for (int i = 0; i < r.getRowCount(); i++) {
                jtaAusgabe.append(r.getData()[i][0]+"\t"+r.getData()[i][1]+ "\t"+r.getData()[i][2]+"\n");
            }
        }
    }

    
    

    public void jbRangliste_ActionPerformed(ActionEvent evt)
    {
        jtaAusgabe.setText("Anzahl\tMottovorschlag\n-------------------------------------------------------------------\n");
        dbConnector.executeStatement("SELECT count(motto._mottoid) as Anzahl ,motto.motto FROM motto,vorschlag WHERE vorschlag._mottoid = motto._mottoid GROUP BY motto.motto ORDER BY Anzahl desc");
        QueryResult r = dbConnector.getCurrentQueryResult();
        if(r == null){
            jtaAusgabe.setText(dbConnector.getErrorMessage());
        }
        else{
            String [] cols = r.getColumnNames();
            jtaAusgabe.setText(cols[0] + "\t" + cols[1]+ "\n");
            for (int i = 0; i < r.getRowCount(); i++) {
                jtaAusgabe.append(r.getData()[i][0]+"\t"+r.getData()[i][1]+"\n");
            }
        }
    }

    

    public void fontSetzen(Font f){
        tVorname.setFont(f);
        tName.setFont(f);
        tMotto.setFont(f);
        jbEintragen.setFont(f);
        jtaAusgabe.setFont(f);
        jtaAusgabeScrollPane.setFont(f);
        jLabel1.setFont(f);
        jbVerbinden.setFont(f);
        jbUebersicht.setFont(f);
        jbRangliste.setFont(f);
        jbVorschlagAendern.setFont(f);
        tCountName.setFont(f);
        jbSQLInjection.setFont(f);


    }

   
  public static void main(String[] args) {
    new Motto("Mottoverwaltung");
  }
    
}


