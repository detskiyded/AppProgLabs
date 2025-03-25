import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        IntermediateObject intermediate = new IntermediateObject(10);

        WriterThread writer = new WriterThread(intermediate);
        ReaderThread reader = new ReaderThread(intermediate);

        writer.start();
        reader.start();
    }
}

// Промежуточный объект
class IntermediateObject {
    private final double[] array;
    private int currentIndex = 0;

    public IntermediateObject(int size) {
        this.array = new double[size];
        Arrays.fill(array, 0);
    }

    public synchronized void write(double value) {
        while (currentIndex >= array.length) {
            try {
                wait(); // Ждем, если массив заполнен
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        array[currentIndex++] = value;
        System.out.println("Write: " + value + " to position " + (currentIndex - 1));
        notifyAll(); // Уведомляем читающий поток
    }

    public synchronized double read() {
        while (currentIndex == 0) {
            try {
                wait(); // Ждем, если массив пуст
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        double value = array[currentIndex - 1];
        System.out.println("Read: " + value + " from position " + (currentIndex - 1));
        currentIndex--;
        notifyAll(); // Уведомляем пишущий поток
        return value;
    }
}

class WriterThread extends Thread {
    private final IntermediateObject intermediate;

    public WriterThread(IntermediateObject intermediate) {
        this.intermediate = intermediate;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            intermediate.write(i * 10); // Записываем произвольные значения
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class ReaderThread extends Thread {
    private final IntermediateObject intermediate;

    public ReaderThread(IntermediateObject intermediate) {
        this.intermediate = intermediate;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            intermediate.read();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}