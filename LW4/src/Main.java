import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MusicAlbum.InvalidTimeException, PodcastSeries.InvalidTimeException, IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - music, 2 - podcast");
        int choice = sc.nextInt();
        sc.nextLine();

        Audio audio = null;
        switch (choice) {
            case 1:
                System.out.print("title: ");
                String title = sc.nextLine();
                System.out.print("artist: ");
                String artist = sc.nextLine();
                System.out.print("num of tracks: ");
                int tracks = sc.nextInt();
                int[] time = new int[tracks];
                for (int i = 0; i < tracks; i++) {
                    System.out.print("track " + (i+1) + ": ");
                    time[i] = sc.nextInt();
                }
                System.out.print("ad time: ");
                int adTime = sc.nextInt();
                audio = new MusicAlbum(title, artist, time, adTime);
                break;
            case 2:
                System.out.print("title: ");
                String p_title = sc.nextLine();
                System.out.print("author: ");
                String author = sc.nextLine();
                System.out.print("num of series: ");
                int series = sc.nextInt();
                int[] ptime = new int[series];
                for (int i = 0; i < series; i++) {
                    System.out.print("podcast #" + (i + 1) + ": ");
                    ptime[i] = sc.nextInt();
                }
                System.out.print("ad time: ");
                int padTime = sc.nextInt();
                audio = new PodcastSeries(p_title, author, ptime, padTime);
                break;
        }

        try (FileOutputStream fos = new FileOutputStream("music.dat")) {
            AudioSerializer.serialize(audio, fos);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        /*
        try(FileInputStream fis = new FileInputStream("music.dat")){
            Audio deserialized = AudioSerializer.deserialize(fis);
            System.out.println(deserialized.toString());
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        */
    }
}

