import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    int cells;
    List<Integer> move;

    public Game(int cells) {
        this.cells = cells;
        this.move = createBoard(cells);
    }

    public List<Integer> getMove() {
        return move;
    }

    private List<Integer> createBoard(int cells) {
        List<Integer> move = new LinkedList<>();

        for (int i=0;i<cells;i++) {
            move.add(-1);
        }

        //Ladders
        move.set(2,21);
        move.set(4,7);
        move.set(10,25);
        move.set(19,28);

        //Snakes
        move.set(26,0);
        move.set(20,8);
        move.set(16,3);
        move.set(18,6);

        return move;
    }

    public int getCells() {
        return cells;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Snake Ladder Game1");
        System.out.println("Please enter the no. of cells of Board");
        Scanner in = new Scanner(System.in);
        int cells = in.nextInt();
        in.nextLine();
        System.out.println("Enter the number of players");
        int player = in.nextInt();
        in.nextLine();
        List<Player> playerList = new LinkedList<>();
        for (int i=0;i<player;i++) {
            System.out.println("Enter name of player"+(i+1));
            playerList.add(new Player(in.nextLine(), 0));
        }
        Game game = new Game(cells);
        Random rand = new Random();
        int round = 1;
        boolean gameFlag = true;
        do {
            for (int i=0;i<player;i++) {
                Player player1 = playerList.get(i);
                System.out.println("Player1: "+player1.getName()+"--------->");
                System.out.println("Press enter to throw dice");
                in.nextLine();
                int dice = generateRandomNumber(rand);
                System.out.println("Dice number= "+dice);
                int newCell = movePlayer(player1, dice, game);
                if (newCell == cells -1) {
                    System.out.println("Player1 "+player1.getName()+" wins");
                    System.out.println("Game1 Over");
                    gameFlag = false;
                    break;
                }
                if (newCell != player1.getCurrCell()) {
                    player1.setCurrCell(newCell);
                    System.out.println(player1.getName()+" is at "+newCell);
                }
                System.out.println();
            }
            if (gameFlag == true) {
                System.out.println("Positions after iteration "+round);
                for (int i=0;i<player;i++) {
                    System.out.println(playerList.get(i).getName()+" at "+playerList.get(i).getCurrCell());
                }
            }
            round++;
        } while(gameFlag);
    }

    private static int movePlayer(Player player, int dice, Game game) {
        int tentativePostion = player.getCurrCell()+dice;
        int newCell;
        if (tentativePostion > game.getCells()-1) {
            System.out.println(player.getName()+" cannot move");
            newCell = player.getCurrCell();
        } else {
            if (game.getMove().get(tentativePostion) == -1) //no snake or ladder
                newCell = tentativePostion;
            else if(game.getMove().get(tentativePostion) > tentativePostion) {
                System.out.println("Yayyy Ladder :)");
                newCell = game.getMove().get(tentativePostion);
            }
            else {
                System.out.println("Snake bite :(");
                newCell = game.getMove().get(tentativePostion);
            }
        }
        return newCell;
    }

    private static int generateRandomNumber(Random rand) {
        int dice = rand.nextInt(6) + 1;
        return dice;
    }
}
