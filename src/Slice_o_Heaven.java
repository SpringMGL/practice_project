import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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

    public void takeOrder(Scanner scanner) {
        System.out.println("Order is being taken...");
        simulateProcess(2000);

        System.out.println("Enter three ingredients for your pizza (use spaces to separate ingredients):");
        String ing1 = scanner.next();
        String ing2 = scanner.next();
        String ing3 = scanner.next();
        pizzaIngredients = ing1 + ", " + ing2 + ", " + ing3;

        System.out.println("Enter size of pizza (Small, Medium, Large):");
        String pizzaSize = scanner.next();
        switch (pizzaSize.toLowerCase()) {
            case "small":
                orderTotal += 5;
                break;
            case "medium":
                orderTotal += 8;
                break;
            case "large":
                orderTotal += 12;
                break;
            default:
                System.out.println("Invalid pizza size. Using default price.");
        }

        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese = scanner.next();
        if (extraCheese.equalsIgnoreCase("Y")) {
            orderTotal += 2;
        }

        System.out.println("Enter one side dish (Calzone, Garlic bread, None):");
        sides = scanner.next();
        if (!sides.equalsIgnoreCase("None")) {
            orderTotal += 3;
        }

        System.out.println("Enter drinks (Cold Coffee, Cocoa drink, Coke, None):");
        drinks = scanner.next();
        if (!drinks.equalsIgnoreCase("None")) {
            orderTotal += 2;
        }

        System.out.println("Order details are being processed...");
        simulateProcess(3000);

        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.next();

        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday(scanner);
        } else {
            makeCardPayment(scanner);
        }
    }

    public void isItYourBirthday(Scanner scanner) {
        System.out.println("Enter your birthday (MM/dd/yyyy):");
        String birthdateStr = scanner.next();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date birthdate = null;
        try {
            birthdate = sdf.parse(birthdateStr);
        } catch (ParseException e) {
            System.out.println("Error parsing birthdate: " + e.getMessage());
            return;
        }

        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthdate);
        Calendar nowCal = Calendar.getInstance();

        int age = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        if (nowCal.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        if (age < 18 && nowCal.get(Calendar.MONTH) == birthCal.get(Calendar.MONTH) &&
                nowCal.get(Calendar.DAY_OF_MONTH) == birthCal.get(Calendar.DAY_OF_MONTH)) {
            System.out.println("Congratulations! You pay only half the price for your order");
            orderTotal /= 2;
        } else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
            makeCardPayment(scanner);
        }
    }

    public void makeCardPayment(Scanner scanner) {
        long cardNumber = 0;
        while (true) {
            try {
                System.out.println("Enter your card number:");
                cardNumber = scanner.nextLong();
                break;
            } catch (Exception e) {
                System.out.println("Invalid card number. Please enter a valid long number.");
                scanner.nextLine();
            }
        }

        System.out.println("Enter the card’s expiry date (MM/yyyy):");
        String expiryDate = scanner.next();

        int cvv = 0;
        while (true) {
            try {
                System.out.println("Enter the card’s cvv number (3 digits):");
                cvv = scanner.nextInt();
                if (String.valueOf(cvv).length() == 3) {
                    break;
                } else {
                    System.out.println("Invalid CVV number. Please enter a 3-digit number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid CVV number. Please enter a valid 3-digit number.");
                scanner.nextLine();
            }
        }

        processCardPayment(cardNumber, expiryDate, cvv);
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);
        int cardLength = cardNumberStr.length();
        if (cardLength == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
            return;
        }

        int firstCardDigit = 0;
        try {
            firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
            System.out.println("First card digit: " + firstCardDigit);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing first card digit: " + e.getMessage());
            return;
        }

        long blacklistedNumber = 12345678901234L;
        if (cardNumber == blacklistedNumber) {
            System.out.println("Card is blacklisted. Please use another card");
            return;
        }

        int lastFourDigits = 0;
        try {
            lastFourDigits = Integer.parseInt(cardNumberStr.substring(cardNumberStr.length() - 4));
            System.out.println("Last four digits of card: " + lastFourDigits);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing last four digits of card: " + e.getMessage());
            return;
        }

        String cardNumberToDisplay = cardNumberStr.charAt(0) +
                cardNumberStr.substring(1, cardNumberStr.length() - 4).replaceAll(".", "*") +
                cardNumberStr.substring(cardNumberStr.length() - 4);
        System.out.println("Card number to display: " + cardNumberToDisplay);
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

    public void makePizza() {
        System.out.println("Pizza is being made!");
        simulateProcess(8000);
        System.out.println("Pizza is ready!");
        printReceipt();
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        StringBuilder display = new StringBuilder();
        display.append("Pizza of the day: ").append(pizzaOfTheDay);
        display.append("\nSide of the day: ").append(sideOfTheDay);
        display.append("\nSpecial price: ").append(specialPrice);
        System.out.println(display.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Slice_o_Heaven soh = new Slice_o_Heaven();
        soh.takeOrder(scanner);
        soh.makePizza();
        soh.specialOfTheDay("Margherita Pizza", "Fries", "$12");
        scanner.close();
    }
}