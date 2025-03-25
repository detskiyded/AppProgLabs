// Task 5

public class PodcastSeriesFactory implements AudioFactory {
    @Override
    public Audio createInstance() {
        try {
            return new PodcastSeries("Default Podcast", "Default Author", new int[]{100}, 0);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create PodcastSeries", e);
        }
    }
}