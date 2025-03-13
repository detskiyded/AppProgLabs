import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public interface Audio {
    String getName();
    void setName(String name);

    int getTime();
    void setTime(int[] time);

    int getAdTime();
    void setAdTime(int adTime);

    String getAuthor();
    void setAuthor(String author);

    int getNoAdTime();

    void output(OutputStream out) throws IOException; // Запись в байтовый поток
    void write(Writer out) throws IOException; // Запись в символьный поток

}
