import java.util.LinkedList;
import java.util.List;

public class Player {
    String name;
    List<Hotel> hotelList;
    int position;
    int amount;
    boolean flag;

    public Player(String name) {

        this.name = name;
        this.hotelList = new LinkedList<>();
        amount = 1000;
        position = 0;
        flag = true;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
