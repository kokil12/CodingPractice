import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


/*

TODO :
1) Can be improved to make it user interactive rather than hard coding.
2) Code reusability can be done.
*/

public class BusinessGame {
    int numberOfPlayers;
    List<String> board;
    List<Player1> playerList;
    Map<Integer, Hotel> hotelMap;

    public BusinessGame(int numberOfPlayers, List<Player1> playerList) {
        this.numberOfPlayers = numberOfPlayers;
        this.board = createBoard();
        this.playerList = playerList;
        this.hotelMap = createHotelMap();
    }

    private Map<Integer,Hotel> createHotelMap() {
        Map<Integer, Hotel> hotelMap = new HashMap<>();
        for (int i=0;i<board.size();i++) {
            if(board.get(i).equalsIgnoreCase("H")) {
                hotelMap.put(i, new Hotel());
            }
        }
        return hotelMap;
    }

    private List<String> createBoard() {
        String[] arr = {"e","e","j","h","e","t","j","t","e","e","h","j","t","h","e","e","j","h","e","t","j","t","e","e"
                ,"h","j","t","h","j","e","e","j","h","t","e","h","e"};
        List<String> board = new LinkedList<String>();
        for (int i=0; i<arr.length;i++) {
            board.add(arr[i]);
        }
        return board;
    }

    private int playerBalance(Player1 p) {
        int net = p.getAmount();
        for (Hotel h : p.getHotelList()) {
            net = net + 200;
        }
        return net;
    }

    private void calculateBalance() {
        for (Player1 p : playerList) {
            int net = p.getAmount();
            for (Hotel h : p.getHotelList()) {
                net = net + 200;
            }
            System.out.println("Net balance of player "+p.getName()+" is "+p.getAmount());
        }
    }

    public void calculateFinalBalance() {
        int max =0;
        Player1 winner = null;
        for (Player1 p : playerList) {
            int net = p.getAmount();
            for (Hotel h : p.getHotelList()) {
                net = net + 200;
            }
            System.out.println("Net balance of player "+p.getName()+" is "+p.getAmount());
            if (net>max) {
                max = net;
                winner = p;
            }
        }
        if (winner!=null)
            System.out.println("Winner is "+winner.getName()+" with net worth "+max);
        else
            System.out.println("All are losers");
    }

    public void play() {
        Scanner in = new Scanner(System.in);
        int count=0;
        do {
            count++;
            for (int i=0;i<playerList.size();i++) {
                Player1 player = playerList.get(i);
                if (player.isFlag()) {
                    System.out.println("Player1: " + player.getName() + "--------->");
                    System.out.println("Press enter to throw dice");
                    in.nextLine();
                    int dice = generateRandomNumber(2, 12);
                    System.out.println("Dice number= " + dice);
                    movePlayer(player, dice);
                }
            }
            System.out.println("Balance after round "+count+" is");
            calculateBalance();
        } while(count<10);
    }

    private int generateRandomNumber(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }

    private void movePlayer(Player1 player, int dice) {
        int currentPosition = player.getPosition();
        int boardSize = board.size();
        int newPosition = (currentPosition + dice) % boardSize;
        String type = board.get(newPosition);
        if (type.equalsIgnoreCase("J")) {
            System.out.println("Oops Jail it is. Pay 150");
            int newAmount = player.getAmount() - 150;
            if (newAmount<0) {
                if(player.getHotelList().size()!=0) {
                    System.out.println("Selling one hotel to government");
                    Hotel h = player.getHotelList().remove(0);
                    h.setOwner(null);
                    newAmount = newAmount + 50;
                    player.setAmount(newAmount);
                } else {
                    player.setFlag(false);
                    System.out.println("No money. Player1 " + player.getName() + " lost");
                }
            }
            else
                player.setAmount(newAmount);
        } else if (type.equalsIgnoreCase("T")) {
            System.out.println("Yayy. Treasure it is. Badhai ho 200 mile!!!!");
            int newAmount = player.getAmount() + 200;
            player.setAmount(newAmount);
        } else if (type.equalsIgnoreCase("H")) {
            if (hotelMap.get(newPosition).getOwner() == null && player.getAmount() >= 200) {
                System.out.println("You are at hotel with no owner. Hence, buying it with 200 bucks");
                hotelMap.get(newPosition).setOwner(player);
                player.getHotelList().add(hotelMap.get(newPosition));
                int newAmount = player.getAmount() - 200;
                player.setAmount(newAmount);
            } else if (hotelMap.get(newPosition).getOwner() != null) {
                System.out.println("Oops you are at someone else property. Play rent of 50");
                int newAmount = player.getAmount() - 50;
                Player1 owner = hotelMap.get(newPosition).getOwner();
                int ownerBal = owner.getAmount();
                owner.setAmount(ownerBal+50);
                if (newAmount<0) {
                    if(player.getHotelList().size()!=0) {
                        System.out.println("Selling one hotel to government to pay rent");
                        Hotel h = player.getHotelList().remove(0);
                        h.setOwner(null);
                        newAmount = newAmount + 50;
                        player.setAmount(newAmount);
                    } else {
                        player.setFlag(false);
                        System.out.println("No money. Player1 " + player.getName() + " lost");
                    }
                }
                else
                    player.setAmount(newAmount);
            }
        }
    }
}
