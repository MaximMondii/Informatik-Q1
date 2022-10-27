
public class Personenmanager
{
    private List<Person> geburtstagsliste;
    public Personenmanager()
    {
        geburtstagsliste = new List<Person>();
    }
    public void hinzufügen(Person pPerson)
    {
        geburtstagsliste.insert(pPerson);
    }
}
