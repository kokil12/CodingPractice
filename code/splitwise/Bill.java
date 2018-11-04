import java.util.Map;

public class Bill {
    int amount;
    Group group;
    Map<User, Integer> userContributionMap;

    public Bill(int amount, Group group, Map<User, Integer> userContributionMap) {
        this.amount = amount;
        this.group = group;
        this.userContributionMap = userContributionMap;
    }

    public int getAmount() {
        return amount;
    }

    public Map<User, Integer> getUserContributionMap() {
        return userContributionMap;
    }
}
