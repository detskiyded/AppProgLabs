public class Main {
    public static void main(String[] args) throws MusicAlbum.InvalidTimeException, PodcastSeries.InvalidTimeException {
        //Task 2
        MusicAlbum album = new MusicAlbum("Album1", "Author1", new int[]{180, 240, 300}, 30);

        System.out.println("Tracks duration in MusicAlbum:");
        for (int trackTime : album) {
            System.out.println(trackTime);
        }

        PodcastSeries podcast = new PodcastSeries("Podcast1", "Author2", new int[]{3000, 4000, 5000}, 60);

        System.out.println("\nTracks duration in PodcastSeries:");
        for (int trackTime : podcast) {
            System.out.println(trackTime);
        }

        // Task 6
        AudioSerializer.setAudioFactory(new MusicAlbumFactory());
        Audio album1 = AudioSerializer.createInstance();
        System.out.println(album1);

        AudioSerializer.setAudioFactory(new PodcastSeriesFactory());
        Audio podcast1 = AudioSerializer.createInstance();
        System.out.println(podcast1);
    }
}
