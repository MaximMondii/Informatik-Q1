
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int interest;
        //Liste von Photos einlesen in die ArrayList photos
        ArrayList<Photo> photos = Photo.getPhotosFrom("d_pet_pictures.txt");
        
        
        //Prüfung des interessierenden Faktors für das 1. und 2. Photo
        System.out.println(photos.get(0).getInterestFactor(photos.get(1)));

        //Wegschreiben einer ArrayList
       
        ArrayList<Photo> testausgabe = new ArrayList<Photo>();
        for(int i = 0; i < 20; i++){
            testausgabe.add(photos.get(i));
        }

        Photo.writePhotosToFile("output_beispiel.txt", testausgabe);

         
    }
    
    public static int hoechsteZahl()
    {
        for(int i = 0; i >= photos.length; i++)
        {
            return int;
        }
    }
}