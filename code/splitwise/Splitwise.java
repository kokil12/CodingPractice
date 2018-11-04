import java.util.*;

public class Splitwise {

    public static Group createGroup(String groupName, List<User> groupUsers) {
        Group group = new Group(groupName, groupUsers);
        return group;
    }

    public static Group findGroup(String groupName, List<Group> groupCollection) {
        for (Group group : groupCollection) {
            if (group.getGroupName().equalsIgnoreCase(groupName)) {
                return group;
            }
        }
        return null;
    }

    public static User findUser(String userName, List<User> userCollection) {
        for (User user : userCollection) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null;
    }

    public static void addUserToGroup(Group group, User user) {
        group.addUser(user);
    }

    public static void main(String[] args) {

        System.out.println("Welcome to the Splitwise Application");

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        String s, groupName, userName;
        Group group;
        List<Group> groupCollection = new LinkedList<>();
        List<User> userCollection = new LinkedList<>();
        List<Bill> billCollection = new LinkedList<>();

        do {
            System.out.println("Select one of the options to perform action required");
            System.out.println("1. Create Group");
            System.out.println("2. Add user to existing group");
            System.out.println("3. Register bill to group");
            System.out.println("4. Show group wise balance of user");

            int option = in.nextInt();
            in.nextLine();

            switch (option) {

                case 1:
                    System.out.println("Please enter the group name:");
                    groupName = in.nextLine();
                    List<User> groupUsers = new LinkedList<>();
                    System.out.println("Do you want to add users? Press y/n");
                    s = in.nextLine();
                    if (!s.equalsIgnoreCase("y")) {
                        groupCollection.add(createGroup(groupName, groupUsers));
                        System.out.println("An empty group created");
                        break;
                    }
                    do {
                        System.out.println("Please enter user name to be added into this group");
                        userName = in.nextLine();
                        User user = findUser(userName, userCollection);
                        if (user == null) {
                            User newUser = new User(userName);
                            userCollection.add(newUser);
                            groupUsers.add(newUser);
                        } else if(!groupUsers.contains(user)){
                            groupUsers.add(user);
                        }
                        System.out.println("Want to add more user. Press y/n");
                        s = in.nextLine();
                    }while (s.equalsIgnoreCase("y"));

                    groupCollection.add(createGroup(groupName, groupUsers));
                    System.out.println("Group created successfully");
                    break;

                case 2:
                    System.out.println("Please enter group name where you want to add user:");
                    groupName = in.nextLine();
                    group = findGroup(groupName, groupCollection);
                    if (group != null) {
                        System.out.println("Enter user name to be added to this group");
                        userName = in.nextLine();
                        User user = findUser(userName, userCollection);
                        if (user != null && !group.getUserList().contains(user)) {
                            addUserToGroup(group, user);
                        } else {
                            User newUser = new User(userName);
                            userCollection.add(newUser);
                            addUserToGroup(group, newUser);
                        }
                        System.out.println("User added successfully");
                    } else {
                        System.out.println("Incorrect group name. Try again");
                    }
                    break;

                case 3:
                    Map<User, Integer> userShareMap = new HashMap<>();
                    System.out.println("Please enter the following bill details");
                    System.out.println("Group Name");
                    groupName = in.nextLine();
                    group = findGroup(groupName, groupCollection);
                    if (group == null) {
                        System.out.println("Group doesn't exist. Please try creating group and then add bill");
                        break;
                    }
                    System.out.println("Amount");
                    int billAmount = in.nextInt();
                    in.nextLine();
                    int checkAmount = 0;
                    List<User> userList = group.getUserList();
                    for (User user : userList) {
                        System.out.println("Enter share of "+user.getUserName()+":");
                        int share = in.nextInt();
                        in.nextLine();
                        userShareMap.put(user, share);
                        checkAmount = checkAmount + share;
                    }
                    if (checkAmount != billAmount) {
                        System.out.println("Shares entered doesn't match with bill amount. Please try again");
                    } else {
                        Bill bill = new Bill(billAmount, group, userShareMap);
                        billCollection.add(bill);
                        group.addBill(bill);
                        System.out.println("Bill addition successfull");
                    }
                    break;

                case 4:
                    int totalBalance = 0;
                    System.out.println("Please enter the user name to see group wise balance");
                    userName = in.nextLine();
                    User user = findUser(userName, userCollection);
                    if (user != null) {
                        for (Group group1 : groupCollection) {
                            if (group1.getUserList().contains(user)) {
                                int balance = group1.getUserBalance(user);
                                System.out.println("Balance for the group "+group1.getGroupName()+" is "+balance);
                                totalBalance = totalBalance + balance;
                            }
                        }
                        System.out.println("Total balance for "+userName+" is "+totalBalance);
                    } else {
                        System.out.println("No entries for this user. Try again");
                    }
                    break;

                 default:
                    System.out.println("Please enter the correct option");
                    break;
            }

            System.out.println("Back to main menu? Press Y/N");
            s = in.nextLine();

        }while (s.equalsIgnoreCase("y"));

        System.out.println("Thank you for using the app, Please do visit again");

    }
}
