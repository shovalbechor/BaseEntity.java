
import java.util.ArrayList;

public class Employee extends BaseEntity {
    private static final ArrayList<Employee> employeeAccounts = new ArrayList<>();
    private final Cart myCart = new Cart();
    private Rank rank;

    public Employee(String firstName, String lastName, String userName, String password, Rank rank) {
        super(firstName, lastName, userName, password);
        this.rank = rank;
        employeeAccounts.add(this);
    }

    public static boolean isUserNameExist(String userName) {
        for (Employee user : employeeAccounts) {
            if (userName.equals(user.userName))
                return true;
        }
        return false;
    }

    public static boolean checkPasswordValidity(String password, String userName) {
        for (Employee user : employeeAccounts) {
            if (userName.equals(user.userName) && password.equals(user.password))
                return true;
        }
        return false;
    }

    public static String getNameForUser() {
        String userName;
        userName = scanner.next();
        scanner.nextLine();
        boolean isTaken = Employee.isUserNameExist(userName);
        while (isTaken) {
            System.out.println("userName is taken!");
            userName = scanner.next();
        scanner.nextLine();
            isTaken = Employee.isUserNameExist(userName);
        }
        return userName;
    }

    public static Employee isUserExist(String userName, String password) {
        for (Employee employee : employeeAccounts) {
            if (employee.userName.equals(userName) && employee.password.equals(password)) {
                return employee;
            }
        }
        return null;
    }
// 1 2 a a a 123456


    public void AddProductToStore(Shop shop) {
        String name,description;
        double price,percentages;
        System.out.println("Enter name of product:");
        name = scanner.nextLine();
        System.out.println("Enter description of product:");
        description = scanner.nextLine();
        System.out.println("Enter price of product:");
        price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter percentages of product(in decimal) :");
        percentages = scanner.nextDouble();
        scanner.nextLine();
        Product product = new Product(name,description,price,percentages);
        shop.AddProduct(product);
    }

    public void printHighestClient(ArrayList<Client> clients) {
        double max = 0;
        Client maxClient = null;
        for (Client client : clients) {
            if (client.getPurchases() >= max) {
                max = client.getPurchases();
                maxClient = client;
            }
        }
        System.out.println("-------------");
        System.out.println(maxClient);
        System.out.println("-------------");
    }

    public void printMembersList(ArrayList<Client> clients) {
        for (Client client : clients) {
            if (client.isMember()) {
                System.out.println("-------------");
                System.out.println(client);
                System.out.println("-------------");
            }
        }
    }

    public void printClientsList(ArrayList<Client> clients) {
        for (Client client : clients) {
            System.out.println("-------------");
            System.out.println(client);
            System.out.println("-------------");
        }
    }

    public void printPurchaseClients(ArrayList<Client> clients) {
        for (Client client : clients) {
            if (client.getPurchases() > 0) {
                System.out.println("-------------");
                System.out.println(client);
                System.out.println("-------------");
            }
        }
    }

    public void changeStatus(Shop shop) {
        ArrayList<Product> products = shop.getProductsOfStore();
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("which product do you want to change?" );
        String answer = scanner.next();
        scanner.nextLine();
        Product product = shop.searchProduct(answer);
        if (product == null) {
            System.out.println("item don't exsits!");
            return;
        }
        product.changeStockStatus();
    }

    public Cart getMyCart() {
        return myCart;
    }

    public Rank getRank() {
        return rank;
    }
}
