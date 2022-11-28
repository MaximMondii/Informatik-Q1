
/**
 * Labyrinth
 */
public class Labyrinth
{
    private int[][] l;
    private int dim;
    private int maxAnzahlSichten = 10;
    private int anzahlSichten = 0;
    private ILabyrinthView[] sichten;

	/**
	 * Konstruktor fuer Objekte der Klasse Labyrinth
	 */
	public Labyrinth(int dim)
	{
		this.dim = dim;
		l = new int[dim][dim];
		sichten = new ILabyrinthView[10];
	}
	
    /**
     * Dimension wird zurückgegeben
     * 
     * @return Anzahl der Zeilen bzw. Spalten des Labyrinths
     */
    public int getDimension(){
        return(dim);
    }

	/**
	 * Erzeugt ein neues zufälliges Labyrinth
	 * 0: freies Feld
	 * 1: Wand
	 * 3: Feld, welches Theseus schon betreten hat
	 * 4: Feld, welches nicht zum Ziel führt
	 * 
	 * @param  p	Wahrscheinlichkeit, mit der ein Feld eine Wand ist
	 */
	public void init(double p){
        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                if (Math.random() < 0.3)
                    l[i][j] = 1;
                else{
                    l[i][j] = 0;
                }
            }
        }
        l[0][0] = 0;
        l[dim - 1][dim - 1] = 0;
        for (int i = 0; i < anzahlSichten; i++){
            sichten[i].zeigeAlles();
        }
	}
	
   /**
    * Rückgabe des Status eines Feldes
    * 
    * @param  x x-Koordinate des Feldes
    * @param  y	y-Koordinate des Feldes
    * @return Status eines Feldes (siehe Beschreibung von init)
    */
    public int getStatus(int x, int y){
        return(l[x][y]);
    }
    
   /**
    * Status eines Feldes setzen
    * (siehe Beschreibung von init)
    * 
    * @param  x x-Koordinate des Feldes
    * @param  y	y-Koordinate des Feldes
    */
    public void setStatus(int x, int y, int status){
        l[x][y] = status;
        for (int i = 0; i < anzahlSichten; i++){
            sichten[i].zeige(x, y);
        }
    }
    
   /**
    * Sicht anmelden
    * 
    * @param  sicht     Sicht die angemeldet wird
    */
   public void meldeAn(ILabyrinthView sicht){
      if (anzahlSichten < maxAnzahlSichten){
         sichten[anzahlSichten] = sicht;
         anzahlSichten++;
         sicht.zeigeAlles();
      }
      else{
          System.out.println("Die Sicht konnte nicht mehr hinzugefuegt werden!");
      }
   }
 
}
