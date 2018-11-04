public class Player {
    String name;
    int currCell;

    public Player(String name, int currCell) {
        this.name = name;
        this.currCell = currCell;
    }

    public String getName() {
        return name;
    }

    public int getCurrCell() {
        return currCell;
    }

    public void setCurrCell(int currCell) {
        this.currCell = currCell;
    }
}
