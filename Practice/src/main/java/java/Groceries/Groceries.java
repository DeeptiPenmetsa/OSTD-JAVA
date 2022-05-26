package java.Groceries;

import java.util.ArrayList;
import java.util.Scanner;

public class Groceries {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> groceries = new ArrayList<>();
        groceries.add("bananas");
        groceries.add("apples");
        groceries.add("milk");
        groceries.add("oats");
        groceries.add("crackets");
        System.out.println("Enter another item or \"quit\" to exit");
        String newItem = in.nextLine();
        boolean found = false;
        while (!newItem.equalsIgnoreCase("quit")) {
            for (int i = 0; i < groceries.size(); i++) {
                if (newItem.equalsIgnoreCase(groceries.get(i))) {
                    System.out.println("The item is already in the list.");
                    found = true;
                }
            }
            if (!found) {
                groceries.add(newItem);
            }
            System.out.println("Enter another item or \"quit\" to exit");
            newItem = in.nextLine();
        }
        System.out.println("\n Here is the final list: \n" + groceries.toString());
    }
}
