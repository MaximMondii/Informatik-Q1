import java.awt.*;
/**
 * Write a description of class Kreisdiagramm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HangmanBild extends Canvas
{
    // instance variables - replace the example below with your own
   private int bildnr = 0;
   
   private Image [] images = new Image [Hangman.maxAnzahlVersuche + 1];

    /**
     * Constructor for objects of class 
     */
    public HangmanBild()
    {
       for (int i = 1; i <= Hangman.maxAnzahlVersuche; i++){
           images[i]=Toolkit.getDefaultToolkit().getImage("img/hangman"+i+".png");
        }
       
    }

    public void setBildnr (int nr){
        bildnr = nr;
        repaint();
    }
    
   public void paint(Graphics g){ //wird immer aufgerufen, wenn die Komponente gezeichnet werden soll
     
       
     
     //four attributes: the image, x/y position, an image observer
     g.drawImage(images[bildnr], 10, 10, this);

     
     
      
       
    }
}
