public class Slice_o_Heaven {
    public String storeAddress;
    public String storeName;
    public String storeMenu;
    public String storeHours;
    public long storePhone;

    private String orderID;
    private double orderTotal;
    private String pizzaIngredients;
    private String sides;
    private String drinks;

    public static final String DEF_ORDER_ID = "DEF-SOH-099";
    public static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    public static final double DEF_ORDER_TOTAL = 15.00;

    public Slice_o_Heaven() {
        this.orderID = DEF_ORDER_ID;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;
        this.sides = "";
        this.drinks = "";
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(String pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }

    public String getSides() {
        return sides;
    }

    public void setSides(String sides) {
        this.sides = sides;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    public void takeOrder() {
        System.out.println("Order accepted!");
        System.out.println("Order is being prepared");
        simulateProcess(5000);
        System.out.println("Your order is ready!");
    }

    public void makePizza() {
        System.out.println("Pizza is being made!");
        simulateProcess(8000);
        System.out.println("Pizza is ready!");
        showReceipt();
    }

    private void simulateProcess(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Process was interrupted!");
            Thread.currentThread().interrupt();
        }
    }

    private void printReceipt() {
        System.out.println("********RECEIPT********");
        System.out.println("Order ID: " + orderID);
        System.out.println("Pizza Ingredients: " + pizzaIngredients);
        System.out.println("Order Total: $" + orderTotal);
        System.out.println("Sides: " + sides);
        System.out.println("Drinks: " + drinks);
    }

    public void showReceipt() {
        printReceipt();
    }

    public void processCardPayment(String cardNumber, String expiryDate, int cvv) {
        int cardLength = cardNumber.length();
        if (cardLength == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
            return;
        }

        int firstCardDigit = 0;
        try {
            firstCardDigit = Integer.parseInt(cardNumber.substring(0, 1));
            System.out.println("First card digit: " + firstCardDigit);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing first card digit: " + e.getMessage());
        }

        String blacklistedNumber = "your_blacklisted_number";
        if (cardNumber.equals(blacklistedNumber)) {
            System.out.println("Card is blacklisted. Please use another card");
            return;
        }

        int lastFourDigits = 0;
        try {
            lastFourDigits = Integer.parseInt(cardNumber.substring(cardNumber.length() - 4));
            System.out.println("Last four digits of card: " + lastFourDigits);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing last four digits of card: " + e.getMessage());
        }

        String cardNumberToDisplay = cardNumber.charAt(0) +
                cardNumber.substring(1, cardNumber.length() - 4).replaceAll(".", "*") +
                cardNumber.substring(cardNumber.length() - 4);
        System.out.println("Card number to display: " + cardNumberToDisplay);
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        StringBuilder display = new StringBuilder();
        display.append("Pizza of the day: ").append(pizzaOfTheDay);
        display.append("\nSide of the day: ").append(sideOfTheDay);
        display.append("\nSpecial price: ").append(specialPrice);
        System.out.println(display.toString());
    }

    public static void main(String[] args) {
        Slice_o_Heaven soh = new Slice_o_Heaven();
        soh.processCardPayment("12345678901234", "01/26", 123);
        soh.specialOfTheDay("Margherita Pizza", "Fries", "$12");
    }
}