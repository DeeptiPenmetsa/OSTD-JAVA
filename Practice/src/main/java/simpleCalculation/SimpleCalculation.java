package simpleCalculation;

import java.util.Scanner;

public class SimpleCalculation {

    public static void main(String[] args) {
        double l, w, h;
        double numWin, winWidth, winHeight;
        double numDoors, doorWidth, doorHeight;
        double surfaceArea;

        System.out.println("Please enter length, width and height of the house to be painted");
        Scanner in = new Scanner(System.in);
        l = in.nextDouble();
        w = in.nextDouble();
        h = in.nextDouble();
        System.out.println("Please enter no.of windows, width and height of the windows");
        numWin = in.nextDouble();
        winWidth = in.nextDouble();
        winHeight = in.nextDouble();
        System.out.println("Please enter no.of doors, width and height of the doors");
        numDoors = in.nextDouble();
        doorWidth = in.nextDouble();
        doorHeight = in.nextDouble();
        surfaceArea = (w * h * 2 + l * h * 2) - (numWin * winWidth * winHeight) - (numDoors * doorHeight * doorWidth);
        System.out.println("The total paintable surface area is: " + surfaceArea);
    }
}
