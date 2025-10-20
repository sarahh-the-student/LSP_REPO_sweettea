package org.howard.edu.lsp.midterm.question2;

public class Main {
        public static void main(String[] args) {

        // Method overloading is the better approach
        // It provides a cleaner intergave while unifying area calculations
        // under a single class with multiple methods named 'area'.
        // This enhances code readability and maintainability.

        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));
        
        // exeption handling 
        try {
            System.out.println("Attempting to calculate area with an invalid dimension...");
            AreaCalculator.area(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error caught: " + e.getMessage());
        }
    }

}