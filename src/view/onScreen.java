/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author iamwu
 */
public class onScreen {
    public static void menu(){
        System.out.println("1. About room\n"
                + "2. About student\n"
                + "3. About booking\n"
                + "4. Exit");
    }
    
    public static void viewRoomMenu(){
        System.out.println("1. Load data from file\n"
                + "2. Input & add to the tree\n"
                + "3. Display data by in-order traversal\n"
                + "4. Save room tree to file by post-order traversal\n"
                + "5. Search by rcode\n"
                + "6. Delete by rcode by copying\n"
                + "7. Delete by rcode by merging\n"
                + "8. Simply balancing\n"
                + "9. Display data by breadth-first traversal\n"
                + "10. Count the number of rooms\n"
                + "11. Search by name\n"
                + "12. Search booked by rcode\n"
                + "13. Back");
    }
    
    public static void viewStudentMenu(){
        System.out.println("1. Load data from file\n"
                + "2. Input & add to the tree\n"
                + "3. Display data by pre-order traversal\n"
                + "4. Save student tree to file by in-order traversal\n"
                + "5. Search by scode\n"
                + "6. Delete by scode by copying\n"
                + "7. Search by name\n"
                + "8. Search booking room by scode\n"
                + "9. Back");
    }
    
    public static void viewBookingMenu(){
        System.out.println("1. Load data from file\n"
                + "2. Book the room\n"
                + "3. Display data\n"
                + "4. Save booking list to file\n"
                + "5. Sort by rcode + scode\n"
                + "6. Leave the room by rocode + scode\n"
                + "7. Back");
    }
}
