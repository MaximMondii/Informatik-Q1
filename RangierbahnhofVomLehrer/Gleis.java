import java.awt.*;

class Gleis {
 private StackGUI gleisGUI;
 private Stack<Wagen> sta;
 private char gleisbuchstabe;

  public Gleis(char gleisbuchstabe, int position){
    sta = new Stack<Wagen>();
    this.gleisbuchstabe = gleisbuchstabe;
    gleisGUI = new StackGUI(position);
    gleisGUI.setTitle(gleisbuchstabe+"");
    gleisGUI.stackAnmelden(sta);
  }

  /**
   * Ein Wagen wird auf das Gleis gepusht
   */
  
  public void push(Wagen w){
      sta.push(w);
    
  }
/**
 * Ein Wagen wird vom Gleis genommen
 */
  public void pop(){
    //Hier soll das Objekt (also der Wagen)vom Gleis
    //genommen werden.
    //Bitte ergaenzen!***************************
    sta.pop();
    
  }

  public int getZahl(){
   //Diese Methode soll die Wagennummer des vordersten Wagens auf dem Gleis (Stack) zurückgeben
  return top().getNummer();
  }
  
  public Wagen top(){
      return  sta.top();
    }
    
  public boolean isEmpty(){
      return sta.isEmpty();
    }
}



