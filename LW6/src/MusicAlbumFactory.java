// Task 5

public class MusicAlbumFactory implements AudioFactory{
    @Override
    public Audio createInstance() {
        try {
            return new MusicAlbum("Default Album", "Default Author", new int[]{100}, 0);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create MusicAlbum", e);
        }
    }
}
