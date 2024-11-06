/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Scanner;

/**
 *
 * @author iamwu
 */
public class InputValidation {

    Scanner inp = new Scanner(System.in);

    //Su dung de check cac string type (Bao gom dau cach, nhung khong bao gom ky tu dac biet)
    public String inputStringSpace(String message) {
        while (true) {
            System.out.print(message);
            String input = inp.nextLine().trim();
            // Kiểm tra xem chuỗi có ký tự đặc biệt hay không
            if (input.matches("[a-zA-Z0-9\\s]+")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter only alphanumeric characters and spaces.");
            }
        }
    }

    public String inputStringNoSpace(String message) {// hàm inputString không có space
        while (true) {
            System.out.print(message);
            String input = inp.nextLine().trim();
            // Kiểm tra xem chuỗi có ký tự đặc biệt hay không
            if (input.matches("[a-zA-Z0-9]+")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter only alphanumeric characters and spaces.");
            }
        }
    }

    public int inputInt(String message, int a, int b) {

        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(inp.nextLine().trim());
                if (value >= a && value <= b) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a value between " + a + " and " + b + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    public double inputDouble(String message, double a, double b) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(inp.nextLine().trim());
                if (value >= a && value <= b) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a value between " + a + " and " + b + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
