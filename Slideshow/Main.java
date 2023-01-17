
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Main
{
    public static int main() {
        //Liste von Photos einlesen in die ArrayList photos
        int interest = 0;
        ArrayList<Photo> photos = Photo.getPhotosFrom("d_pet_pictures.txt");
        sort();
        ArrayList<Photo> sorted = Photo.getPhotosFrom("output_beispiel.txt");
        for(int i = 0; i < sorted.size() - 1; i++)
        {
            interest = interest + sorted.get(i).getInterestFactor(sorted.get(i+1));
        }
        return interest;
    }
    
    public static int mainTheSecond() {
        //Liste von Photos einlesen in die ArrayList photos
        ArrayList<Photo> photos = Photo.getPhotosFrom("d_pet_pictures.txt");
        sort();
        ArrayList<Photo> sorted = Photo.getPhotosFrom("output_beispiel.txt");
        int interestFactor = maximizeInterestFactor(sorted);
        return interestFactor;
    }

    public static int maximizeInterestFactor(List<Photo> sortedPhotos) {
        List<Photo> slideshow = new ArrayList<>();
        int interestFactor = 0;
        Photo lastAdded = sortedPhotos.get(0); // start with the first photo
        slideshow.add(lastAdded);
        for (int i = 1; i < sortedPhotos.size(); i++) {
            Photo current = sortedPhotos.get(i);
            int maxInterest = 0;
            Photo next = null;
            for (int j = i; j < sortedPhotos.size(); j++) {
                int interest = current.getInterestFactor(sortedPhotos.get(j));
                if (interest > maxInterest) {
                    maxInterest = interest;
                    next = sortedPhotos.get(j);
                }
            }
            if (next != null) {
                lastAdded = next;
                slideshow.add(next);
                interestFactor += maxInterest;
            }
        }
        return interestFactor;
    }

    public static int test()
    {
        sort();
        ArrayList<Photo> sorted = Photo.getPhotosFrom("output_beispiel.txt");
        return sorted.get(3).getInterestFactor(sorted.get(4));
    }

    public static void sort() 
    {
        ArrayList<Photo> photos = Photo.getPhotosFrom("d_pet_pictures.txt");
        ArrayList<Photo> sortedPhotos = new ArrayList<>(photos.subList(0, photos.size()));
        sortedPhotos.sort(Collections.reverseOrder());
        //Collections.sort(sortedPhotos);
        Photo.writePhotosToFile("output_beispiel.txt", sortedPhotos);
        //sortedPhotos.forEach(System.out::println);
    }
}
