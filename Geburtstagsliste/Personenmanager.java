
public class Personenmanager
{
    private List<Person> geburtstagsliste;
    public Personenmanager()
    {
        geburtstagsliste = new List<Person>();
    }
    
        /**
     * Methode, die eine Referenz auf die in der ZahlList gespeicherte
     * Liste zurückgibt, damit diese an der ListGUI angemeldet werden kann
     */
    public List<Person> getList()
    {
        // put your code here
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
