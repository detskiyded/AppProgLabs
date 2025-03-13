import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Audio> playlist = new ArrayList<>();
        List<Audio> albums = new ArrayList<>();
        List<Audio> podcasts = new ArrayList<>();
        List<Audio> duplicates = new ArrayList<>();

        try{
            playlist.add(new MusicAlbum("a", "b", new int[]{100, 200, 300}, 20));
            playlist.add(new MusicAlbum("c", "d", new int[]{150, 250, 350}, 20));
            playlist.add(new MusicAlbum("a", "b", new int[]{100, 200, 300}, 20));

            playlist.add(new PodcastSeries("e", "f", new int[]{1000, 3600, 1800}, 120));
            playlist.add(new PodcastSeries("g", "h", new int[]{1500, 4100, 2300}, 150));

            for (int i =0; i < playlist.size(); i++){
                for (int j = i+1; j < playlist.size(); j++) {
                    if (playlist.get(i).equals(playlist.get(j))) {
                        duplicates.add(playlist.get(i));
                    }
                }
            }
            for (Audio audio : playlist) {
                if (audio.getClass() == MusicAlbum.class) {
                    albums.add(audio);
                }
                else{
                    podcasts.add(audio);
                }
            }

            System.out.println(playlist);
            System.out.println(albums);
            System.out.println(podcasts);
            System.out.println(duplicates);
        }catch(MusicAlbum.InvalidTimeException |
               MusicAlbum.InvalidAdDurationException |
               PodcastSeries.InvalidTimeException |
               PodcastSeries.InvalidAdDurationException e){
            System.out.println(e.getMessage());
        }
    }
}
