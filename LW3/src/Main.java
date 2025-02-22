import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Audio> playlist = new ArrayList<>();
        List<Audio> albums = new ArrayList<>();
        List<Audio> podcasts = new ArrayList<>();

        try{
            MusicAlbum a = new MusicAlbum("a", "b", new int[]{100, 200, 300}, 20);
            MusicAlbum b = new MusicAlbum("a", "b", new int[]{100, 200, 300}, 20);

            System.out.println(a.hashCode());
            System.out.println(b.hashCode());
        }catch(MusicAlbum.InvalidTimeException | MusicAlbum.InvalidAdDurationException e){
            System.out.println(e.getMessage());
        }
    }
}
