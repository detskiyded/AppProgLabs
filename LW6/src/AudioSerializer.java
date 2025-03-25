import java.io.*;

public class AudioSerializer {
    // Запись в символьный поток
    public static void write(Audio audio, Writer out) throws IOException {
        audio.write(out);
    }

    // Запись в байтовый поток
    public static void output(Audio audio, OutputStream out) throws IOException {
        audio.output(out);
    }

    // Сериализация
    public static void serialize(Audio audio, OutputStream out) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(out))  { // байтовый поток
            oos.writeObject(audio);
        }
    }
    public static Audio deserialize(InputStream in) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            return (Audio) ois.readObject();
        }
    }

    // Task 3
    public static Audio unmodifiableAudio(Audio audio) {
        return new UnmodifiableAudio(audio);
    }
    private static AudioFactory factory = new MusicAlbumFactory();
    public static void setAudioFactory(AudioFactory newFactory) {
        factory = newFactory;
    }
    public static Audio createInstance() {
        return factory.createInstance();
    }
}
