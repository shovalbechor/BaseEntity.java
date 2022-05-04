import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cart {
    private static final ArrayList<Product> productsBought = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public void makeAPurchase(Shop myShop, boolean isEmployee, Rank rank, boolean isMember) {
        ArrayList<Product> availableProducts = myShop.getAvailableProducts();
        for (int i = 0; i < availableProducts.size(); i++) {
            System.out.println(i + 1 + ".\n " + availableProducts.get(i));
        }
        System.out.println("which product do you like?");
        System.out.println("enter -1 to return to the main options");
        int choice = 0, quantity;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("write the index!");
            scanner.nextLine();
            makeAPurchase(myShop, isEmployee, rank, isMember);
            return;
        }
        if (availableProducts.size() == 0){
            System.out.println("there are not items!");
            Main.start(true);
        }
        if (choice == -1) {
            showCart(isEmployee, rank, isMember);
            Main.start(true);
            return;
        }
        while (choice < 1 || choice > availableProducts.size()) {
            System.out.println("chose from the list!");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("how many items you want to buy?");
        quantity = scanner.nextInt();
        scanner.nextLine();
        Product product = availableProducts.get(choice - 1);
        for (int i = 0; i < quantity; i++) {
            addToCart(product);
        }
        product.makeABuying(quantity);
        showCart(isEmployee, rank, isMember);
        makeAPurchase(myShop, isEmployee, rank, isMember);
    }

    public void showCart(boolean isEmployee, Rank rank, boolean isMember) {
        double sum = 0;
        for (Product product : productsBought) {
            if (!isEmployee && isMember)
                sum += product.getPrice() * product.getDiscountPercentage();
            else if (isEmployee) {
                sum += calculatedPrice(rank, product);
            } else
                sum += product.getPrice();
            System.out.println("------------------");
            System.out.println(product);
            System.out.println("------------------");
        }
        System.out.println("and the price of all of that is:\n\t" + sum);
    }

    private double calculatedPrice(Rank rank, Product product) {
        double percentage = (double) (rank.ordinal() + 1) / 10.0;
        double price = product.getPrice() - (product.getPrice() * percentage);
        return price;
    }

    private void addToCart(Product product) {
        productsBought.add(product);
    }
}
