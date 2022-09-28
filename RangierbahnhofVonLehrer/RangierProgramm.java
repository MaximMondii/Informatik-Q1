import java.awt.*;
import java.awt.event.*;

class RangierProgramm {
    private Graphics g;
    private Gleis gleisA;
    private Gleis gleisB;
    private Gleis gleisC;
    private Frame f;
    private FensterSchliesser fensch;

    public RangierProgramm(){
        f=new Frame("Rangierbahnhof");
        f.setSize(500,500);
        f.setVisible(true);
        g=f.getGraphics();
        warte(500); //Gleis konnte nicht gezeichnet werden!!!(Grafikproblem)
        gleisA = new Gleis('A',0);
        gleisB = new Gleis('B',1);
        gleisC = new Gleis('C',2);
        this.gleisMitWagenFuellen();
        //gleisMitSortiertenWagenFuellen();
        fensch=new FensterSchliesser();
        f.addWindowListener(fensch);
    }

    public void warte(long msec){
        long zeit=System.currentTimeMillis();
        while(System.currentTimeMillis()-zeit<msec);
    }

    public void gleisMitWagenFuellen(){
        Zufallszahl zz=new Zufallszahl();
        int[] zahlen=zz.getNumbers();
        for(int i=0;i<zahlen.length;i++){
            Wagen neuerWagen=new Wagen(zahlen[i]);

            gleisA.push(neuerWagen);
        }
    }

    public void gleisMitSortiertenWagenFuellen(){
        int[] zahlen= {4,3,1,2};
        for(int i=0;i<zahlen.length;i++){
            Wagen neuerWagen=new Wagen(zahlen[i]);
            gleisA.push(neuerWagen);
        }
    }

    public void verschiebe(){
        verschiebe(gleisA, gleisB, gleisC);
    }

    public void verschiebe (Gleis start, Gleis rangier, Gleis ziel){
        ziel.push(start.top());
        start.pop();
        while(!start.isEmpty())
        {
            while(start.top().getNummer() < ziel.top().getNummer())
            {
                rangier.push(ziel.top());
                ziel.pop();
                if(ziel.isEmpty())
                {
                    ziel.push(start.top());
                    start.pop();  
                }
            }
            if(!rangier.isEmpty())
            {
                if(start.top().getNummer() < rangier.top().getNummer())
                {
                    ziel.push(start.top());
                    start.pop();
                }
            }
            while(!rangier.isEmpty())
            {
                ziel.push(rangier.top());
                rangier.pop();
            }
        }
    }

    public void verschiebeTestABC(){
        verschiebe(gleisA, gleisB, gleisC);
    }

    public void verschiebe(Gleis start, Gleis ziel){
        if (!start.isEmpty()){
            Wagen w = start.top();
            start.pop();
            ziel.push(w);
        }
    }

    public Gleis getGleisA(){
        return gleisA;
    }

    public Gleis getGleisB(){
        return gleisB;
    }

    public Gleis getGleisC(){
        return gleisC;
    }

    public void starte() {
        gleisMitWagenFuellen();
        verschiebe(gleisA,gleisB,gleisC);
    }

    public void leereRangiergleis(Gleis start, Gleis ziel){
        while(!start.isEmpty()){
            Wagen w = start.top();
            start.pop();
            ziel.push(w);
        }
    }
}

