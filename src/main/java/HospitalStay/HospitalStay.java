package HospitalStay;

import java.util.Scanner;

public class HospitalStay {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double medCharges = 0, labCharges = 0, hospitalCharges = 0, totalCost;
        String response = "";
        do {
            if (checkOverNightStay()) {
                System.out.println("Enter cost of hospital stay: ");
                hospitalCharges = in.nextDouble();
            } else {
                hospitalCharges = 0;
            }
            System.out.println("Enter the medication charges: ");
            medCharges = in.nextDouble();
            System.out.println("Enter the lab charges: ");
            labCharges = in.nextDouble();
            totalCost = hospitalCharges + medCharges + labCharges;
            printTotal(totalCost);
            System.out.println("\nDo you have another patient? (y/n) ");
            response = in.next();
        } while (response.equalsIgnoreCase("y"));
    }

    private static void printTotal(double totalCost) {
        System.out.printf("The total cost for this patient is: $%6.2f", totalCost);
    }

    public static boolean checkOverNightStay() {
        Scanner in = new Scanner(System.in);
        String response = "";
        System.out.println("Is this an Overnight Stay? (y/n) ");
        response = in.next();
        if (response.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }
}
