
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    ArrayList<Photo> photos = Photo.getPhotosFrom("d_pet_pictures.txt");
    public void sortTags()
    {
        for(int i = 0; i >= photos.size(); i++)
        {
            if(photos.get(i).getNumberTags() < photos.get(i+1).getNumberTags())
            {
                swap(photos.get(i), photos.get(i+1));
            }
        }
    }
    public void swap(Photo A, Photo B)
    {
        int AIndex = photos.indexOf(A);
        int BIndex = photos.indexOf(B);
        photos.remove(A);
        photos.add(AIndex, B);
        photos.remove(B);
        photos.add(BIndex, A);
    }
    //https://github.com/chameleonTK/hashcode-2019
}