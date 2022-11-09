
<<<<<<< HEAD
public class Personenmanager extends List<Person>
=======
public class Personenmanager
>>>>>>> effb5b8b9b4957d5dab08fa387990d2e90452f85
{
    private List<Person> geburtstagsliste;
    public Personenmanager()
    {
<<<<<<< HEAD
        personenAusTxtHinzufügen();
        geburtstagsliste = new List<Person>();
    }
    
    public void personenAusTxtHinzufügen()
    {
            
    }
    
=======
        geburtstagsliste = new List<Person>();
    }
    
        /**
     * Methode, die eine Referenz auf die in der ZahlList gespeicherte
     * Liste zurückgibt, damit diese an der ListGUI angemeldet werden kann
     */
>>>>>>> effb5b8b9b4957d5dab08fa387990d2e90452f85
    public List<Person> getList()
    {
        return geburtstagsliste;
    }

    public void erstellenUndHinzufügen(String pName, String Datum)
    {
        Person person = new Person(pName, Datum);
        geburtstagsliste.insert(person);
    }
    
    public void hinzufügen(Person pPerson)
    {
        geburtstagsliste.insert(pPerson);
    }
<<<<<<< HEAD
    
        public void testlisteAnlegen(){
        erstellenUndHinzufügen("Herrmann Müller", "12.12.2000");
        erstellenUndHinzufügen("Tim Müller", "20.01.2006");
        erstellenUndHinzufügen("Toben Müller", "23.04.1999");
    }
=======
>>>>>>> effb5b8b9b4957d5dab08fa387990d2e90452f85

    public void entfernen(String pName)
    {
        if(geburtstagsliste.isEmpty() == false){
            geburtstagsliste.toFirst();
            if(geburtstagsliste.current.equals(pName))
            {
                geburtstagsliste.remove();
            }
            else if(!geburtstagsliste.current.equals(null))
            {
                geburtstagsliste.next();
            }
            else
            {
                System.out.println("Keine Person mit dem Namen " + pName + " gefunden.");
            }
        }
    }
}
