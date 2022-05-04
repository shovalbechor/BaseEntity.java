
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    private final ArrayList<Product> productsOfStore;
    private final Scanner scanner = new Scanner(System.in);

    public Shop() {
        this.productsOfStore = new ArrayList<Product>();
    }

    public void AddProduct(Product product) {
        productsOfStore.add(product);
    }

    public Product searchProduct(String search) {
        for (Product product : productsOfStore) {
            if (search.equals(product.getName()))
                return product;
        }
        return null;
    }

    public ArrayList<Product> getProductsOfStore() {
        return productsOfStore;
    }

    public ArrayList<Product> getAvailableProducts() {
        ArrayList<Product> availableProducts = new ArrayList<>();
        for (Product product : productsOfStore)
            if (product.isInStock())
                availableProducts.add(product);
        return availableProducts;
    }
}



