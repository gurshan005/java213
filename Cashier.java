package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Gurshan bhogal 
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-10-15
 */
public class Cashier {

    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Reads keyboard input. Returns a positive quantity, or 0 if the input is not a
     * valid positive integer.
     *
     * @param scan A keyboard Scanner.
     * @return
     */
    private int askForQuantity(Scanner scan) {
	int quantity = 0;
	System.out.print("How many do you want? ");

	try {
	    String line = scan.nextLine();
	    quantity = Integer.parseInt(line);
	} catch (NumberFormatException nfex) {
	    System.out.println("Not a valid number");
	}
	return quantity;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");

	// your code here
	
	System.out.println("Welcome to WLU Foodorama!");
    printCommands();

    Scanner scanner = new Scanner(System.in);
    Order order = new Order();
    boolean ordering = true;

    while (ordering) {
        System.out.print("Enter a command: ");
        String command = scanner.nextLine();

        try {
            int itemNumber = Integer.parseInt(command);

            if (itemNumber == 0) {
                // End the order
                ordering = false;
                System.out.println("Order completed. Here is your receipt:\n");
                System.out.println(order);
            } else if (itemNumber > 0 && itemNumber <= menu.size()) {
                // Valid menu item number
                MenuItem item = menu.getItem(itemNumber - 1); // Convert to 0-based index
                int quantity = askForQuantity(scanner);

                if (quantity > 0) {
                    order.add(item, quantity);
                } else {
                    System.out.println("Not a valid number");
                }
            } else {
                // Invalid menu item number
                System.out.println("Not a valid number");
                printCommands();
            }
        } catch (NumberFormatException e) {
            // Invalid input, show the menu again
            System.out.println("Not a valid number");
            printCommands();
        }
    }

    scanner.close();
    return order;
}
}

	
