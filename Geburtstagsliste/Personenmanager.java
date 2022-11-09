
public class Personenmanager extends List<Person>
{
    private List<Person> geburtstagsliste;
    public Personenmanager()
    {
        personenAusTxtHinzufügen();
        geburtstagsliste = new List<Person>();
    }
    
    public void personenAusTxtHinzufügen()
    {
            
    }
    
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
    
        public void testlisteAnlegen(){
        erstellenUndHinzufügen("Herrmann Müller", "12.12.2000");
        erstellenUndHinzufügen("Tim Müller", "20.01.2006");
        erstellenUndHinzufügen("Toben Müller", "23.04.1999");
    }

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
