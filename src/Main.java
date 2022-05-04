import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Shop myShop = new Shop();

    public static void main(String[] args) {
        start(false);
    }

    public static void start(boolean notFirstTime) {
        if (notFirstTime)
            System.out.println("hello again!");
        System.out.println("Choice form this menu:");
        System.out.println("\tEnter 1 to create new user");
        System.out.println("\tEnter 2 to log in to an existing account");
        System.out.println("\tEnter 3 to exit the application");
        int choice;
        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case Def.CREATE_NEW_ACCOUNT -> createNewAccount();
            case Def.LOG_TO_ACCOUNT -> logToAccount();
            case Def.EXIT -> System.exit(0);
            default -> start(true);
        }
    }

    private static void logToAccount() {
        int choice;
        String userName, password;
        System.out.println("do you want to login to a client account or employee account:");
        System.out.println("\tEnter 1 for an employee");
        System.out.println("\tEnter 2 for an client");
        choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter username and password:");
        System.out.println("\tUsername:");
        userName = scanner.next();
        scanner.nextLine();
        System.out.println("\tPassword:");
        password = scanner.next();
        scanner.nextLine();
        switch (choice) {
            case Def.LOGIN_TO_EMPLOYEE_ACCOUNT -> loginToEmployeeAccount(userName, password);
            case Def.LOGIN_TO_CLIENT_ACCOUNT -> loginToClientAccount(userName, password);
            default -> logToAccount();
        }

    }

    private static void loginToClientAccount(String userName, String password) {
        Client client = Client.isUserExist(userName, password);
        if (client == null) {
            System.out.println("userName or password is wrong");
            start(true);
            return;
        }
        client.printWelcome();
        client.getCart().makeAPurchase(myShop, false, null, client.isMember());


    }

    private static void loginToEmployeeAccount(String userName, String password) {
        Employee employee = Employee.isUserExist(userName, password);
        if (employee == null) {
            System.out.println("userName or password is wrong");
            start(true);
            return;
        }
        System.out.println(employee.firstName + " " + employee.lastName + " (" + employee.getRank() + ")!");
        getOptions(employee);

    }

    public static void getOptions(Employee employee) {
        ArrayList<Client> clients = Client.getClientAccounts();
        System.out.println("choice from the following list");
        System.out.println("\t1. print clients list");
        System.out.println("\t2. print member clients list");
        System.out.println("\t3. print client that made one purchase");
        System.out.println("\t4. print te highest purchaser client");
        System.out.println("\t5. add product to the store");
        System.out.println("\t6. change the status of a product");
        System.out.println("\t7. make a purchase");
        System.out.println("\t8. logout");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case Def.PRINT_CLIENTS_LIST -> {
                employee.printClientsList(clients);
            }
            case Def.PRINT_MEMBERS_LIST -> {
                employee.printMembersList(clients);
            }
            case Def.PRINT_PURCHASE_CLIENTS -> {
                employee.printPurchaseClients(clients);
            }
            case Def.PRINT_HIGHEST_CLIENT -> {
                employee.printHighestClient(clients);
            }
            case Def.ADD_PRODUCT -> {
                employee.AddProductToStore(myShop);
            }
            case Def.CHANGE_STATUS -> {
                employee.changeStatus(myShop);
            }
            case Def.MAKE_A_PURCHASE -> {
                employee.getMyCart().makeAPurchase(myShop, true, employee.getRank(), false);
            }
            case Def.LOGOUT -> {
                Main.start(true);
            }
            default -> {
                System.out.println("select from 1-8");
                getOptions(employee);
            }
        }
        getOptions(employee);
    }

    private static void createNewAccount() {
        System.out.println("Do you want to create an client account or an employee account?");
        System.out.println("\t1 for client");
        System.out.println("\t2 for employee");
        int choice;
        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case Def.CREATE_EMPLOYEE_ACCOUNT -> initEmployeeAccount();
            case Def.CREATE_CLIENT_ACCOUNT -> initClientAccount();
            default -> start(true);
        }
    }

    private static void initClientAccount() {
        System.out.println("Enter a FirstName");
        String firstName = getName();
        System.out.println("Enter a LastName");
        String lastName = getName();
        System.out.println("Enter a UserName");
        String userName = Client.getNameForUser();
        System.out.println("Enter a Password");
        String password = getPassword();
        boolean isAMember = Client.isAMember();
        new Client(firstName, lastName, userName, password, isAMember);
        start(true);
    }

    private static String getPassword() {
        String password;
        password = scanner.next();
        scanner.nextLine();
        while (password.length() < 6) {
            System.out.println("password must be in length of 6 chars!");
            password = scanner.next();
            scanner.nextLine();
        }
        return password;
    }

    private static String getName() {
        String name;
        name = scanner.next();
        scanner.nextLine();
        boolean testResult = BaseEntity.checkNamesIsNumeric(name);
        while (testResult) {
            System.out.println("the input contain numbers!");
            name = scanner.next();
            scanner.nextLine();
            testResult = BaseEntity.checkNamesIsNumeric(name);
        }
        return name;
    }

    private static void initEmployeeAccount() {
        System.out.println("Enter a FirstName");
        String firstName = getName();
        System.out.println("Enter a LastName");
        String lastName = getName();
        System.out.println("Enter a UserName");
        String userName = Employee.getNameForUser();
        System.out.println("Enter a Password");
        String password = getPassword();
        Rank rank = getRank();
        new Employee(firstName, lastName, userName, password, rank);
        start(true);
    }

    public static Rank getRank() {
        System.out.println("choice rank:");
        System.out.println("\t1 -> regular stuff");
        System.out.println("\t2 -> manager");
        System.out.println("\t3 -> board member");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case Def.REGULAR -> {
                return Rank.REGULAR;
            }
            case Def.MANAGER -> {
                return Rank.MANAGER;
            }
            case Def.BOARD_MEMBER -> {
                return Rank.BOARD_MEMBER;
            }
            default -> {
                return getRank();
            }
        }
    }
}
