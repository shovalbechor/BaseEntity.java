import java.util.ArrayList;
import java.util.Scanner;

public class BaseEntity {
    protected static final Scanner scanner = new Scanner(System.in);
    ArrayList<String> userNames;
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;

    public BaseEntity(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public static boolean checkNamesIsNumeric(String text) {
        if (text.matches("/[0-9]/g")) {
            System.out.println("FirstName and lastName can't contain numbers!");
            return true;
        }
        return false;
    }

    public ArrayList<String> getUserNames() {
        return userNames;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
