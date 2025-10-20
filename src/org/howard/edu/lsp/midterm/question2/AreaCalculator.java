package org.howard.edu.lsp.midterm.question2;

public class AreaCalculator {

    // Circle area
    public static double area(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be a positive number.");
        }
        return Math.PI * radius * radius;
    }

    // Rectangle area
    public static double area(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive numbers.");
        }
        return width * height;
    }

    // Triangle (base & height) area
    public static double area(int base, int height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height must be positive numbers.");
        }
        return 0.5 * base * height;
    }

    // Square (side length) area
    public static double area(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side length must be a positive number.");
        }
        return side * side;
    }
}


