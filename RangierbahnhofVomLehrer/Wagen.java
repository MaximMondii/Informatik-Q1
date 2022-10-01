import java.awt.*;
class Wagen {
  
   private int nummer;            //Die Wagennummer

   public Wagen(int nummer){
    this.nummer = nummer;
   }
   
   public void setNumber(int n){
     nummer=n;
   }
   public int getNummer(){
    return nummer;
   }
   
   public String toString(){
       return Integer.toString(nummer);
   }
   
}

