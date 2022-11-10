
public class Kartendealer
{
    private List<Karte> deck; 
    public Kartendealer()
    {
        deck = new List<Karte>();
        kartendeckErstellen(deck);
    }

    public void kartendeckErstellen(List<Karte> Deck)
    {
        for(int o = 0; o < 4; o++){
            switch(o){
                case 0:
                for(int i = 6; i < 15; i++)
                {
                    switch(i){
                        case 6:
                            Karte p6 = new Karte(Integer.toString(i), "Pik");
                            deck.append(p6);
                        break;
                        case 7:
                            Karte p7 = new Karte(Integer.toString(i), "Pik");
                            deck.append(p7);
                        break;
                        case 8:
                            Karte p8 = new Karte(Integer.toString(i), "Pik");
                            deck.append(p8);
                        break;
                        case 9:
                            Karte p9 = new Karte(Integer.toString(i), "Pik");
                            deck.append(p9);
                        break;
                        case 10:
                            Karte p10 = new Karte(Integer.toString(i), "Pik");
                            deck.append(p10);
                        break;
                        case 11:
                            Karte p11 = new Karte("Bube", "Pik");
                            deck.append(p11);
                        break;
                        case 12:
                            Karte p12 = new Karte("Dame", "Pik");
                            deck.append(p12);
                        break;
                        case 13:
                            Karte p13 = new Karte("König", "Pik");
                            deck.append(p13);
                        break;
                        case 14:
                            Karte p14 = new Karte("Ass", "Pik");
                            deck.append(p14);
                        break;
                        default:
                            System.out.println("Error bei Pik");
                        break;
                        }
                    }
                break;
                case 1:
                for(int i = 6; i < 15; i++)
                {
                    switch(i){
                        case 6:
                            Karte p6 = new Karte(Integer.toString(i), "Herz");
                            deck.append(p6);
                        break;
                        case 7:
                            Karte p7 = new Karte(Integer.toString(i), "Herz");
                            deck.append(p7);
                        break;
                        case 8:
                            Karte p8 = new Karte(Integer.toString(i), "Herz");
                            deck.append(p8);
                        break;
                        case 9:
                            Karte p9 = new Karte(Integer.toString(i), "Herz");
                            deck.append(p9);
                        break;
                        case 10:
                            Karte p10 = new Karte(Integer.toString(i), "Herz");
                            deck.append(p10);
                        break;
                        case 11:
                            Karte p11 = new Karte("Bube", "Herz");
                            deck.append(p11);
                        break;
                        case 12:
                            Karte p12 = new Karte("Dame", "Herz");
                            deck.append(p12);
                        break;
                        case 13:
                            Karte p13 = new Karte("König", "Herz");
                            deck.append(p13);
                        break;
                        case 14:
                            Karte p14 = new Karte("Ass", "Herz");
                            deck.append(p14);
                        break;
                        default:
                            System.out.println("Error bei Herz");
                        break;
                        }
                    }
                break;
                case 2:
                for(int i = 6; i < 15; i++)
                {
                    switch(i){
                        case 6:
                            Karte p6 = new Karte(Integer.toString(i), "Karo");
                            deck.append(p6);
                        break;
                        case 7:
                            Karte p7 = new Karte(Integer.toString(i), "Karo");
                            deck.append(p7);
                        break;
                        case 8:
                            Karte p8 = new Karte(Integer.toString(i), "Karo");
                            deck.append(p8);
                        break;
                        case 9:
                            Karte p9 = new Karte(Integer.toString(i), "Karo");
                            deck.append(p9);
                        break;
                        case 10:
                            Karte p10 = new Karte(Integer.toString(i), "Karo");
                            deck.append(p10);
                        break;
                        case 11:
                            Karte p11 = new Karte("Bube", "Karo");
                            deck.append(p11);
                        break;
                        case 12:
                            Karte p12 = new Karte("Dame", "Karo");
                            deck.append(p12);
                        break;
                        case 13:
                            Karte p13 = new Karte("König", "Karo");
                            deck.append(p13);
                        break;
                        case 14:
                            Karte p14 = new Karte("Ass", "Karo");
                            deck.append(p14);
                        break;
                        default:
                            System.out.println("Error bei Karo");
                        break;
                        }
                    }
                break;
                case 3:
                for(int i = 6; i < 15; i++)
                {
                    switch(i){
                        case 6:
                            Karte p6 = new Karte(Integer.toString(i), "Kreuz");
                            deck.append(p6);
                        break;
                        case 7:
                            Karte p7 = new Karte(Integer.toString(i), "Kreuz");
                            deck.append(p7);
                        break;
                        case 8:
                            Karte p8 = new Karte(Integer.toString(i), "Kreuz");
                            deck.append(p8);
                        break;
                        case 9:
                            Karte p9 = new Karte(Integer.toString(i), "Kreuz");
                            deck.append(p9);
                        break;
                        case 10:
                            Karte p10 = new Karte(Integer.toString(i), "Kreuz");
                            deck.append(p10);
                        break;
                        case 11:
                            Karte p11 = new Karte("Bube", "Kreuz");
                            deck.append(p11);
                        break;
                        case 12:
                            Karte p12 = new Karte("Dame", "Kreuz");
                            deck.append(p12);
                        break;
                        case 13:
                            Karte p13 = new Karte("König", "Kreuz");
                            deck.append(p13);
                        break;
                        case 14:
                            Karte p14 = new Karte("Ass", "Kreuz");
                            deck.append(p14);
                        default:
                            System.out.println("Es ist eigentlich ein Error, aber es klappt lmao");
                        break;
                        }
                    }
                break;
            }
        }
    }
    public void mehrRotOderSchwarz(int bereich)
    {
        int schwarz = 0;
        int rot = 0;
        deck.toFirst();
        if(bereich > length() || bereich < 0)
        {
            System.out.println("Der Bereich ist nicht so gut gewählt");    
        }else
        {
            for(int i = 0; i < bereich; i++)
            {
                if(deck.getContent().getFarbe().equals("Pik") || deck.getContent().getFarbe().equals("Kreuz"))
                {
                    schwarz++;
                }
                if(deck.getContent().getFarbe().equals("Karo") || deck.getContent().getFarbe().equals("Herz"))
                {
                    rot++;
                }
                deck.next();
            }
        }
        if(schwarz > rot)
        {
            System.out.println("Schwarz ist größer");
        }
        else
        {
            System.out.println("Rot ist größer");
        }
    }
    public boolean vorhanden(String pFarbe, String pZahl)
    {
        boolean b = false;
        deck.toFirst();
        for(int i = 0; i < length(); i++)
        {
            if(deck.getContent().getFarbe().equals(pFarbe) && deck.getContent().getZahl().equals(pZahl))
            {
                b = true;
            }
            deck.next();
        }
        return b;
    }
    public void aussortierenFarbe(String pFarbe)
    {   
        List<Karte> temp = new List<Karte>();
        deck.toFirst();
        //System.out.println();
        for(int i = 0; i < length(); i++)
        {
            String f;
            f = deck.getContent().getFarbe();
            System.out.println(f);
            if(pFarbe.equals(f))
            {
              
            }
            else
            {
                temp.append(deck.getContent());
            }
            deck.next();
        }
        deck.toFirst();
        while(deck.isEmpty() != true)
        {
            deck.remove();
            
        }
        deck.concat(temp);
    }
        public int length()
    {
        int i = 0;
        List<Karte> temp = new List<Karte>();
        temp.concat(deck);
        temp.toFirst();
        while(!temp.isEmpty())
        {
            temp.remove();
            i++;
            //System.out.println(i);
        }
        return i;
    }
    public void mischen()
    {
      for(int i = 0; i < 1000; i++)
      {
          mischenEinMal();
      }
    }
    public void mischenEinMal()
    {
            int a = (int)(Math.random()*36);
            int b = (int)(Math.random()*36);
            if(b == 36)
            {
                b = 1;
            }
            if(a == b)
            {
                b = b++;
            }
            deck.toFirst();
            for(int i = 0; i < a; i++)
            {
                deck.next();    
            }
            Karte tempA = deck.getContent();
            //System.out.println("Karte A ist " + deck.getContent());
            deck.toFirst();
            for(int i = 0; i < b; i++)
            {
                deck.next();    
            }
            Karte tempB = deck.getContent();
            //System.out.println("Karte B ist " + deck.getContent());
            deck.remove();
            if(deck.getContent() == null){deck.toLast();}
            deck.insert(tempA);
            deck.toFirst();
            for(int i = 0; i < a; i++)
            {
                deck.next();    
            }
            deck.remove();
            if(deck.getContent() == null){deck.toLast();}
            deck.insert(tempB);
    }
    public List<Karte> getDeck()
    {
        return deck;
    } 
}
