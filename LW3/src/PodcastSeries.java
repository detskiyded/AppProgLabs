import java.util.Arrays;
import java.util.Objects;

public class PodcastSeries implements Audio {
    // Необъявляемое исключение
    static class InvalidAdDurationException extends RuntimeException {
        public InvalidAdDurationException(String message) {
            super(message);
        }
    }
    // Объявляемое исключение
    public static class InvalidTimeException extends Exception {
        public InvalidTimeException(String message) {
            super(message);
        }
    }

    private String name;
    private String author;
    private int[] time;
    private int timeWithoutAds;
    private int adTime;

    public PodcastSeries(String name, String author, int[] time, int adTime) throws InvalidTimeException {
        this.name = name;
        this.author = author;
        if (adTime < 0) {
            throw new PodcastSeries.InvalidAdDurationException("Invalid ad time");
        }else{
            this.adTime = adTime;
        }
        validateTime(time);
        this.time = new int[time.length];
        for (int i = 0; i < time.length; i++){
            this.time[i] +=  time[i] + adTime;
        }
    }
    private void validateTime(int[] time) throws InvalidTimeException {
        for (int track : time) {
            if (track <= 0) {
                throw new PodcastSeries.InvalidTimeException("Invalid track time");
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getAuthor() {
        return author;
    }
    @Override
    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public int getTime() {
        int res = 0;
        for (int track : time) {
            res += track;
        }
        return res;
    }
    @Override
    public void setTime(int[] time) {
        this.time = time;
    }
    @Override
    public int getAdTime() {
        return adTime;
    }
    @Override
    public void setAdTime(int adTime) {
        this.adTime = adTime;
    }
    @Override
    public int getNoAdTime(){
        for (int track : time){
            timeWithoutAds += track - adTime;
        }
        return timeWithoutAds;
    }

    @Override
    public String toString() {
        return String.format("""
                        Podcast series
                         name: %s
                         author: %s
                         time w/out ads: %d sec
                         time w/out sub: %d sec
                        """,
                getName(), getAuthor(), getNoAdTime(), getTime());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PodcastSeries that = (PodcastSeries) o;
        if (adTime != that.adTime) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(author, that.author)) return false;
        if (time != null ? !Arrays.equals(time, that.time) : that.time != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (time != null ? Arrays.hashCode(time) : 0);
        result = 31 * result + timeWithoutAds;
        result = 31 * result + adTime;
        return result;
    }
}
