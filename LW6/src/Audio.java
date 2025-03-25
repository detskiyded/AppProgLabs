import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.*;
import java.util.Iterator;

// Task 1
public interface Audio extends Serializable, Iterable<Integer> {
    String getName();
    void setName(String name);
    int getTime();
    void setTime(int[] time);
    int getAdTime();
    void setAdTime(int adTime);
    String getAuthor();
    void setAuthor(String author);
    int getNoAdTime();
    void output(OutputStream out) throws IOException;
    void write(Writer out) throws IOException;
}