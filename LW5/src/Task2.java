public class Task2 {
    public static void main(String[] args) {
        IntermediateObject intermediate = new IntermediateObject(10);
        Synchronizer synchronizer = new Synchronizer(intermediate);

        Thread writerThread = new Thread(new WriterRunnable(synchronizer));
        Thread readerThread = new Thread(new ReaderRunnable(synchronizer));

        writerThread.start();
        readerThread.start();
    }
}

class Synchronizer {
    private final IntermediateObject intermediate;
    private final Object lock = new Object();
    private boolean isSet = false;

    public Synchronizer(IntermediateObject intermediate) {
        this.intermediate = intermediate;
    }

    public void write(double value) throws InterruptedException {
        synchronized (lock) {
            while (isSet) {
                lock.wait(); // Ждем, пока не освободится место
            }
            intermediate.write(value);
            isSet = true;
            lock.notifyAll();
        }
    }

    public double read() throws InterruptedException {
        synchronized (lock) {
            while (!isSet) {
                lock.wait(); // Ждем, пока данные не будут записаны
            }
            double value = intermediate.read();
            isSet = false;
            lock.notifyAll();
            return value;
        }
    }
}

class WriterRunnable implements Runnable {
    private final Synchronizer synchronizer;

    public WriterRunnable(Synchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                synchronizer.write(i * 10);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class ReaderRunnable implements Runnable {
    private final Synchronizer synchronizer;

    public ReaderRunnable(Synchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                synchronizer.read();
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}