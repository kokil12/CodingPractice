import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        System.out.println("Enter number of players");
        Scanner in = new Scanner(System.in);
        int players = in.nextInt();
        in.nextLine();
        List<Player> playerList = new LinkedList<>();
        for (int i=0;i<players;i++) {
            System.out.println("Enter name of player "+(i+1));
            playerList.add(new Player(in.nextLine()));
        }
        System.out.println("Lets begin the game");
        BusinessGame businessGame = new BusinessGame(players, playerList);

        businessGame.play();
        businessGame.calculateFinalBalance();
    }
}
