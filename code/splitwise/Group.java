import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Group {
    private static int count = 0;
    int groupId;
    String groupName;
    List<User> userList;
    List<Bill> billList = new LinkedList<>();
    Map<User, Integer> userBalanceMap = new HashMap<>();

    public Group(String groupName, List<User> userList) {
        groupId = ++count;
        this.groupName = groupName;
        this.userList = userList;
        initializeMap();
    }

    private void initializeMap() {
        for (User user : userList) {
            userBalanceMap.put(user, 0);
        }
    }

    public String getGroupName() {
        return groupName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
        userBalanceMap.put(user, 0);
    }

    public void addBill(Bill bill) {
        billList.add(bill);
        updateBalance(bill);
    }

    private void updateBalance(Bill bill) {
        int amount = bill.getAmount();
        int toPay;
        int toGet;
        int equalContribution = amount/userList.size();
        Map<User, Integer> userContributionMap = bill.getUserContributionMap();
        for (Map.Entry<User, Integer> entry : userContributionMap.entrySet()) {
            User user = entry.getKey();
            int balance;
            if (userBalanceMap.containsKey(user)) {
                balance = userBalanceMap.get(user);
            } else {
                balance = 0;
            }

            int userShare = entry.getValue();
            if (userShare > equalContribution) {
                toGet = userShare - equalContribution;
                balance = balance + toGet;
            } else {
                toPay = equalContribution - userShare;
                balance = balance - toPay;
            }
            userBalanceMap.put(user, balance);
        }
    }

    public int getUserBalance(User user) {
        return userBalanceMap.get(user);
    }
}
