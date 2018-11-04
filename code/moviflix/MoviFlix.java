import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoviFlix {

    private static List<Movie> movieList = new ArrayList<>();
    public static void main(String[] args) {
        File file = new File("/Users/kokil.jain/Desktop/MovieFlix/src/Input");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String movieName = sc.nextLine();
            String genre = sc.nextLine();
            String viewer = sc.nextLine();
            String role = sc.nextLine();
            int rating = sc.nextInt();
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            movieList.add(new Movie(movieName, genre, rating));

        }

    }
}
