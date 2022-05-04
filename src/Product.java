public class Product {
    private String name;
    private String description;
    private double price;
    private double discountPercentage;
    private boolean isInStock;
    private int amount;


    public Product(String name, String description, double price, double discountPercentage) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.roundPrice();
        this.discountPercentage = discountPercentage;
        isInStock = true;
        amount = 0;
    }

    public void roundPrice() {
        this.price = Math.round(this.price * 10.00) / 10.00;

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public boolean isInStock() {
        return isInStock;
    }

    public int getAmount() {
        return amount;
    }

    public void makeABuying(int amount) {
        this.amount -= amount;
        this.isInStock = amount != 0;
    }

    public void changeStockStatus() {
        isInStock = !this.isInStock;
    }

    @Override
    public String toString() {
        return "name = " + name +
                "\ndescription = '" + description + "'";
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}
