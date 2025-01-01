import java.util.Scanner;

public class Calculator {

    // Method for addition
    public static double add(double a, double b) {
        return a + b;
    }

    // Method for subtraction
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Method for multiplication
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Method for division
    public static double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return a / b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Console Calculator!");
        System.out.println("Available operations: +, -, *, /");

        while (true) {
            try {
                // Input first number
                System.out.print("Enter the first number: ");
                double num1 = Double.parseDouble(scanner.nextLine());

                // Input operation
                System.out.print("Enter an operation (+, -, *, /): ");
                String operation = scanner.nextLine().trim();

                // Input second number
                System.out.print("Enter the second number: ");
                double num2 = Double.parseDouble(scanner.nextLine());

                // Perform operation
                double result;
                switch (operation) {
                    case "+":
                        result = add(num1, num2);
                        break;
                    case "-":
                        result = subtract(num1, num2);
                        break;
                    case "*":
                        result = multiply(num1, num2);
                        break;
                    case "/":
                        result = divide(num1, num2);
                        break;
                    default:
                        System.out.println("Invalid operation. Please choose +, -, *, or /.");
                        continue;
                }

                System.out.println("The result is: " + result);

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter numeric values.");
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

            // Ask if the user wants another calculation
            System.out.print("Do you want to perform another calculation? (yes/no): ");
            String again = scanner.nextLine().trim().toLowerCase();
            if (!again.equals("yes")) {
                System.out.println("Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}