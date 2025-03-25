import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

// Task 3
public class UnmodifiableAudio implements Audio {
    private final Audio audio;

    public UnmodifiableAudio(Audio audio) {
        this.audio = audio;
    }

    @Override
    public String getName() {
        return audio.getName();
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Modification is not allowed");
    }

    @Override
    public int getTime() {
        return audio.getTime();
    }

    @Override
    public void setTime(int[] time) {
        throw new UnsupportedOperationException("Modification is not allowed");
    }

    @Override
    public int getAdTime() {
        return audio.getAdTime();
    }

    @Override
    public void setAdTime(int adTime) {
        throw new UnsupportedOperationException("Modification is not allowed");
    }

    @Override
    public String getAuthor() {
        return audio.getAuthor();
    }

    @Override
    public void setAuthor(String author) {
        throw new UnsupportedOperationException("Modification is not allowed");
    }

    @Override
    public int getNoAdTime() {
        return audio.getNoAdTime();
    }

    @Override
    public void output(OutputStream out) throws IOException {
        audio.output(out);
    }

    @Override
    public void write(Writer out) throws IOException {
        audio.write(out);
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}