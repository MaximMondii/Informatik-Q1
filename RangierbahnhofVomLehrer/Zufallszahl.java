import java.util.Random;

class Zufallszahl {
 //private int[] zahlenFeld={0,0,0,0,0,0,0,0,0,0,0,0};
 
 private int[] zahlenFeld={0,0,0,0,0};
 
  public Zufallszahl(){
    Random zufall=new Random();           //Zufallsgenerator erzeugen
    int counter=0;
    int einsatz=0;
    boolean kannEinsetzen=true;
    while(counter<zahlenFeld.length){
      einsatz=1+zufall.nextInt(50);       //Zahl würfeln
      for(int i=0;i<zahlenFeld.length;i++)//Prüfen ob Zahl schon vorhanden
        if(zahlenFeld[i]==einsatz)
          kannEinsetzen=false;
      if(kannEinsetzen){                  //Wenn nicht vorhanden Hinzufügen
        zahlenFeld[counter]=einsatz;
        counter++;
      }
      else kannEinsetzen=true;            //Sonst neu auswürfeln
    }
  }

  public int[] getNumbers(){
   return zahlenFeld;
  }
}
