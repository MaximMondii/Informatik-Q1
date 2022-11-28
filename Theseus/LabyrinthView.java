import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Graphische Darstellung des Labyrinths
 */
public class LabyrinthView extends Canvas implements ILabyrinthView
{
    // Instanzvariablen
    private Labyrinth l;
    private int dim;
    private int skalierung = 20;

    
    /**
     * Konstruktor für Objekte der Klasse LabyrinthView
     */
    public LabyrinthView(Labyrinth l, int breite)
    {
        this.l = l;
        dim = l.getDimension();
        skalierung = breite;
    }

    /**
     * Graphik wird neu gezeichnet
     */ 
    public void paint(Graphics g){

       // Felder
       for (int i = 0; i < dim; i++){
          for (int j = 0; j < dim ; j++){
             zeige(i, j);
          }
       } 

       // Raster
       for (int i = 0; i < dim + 1; i++){
           g.drawLine(i * skalierung, 0, i  * skalierung, dim * skalierung);
       }
       for (int i = 0; i < dim + 1; i++){
           g.drawLine(0, i * skalierung, dim * skalierung, i * skalierung);
       }        
    }
  
    /**
     * Ausgabe des Spielbrettes
     */
    public void zeigeAlles(){
       repaint();
    }
    
    /**
     * Ausgabe eines Feldes
     * 
     * @param   x       x-Koordinate 
     * @param   y       y-Koordinate 
     */
    public void zeige(int x, int y){
       Graphics g = getGraphics();
       int status = l.getStatus(x, y);
       switch(status){
          case 0:
             g.setColor(Color.white);
             break;
          case 1:
             g.setColor(Color.black);
             break;
          case 3:
             g.setColor(Color.green);
             break;
          case 4:
             g.setColor(Color.red);
             break;
       }
       g.fillRect(x * skalierung, y * skalierung, skalierung, skalierung);
       g.setColor(Color.black);
       g.drawRect(x * skalierung, y * skalierung, skalierung, skalierung);
    }
    
}

