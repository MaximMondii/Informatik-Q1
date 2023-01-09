
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Photo {
    private char orientation;
    private int numberTags;
    private String [] tags;
    private int id; //number of the photo in the photocollection file d_pet_pictures.txt


    public Photo(char orientation, String[] tags) {
        this.orientation = orientation;
        this.tags = tags;
    }
    public Photo(String line){
        String [] params = line.split(" ");
        orientation = params[0].charAt(0);
        numberTags = Integer.parseInt(params[1]);
        tags = new String [numberTags];
        for(int i = 0; i < numberTags; i++){
            tags[i] = params[i+2];
        }
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public int getNumberTags() {
        return numberTags;
    }

    public void setNumberTags(int numberTags) {
        this.numberTags = numberTags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public boolean hasTag(String tag){
        boolean gefunden = false;
        for (String t : tags){
            if(tag.equals(t)){
                gefunden = true;
                break;
            }

        }
        return gefunden;
    }

    public String toString(){
        String ausgabe =  orientation + " " + numberTags + " ";
        for(String t: tags){
            ausgabe += (t + " ");
        }
        return ausgabe;
    }

    public int getInterestFactor(Photo nextPhoto){
        int minimum = 0;
        ArrayList<String> commonTags = new ArrayList<String>();
        ArrayList<String> tagsPhoto1 = new ArrayList<String>();
        ArrayList<String> tagsPhoto2 = new ArrayList<String>();

        for(String t1: this.tags){
            tagsPhoto1.add(t1);
        }

        for(String t2: nextPhoto.getTags()){
            tagsPhoto2.add(t2);
        }

        ArrayList<String> toRemove = new ArrayList<String>();

        for(String t3:tagsPhoto1){
            if(containsEqualCase(t3,tagsPhoto2)){
                commonTags.add(t3);
                toRemove.add(t3);
            }
        }

        tagsPhoto1.removeAll(toRemove);
        tagsPhoto2.removeAll((toRemove));

        commonTags.trimToSize();
        tagsPhoto1.trimToSize();
        tagsPhoto2.trimToSize();

        //Kontrollausgabe
        System.out.println(commonTags.toString());

        if (commonTags.size() <= tagsPhoto1.size() && commonTags.size() <= tagsPhoto2.size()){
            return commonTags.size();
        }
        else if (tagsPhoto1.size() <= commonTags.size() && tagsPhoto1.size()<= tagsPhoto2.size()){
            return tagsPhoto1.size();
        }
        else if (tagsPhoto2.size() <= commonTags.size() && tagsPhoto2.size()<= tagsPhoto1.size()) {
            return tagsPhoto2.size();

        }
        return minimum;

    }



    public static ArrayList<Photo> getPhotosFrom(String filename){
        ArrayList<Photo> list = new ArrayList<Photo>();
        String line;
        Photo photo;
        int numberOfPhotos = 0;
        int numberOfPhotosInFile = 0;
//dies ist ein try-Block: Wir versuchen eine Datei zu öffnen, wir wissen aber nicht, ob das klappt (die Datei könnte nicht so heißen oder für uns nicht lesbar sein
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
//wenn das Öffnen geklappt hat, lesen wir nacheinander immer eine Zeile, bis keine Zeilen mehr in der Datei stehen und wir am Ende angekommen sind.
//Bei jeder Zeile erhöhen wir die numberOfPhotos
            //Die Anzehl der Photos vorlesen
            numberOfPhotosInFile = Integer.parseInt(br.readLine());
            while ((line =br.readLine()) != null) {
                photo = new Photo(line);
                photo.setId(numberOfPhotos);
                list.add(photo);
                numberOfPhotos++;
            }
//An Ende schliessen wir die Datei, damit auch andere Programme auf die Datei zugreifen können
            br.close();
        }
//Falls irgendein Fehler passiert, öffnen wir ein Popup-Fenster und melden dies dem User.
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Lesen der Datei: "+ filename +"\n"
                    +e.getMessage(), "FEHLER", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

    /**
     * Diese Methode ist optimiert dafür eine Datei mit den gewählten Slides zu schreiben
     * Falls es schon eine Datei mit dem übergebenen Namen gibt, wird diese gelöscht
     * @param filename
     * @param list
     */
    public static void writePhotosToFile(String filename, ArrayList<Photo> list){

        PrintWriter pWriter = null;


        try {
//Wir öffnen die Datei zum Schreiben „Writer“ und zum Anhängen „true“
            //falls die Datei schon bestand, wird Sie gelöscht
            //Zum Anähngen würde man als 2. Parameter append mit dem Wert true belegen
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)),true);
            pWriter.append(list.size()+ "\n");
            for (Photo p:list) {

                pWriter.append(p.getId() + " " + p  + "\n");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
//Am Schluss schliessen wir die Datei auf jeden Fall (finally) wieder
        finally {
            if (pWriter != null){
                pWriter.flush();
                pWriter.close();
            }
        }

    }

    public boolean containsEqualCase(String str, ArrayList<String> list){
        for(String i : list){
            if(i.equals(str))
                return true;
        }
        return false;
    }
}
