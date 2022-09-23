
/**
 * Beschreiben Sie hier die Klasse PalindromTest.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class PalindromPrüfer
{
    private String zusammenGeklebterSatz;
    private Stack<Character> stapel;
    private String gedrehterText = "";
    public PalindromPrüfer()
    {
        stapel = new Stack<Character>();
    }

    public String satzZusammenkleben(String a)
    {
        a = a.toLowerCase();
        zusammenGeklebterSatz = "";
        for(int i = 0; i < a.length(); i++)
        {
            zusammenGeklebterSatz = zusammenGeklebterSatz + Character.toString(a.charAt(i));
            zusammenGeklebterSatz = zusammenGeklebterSatz.trim();
        }
        return zusammenGeklebterSatz;
    }
    public String dreher(String a)
    {
        a = a.toLowerCase();
        gedrehterText = "";
        char[] b = a.toCharArray();
        for(int i = 0; b.length > i; i++)
        {
            stapel.push(b[i]);
        }
        while(stapel.top() != null)
        {
            gedrehterText = gedrehterText + Character.toString(stapel.top());
            gedrehterText = gedrehterText.trim();
            stapel.pop();
        }
        return gedrehterText;
    }
    
    public boolean palindromPruefer(String a)
    {
        dreher(a);
        if(gedrehterText.equals(satzZusammenkleben(a)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}