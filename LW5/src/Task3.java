import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class Task3 {
    public static synchronized Audio synchronizedAudio(Audio audio) {
        return new SynchronizedAudio(audio);
    }

    private static class SynchronizedAudio implements Audio {
        private final Audio audio;

        public SynchronizedAudio(Audio audio) {
            this.audio = audio;
        }

        @Override
        public synchronized String getName() {
            return audio.getName();
        }

        @Override
        public synchronized void setName(String name) {
            audio.setName(name);
        }

        @Override
        public synchronized int getTime() {
            return audio.getTime();
        }

        @Override
        public synchronized void setTime(int[] time) {
            audio.setTime(time);
        }

        @Override
        public synchronized int getAdTime() {
            return audio.getAdTime();
        }

        @Override
        public synchronized void setAdTime(int adTime) {
            audio.setAdTime(adTime);
        }

        @Override
        public synchronized String getAuthor() {
            return audio.getAuthor();
        }

        @Override
        public synchronized void setAuthor(String author) {
            audio.setAuthor(author);
        }

        @Override
        public synchronized int getNoAdTime() {
            return audio.getNoAdTime();
        }

        @Override
        public synchronized void output(OutputStream out) throws IOException {
            audio.output(out);
        }

        @Override
        public synchronized void write(Writer out) throws IOException {
            audio.write(out);
        }
    }
}