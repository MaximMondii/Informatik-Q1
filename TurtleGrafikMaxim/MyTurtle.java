import greenfoot.*;

/**
 * Turtle mit Zeitverzoegerung bei der Bewegung
 * 
 * @author (HB) 
 * @version (1.1)
 */
public class MyTurtle extends Turtle
{
    /**
     * Konstruktor
     */
    public MyTurtle(){
        penDown();
    }
    
    public void quadrat(double length)
    {
        penDown();
        for(int i = 0; i < 4; i++)
        {
            move(length);
            turn(90);
        }
    }
    
    
    public void quadratBaum(int Anzahl, double length)
    {
        if(Anzahl >= 0)
        {            
            move(length/2);
            turn(-90);
            move(length);
            turn(135);
            quadratBaum(Anzahl-1, length/2);
            turn(-135);
            turn(-90);
            move(length);
            turn(-90);
            turn(-135);
            quadratBaum(Anzahl-1, length/2);
            turn(135);
            move(length);
            turn(-90);
            move(length/2);
          
        }
    }
    
    public void baumBaum(int Anzahl, double length)
    {   
        if(Anzahl >= 0){
        //setDirection(270);
        move(length);
        double temp = getDirection();
        turn(45);
        move(length*0.75); // erster Ast
        baumBaum(Anzahl-1, length/2);
        turn(180);
        move(length*0.75);
        setDirection(temp);
        turn(-45);
        move(length*0.75); // zweiter Ast
        baumBaum(Anzahl-1, length/2);
        turn(180);
        move(length*0.75);
        setDirection(temp);
        turn(180);
        move(length);
        turn(180);
        }
    }
    
        public void doppelBaum(int Anzahl, double length)
    {   
        if(Anzahl >= 0){
            //setDirection(270);
            move(length);
            double temp = getDirection();
            turn(45);
            move(length*0.75); // erster Ast
            if(Anzahl-1 >= 0){
                turn(45);
                doppelBaum(Anzahl-1, length/2);
                turn(-90);
                doppelBaum(Anzahl-1, length/2);
                turn(45);
            }
            turn(180);
            move(length*0.75);
            setDirection(temp);
            turn(-45);
            move(length*0.75); // zweiter Ast
            if(Anzahl-1 >= 0){
                turn(45);
                doppelBaum(Anzahl-1, length/2);
                turn(-90);
                doppelBaum(Anzahl-1, length/2);
                turn(45);
            }
            turn(180);
            move(length*0.75);
            setDirection(temp);
            turn(180);
            move(length);
            turn(180);
        }
    }
    
    
    /**
     * Spezielle Version von move mit Zeitverzoegerung
     * 
     * @param distance Distanz
     */
    public void move(double distance){
        Greenfoot.delay(1);
        super.move(distance);
    }
    
    /**
     * Spezielle Version von turn mit Zeitverzoegerung
     * 
     * @param degrees Winkel
     */
    public void turn(double degrees){
        Greenfoot.delay(1);
        super.turn(degrees);
    }
    
    /**
     * Spezielle Version von move mit Zeitverzoegerung
     * 
     * @param distance Distanz
     */
    public void move(int distance){
        Greenfoot.delay(1);
        super.move(distance);
    }
    
    /**
     * Spezielle Version von turn mit Zeitverzoegerung
     * 
     * @param degrees Winkel
     */
    public void turn(int degrees){
        Greenfoot.delay(1);
        super.turn(degrees);
    }
    
    /**
     * fahre zurück
     */
    public void moveBackwards(double distance){

        setRotation(getRotation() - 180);
        setDirection(getDirection()-180);
        move(distance);
    }
}
