public class User {
    private static int count = 0;
    int userId;
    String userName;

    public User(String userName) {
        this.userName = userName;
        userId = ++count;
    }

    public String getUserName() {
        return userName;
    }
}
