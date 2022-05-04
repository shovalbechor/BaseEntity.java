import java.util.ArrayList;

public class Client extends BaseEntity{
    private static final ArrayList<Client> clientAccounts = new ArrayList<>();
    private final Cart myCart = new Cart();
    private final boolean isMember;
    private double purchases;

    public Client(String firstName, String lastName, String userName, String password, boolean isAMember) {
        super(firstName, lastName, userName, password);
        this.isMember = isAMember;
        this.purchases = 0;
        clientAccounts.add(this);

    }

    public static boolean isUserNameExist(String userName) {
        for (Client user : clientAccounts) {
            if (userName.equals(user.userName))
                return true;
        }
        return false;
    }

    public static Client isUserExist(String userName, String password) {
        for (Client client : clientAccounts) {
            if (client.userName.equals(userName) && client.password.equals(password)) {
                return client;
            }
        }
        return null;
    }

    public static String getNameForUser() {
        String userName;
        userName = scanner.next();
        scanner.nextLine();
        boolean isTaken = isUserNameExist(userName);
        while (isTaken) {
            System.out.println("userName is taken!");
            userName = scanner.next();
        scanner.nextLine();
            isTaken = isUserNameExist(userName);
        }
        return userName;
    }

    public static boolean isAMember() {
        System.out.println("Are u a member?");
        System.out.println("yes or no?");
        String choice = scanner.next();
        scanner.nextLine();
        return switch (choice) {
            case "yes" -> true;
            case "no" -> false;
            default -> isAMember();
        };
    }

    public boolean isMember() {
        return isMember;
    }

    public static ArrayList<Client> getClientAccounts() {
        return clientAccounts;
    }

    @Override
    public String toString() {
        return "\n\tfirstName = '" + firstName +
                "\n\tlastName = '" + lastName +
                "\n\tuserName = '" + userName +
                "\n\tpassword = '" + password +
                "\n\tMember = " + isMember;
    }

    public double getPurchases() {
        return purchases;
    }


    public void printWelcome() {
        if (isMember)
            System.out.println(firstName + " " + lastName + " (VIP)!");
        else
            System.out.println(firstName + " " + lastName + "!");
    }


    public Cart getCart() {
        return myCart;
    }
}
