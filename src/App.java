import java.util.Scanner;

class App {
    public static void main(String[] args) {
        Slice_o_Heaven pizzaShop = new Slice_o_Heaven();
        System.out.println("Showing default order process with DEF values:");
        Scanner scanner = new Scanner(System.in);
        pizzaShop.takeOrder(scanner);
        pizzaShop.makePizza();
    }
}