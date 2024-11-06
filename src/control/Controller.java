/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import BSTree.MyQueue;
import BSTree.TreeNode;
import Model.Booking;
import Model.Room;
import Model.Student;
import MyLinkedList.MyLinkedList;
import MyLinkedList.Node;
import bo.BookingManager;
import bo.RoomManager;
import bo.StudentManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.InputValidation;

/**
 *
 * @author iamwu
 */
public class Controller {

    InputValidation inp = new InputValidation();
    RoomManager rm = new RoomManager();
    StudentManager sm = new StudentManager();
    BookingManager bm = new BookingManager();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    //1. Room Management
    //1.1 Load data from file
    public void readRoomFile() {
        try {
            rm.readFile("rooms.txt");
            System.out.println("Read succesfully!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //1.2 Input & add to the tree 
    public void inputAndAddNewRoom() {
        String rcode = inp.inputStringNoSpace("Room code: ");
        String name = inp.inputStringSpace("Room name: ");
        String dom = inp.inputStringSpace("Dom: ");
        String floor = inp.inputStringSpace("Floor: ");
        String type = inp.inputStringSpace("Room type (Double / Triple): ");
        //No need input for beds, booked,price when we add a new room

        Room newRoom = new Room(rcode, name, dom, floor, type);
        try {
            rm.addRoom(newRoom);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //1.3 Display data by in-order traversal
    public void displayRoomData() {
        TreeNode p = rm.getRoomTree().getRoot();
        System.out.println(String.format("%-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s|",
                "RCODE", "NAME", "DOM", "FLOOR", "TYPE", "BEDS", "BOOKED", "PRICE"));
        System.out.println("----------------------------------------------------------------------------------------------");

        // Sử dụng phương thức inOrder để in danh sách phòng theo thứ tự in-order
        inOrderDisplay(p);
        System.out.println();
    }

    private void inOrderDisplay(TreeNode p) {
        if (p == null) {
            return;
        }

        // Duyệt qua cây theo thứ tự in-order
        inOrderDisplay(rm.getRoomTree().getLeft(p));

        // In thông tin của Room hiện tại
        Room r = (Room) p.getInfo();
        System.out.printf("%-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s|\n",
                r.getRcode(), r.getName(), r.getDom(), r.getFloor(), r.getType(),
                r.getBeds(), r.getBooked(), r.getPrice());

        inOrderDisplay(rm.getRoomTree().getRight(p));
    }

    //1.4 Save room tree to file by post-order traversal
    public void saveRoomTreeToFilePost_order() {
        try {
            rm.saveToFile("temp.txt");
            System.out.println("Save successfully");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //1.5 Search by rcode
    public void searchRoomByRoomCode() {
        String rcode = inp.inputStringNoSpace("Room code want to search infor: ");
        Room needToBeFound = (Room) rm.searchByRoomCode(rcode);
        if (needToBeFound != null) {
            //tìm thấy thì in ra thông tin 
            System.out.println("CODE \t NAME \t DOM \t FLOOR \t TYPE \t\t BEDS \t BOOKED \t PRICE");
            System.out.println(needToBeFound.toScreen());
        } else {
            System.out.println("This room code does not exist");
        }
    }

    //1.6 Delete by rcode by copying 
    public void deleteRcodeByCopying() {
        String rcode = inp.inputStringNoSpace("Enter room code that you want to delete by copying: ");
        MyLinkedList currentBooking = bm.searchCurrentBookingByRoomCode(rcode);
        if (currentBooking.isEmpty()) {
            try {
                bm.deleteBookingByRoomCode(rcode);
                rm.deleteByCopying(rcode);
                System.out.println("Delete successfully!");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("This room is currently booked by students, cannot delete!");
        }
    }

    //1.7 Delete by rcode by merging 
    public void deleteRcodeByMerging() {
        String rcode = inp.inputStringNoSpace("Enter room code that you want to delete by merging: ");
        MyLinkedList currentBooking = bm.searchCurrentBookingByRoomCode(rcode);
        if (currentBooking.isEmpty()) {
            try {
                bm.deleteBookingByRoomCode(rcode);
                rm.deleteByMerging(rcode);
                System.out.println("Delete successfully!");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("This room is currently booked by students, cannot delete!");
        }
    }

    //1.8 Simply balancing 
    public void simpleBalance() {
        try {
            rm.simpleBalance();
            System.out.println("Balance tree successfully!!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //1.9 Display data by breadth-first traversal 
    public void displayRoomDataBFT() {
        TreeNode p = rm.getRoomTree().getRoot();
        System.out.println(String.format("%-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s|",
                "RCODE", "NAME", "DOM", "FLOOR", "TYPE", "BEDS", "BOOKED", "PRICE"));
        System.out.println("----------------------------------------------------------------------------------------------");

        breadth(p);
        System.out.println();
    }

    private void breadth(TreeNode root) {
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root.getInfo());

        TreeNode p;
        while (!q.isEmpty()) {
            Room tmp = (Room) q.dequeue();
            p = rm.getRoomTree().getNodeByRoomObject(tmp);
            if (rm.getRoomTree().getLeft(p) != null) {
                q.enqueue(rm.getRoomTree().getLeft(p).getInfo());
            }
            if (rm.getRoomTree().getRight(p) != null) {
                q.enqueue(rm.getRoomTree().getRight(p).getInfo());
            }

            Room r = (Room) p.getInfo();
            System.out.printf("%-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s|\n",
                    r.getRcode(), r.getName(), r.getDom(), r.getFloor(), r.getType(),
                    r.getBeds(), r.getBooked(), r.getPrice());

        }
        System.out.println("");
    }

    //1.10 Count the number of rooms
    public void countRooms() {
        System.out.println("Total number of rooms: " + rm.countRooms());
    }

    //1.11 Search by name 
    public void searchRoomByName() {
        String name = inp.inputStringNoSpace("Room name you want to search: ");
        MyLinkedList roomsWithSameName = rm.searchByroomName(name);  // Lấy danh sách các phòng trùng tên

        if (roomsWithSameName.getSize() > 0) {
            System.out.println("CODE \t NAME \t DOM \t FLOOR \t TYPE \t\t BEDS \t BOOKED \t PRICE");

            for (int i = 0; i < roomsWithSameName.getSize(); i++) {
                Room room = (Room) roomsWithSameName.getByIndex(i).getInfo();  // Lấy từng phòng ra từ danh sách
                if (room != null) {
                    System.out.println(room.toScreen());  // In thông tin phòng
                }
            }
        } else {
            System.out.println("No rooms found with the given name");
        }
    }

    //1.12 Search booked by rcode 
    public void searchBookedByRoomCode() {
        String rcode = inp.inputStringNoSpace("Enter room code to search: ");

        Room room = (Room) rm.searchByRoomCode(rcode);
        if (room == null) {
            System.out.println("Room with code " + rcode + " does not exist.");
            return;
        }
        System.out.println("Room found: " + room.toString());

        MyLinkedList booking = bm.searchCurrentBookingByRoomCode(rcode);

        if (booking.isEmpty()) {
            System.out.println("There was no student in that room!");
        } else {
            System.out.println("Student info in that room: ");
            Node p = booking.getFirst();
            Booking tmp;
            while (p != null) {
                tmp = (Booking) p.getInfo();
                //function 2.5
                Student s = sm.searchByStudentCode(tmp.getScode());
                System.out.println(s.toString());
                p = booking.getNext(p);
            }
        }
    }

    
    
    
    
    //2. StudentManagement
    //2.1 Load data from file
    public void readStudentFile() {
        try {
            sm.readFile("students.txt");
            System.out.println("Read succesfully!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //2.2 Input & add to the tree
    public void addNewStudent() {
        try {
            String scode = inp.inputStringNoSpace("Student code: ");
            String name = inp.inputStringSpace("Student name: ");
            int byear = inp.inputInt("Birth year: ", 1900, 2023);  // Validate birth year

            // Creating a new student object after validation
            Student newStudent = new Student(scode, name, byear);
            sm.addStudent(newStudent);  // Add the validated student to the tree
            System.out.println("Student added successfully.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //2.3 Display data by pre-order traversal 
    public void displayStudentData() {
        TreeNode p = sm.getStudentTree().getRoot();
        System.out.println(String.format("%-10s| %-20s| %-6s", "SCODE", "STUDENT NAME", "BIRTH YEAR"));
        System.out.println("-----------------------------------------------");

        // Sử dụng phương thức inOrder để in danh sách phòng theo thứ tự in-order
        preOrderDisplay(p);
        System.out.println();
    }

    private void preOrderDisplay(TreeNode p) {
        if (p == null) {
            return;
        }
        // In thông tin của Room hiện tại
        Student s = (Student) p.getInfo();  // Get student info from the current node

        System.out.printf(String.format("%-10s| %-20s| %-6d\n",
                s.getScode(), // Student code
                s.getName(), // Student name
                s.getByear() // Birth year
        ));

        // Duyệt qua cây theo thứ tự in-order
        preOrderDisplay(sm.getStudentTree().getLeft(p));

        preOrderDisplay(sm.getStudentTree().getRight(p));
    }

    //2.4 Save student tree to file by in-order traversal
    public void saveStdTreeToFileIn_order() {
        try {
            sm.saveToFile("temp.txt");
            System.out.println("Save successfully");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //2.5 Search by scode
    public void searchStudentByScode() {
        String scode = inp.inputStringNoSpace("Enter student code to search: ");

        // find student by scode
        Student foundStudent = sm.searchByStudentCode(scode);

        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent.toString());
        } else {
            System.out.println("Student with code " + scode + " not found.");
        }
    }

    //2.6 Delete by scode by copying
    public void deleteScodeByCopying() {
        String scode = inp.inputStringNoSpace("Enter student code that you want to delete by copying: ");
        if (!bm.isStdCurrentBooking(scode)) {
            try {
                bm.deleteBookingsByScode(scode);
                sm.deleteByCopying(scode);
                System.out.println("Delete successfully!");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("This student is currently booking a room, cannot delete!");
        }
    }

    //2.7 Search by name 
    public void searchStudentByName() {
        String name = inp.inputStringSpace("Enter student name to search: ");
        MyLinkedList result = sm.searchByStudentName(name);

        if (result.isEmpty()) {
            System.out.println("No students found with name: " + name);
        } else {
            Node current = result.getFirst();
            System.out.println("Students found:");
            while (current != null) {
                Student student = (Student) current.getInfo();
                System.out.println(student.toString());
                current = result.getNext(current);
            }
        }
    }

    //2.8 Search booking room by scode 
    public void getBookingRoomByScode() {
        String scode = inp.inputStringNoSpace("Enter student code that you want to search: ");
        Student s = (Student) sm.getStudentTree().search(scode);
        if (s != null) {
            System.out.println(s.toString());
            MyLinkedList booking = bm.searchAllBookingByStdCode(scode);

            if (booking.isEmpty()) {
                System.out.println("This student did not book any room");
            } else {
                displayBookings(booking);
            }
        } else {
            System.out.println("This student code doesn't exist!");
        }

    }

    
    
    
    
    //3. Room Management
    //3.1 Load data from file
    public void readFileBook() {
        try {
            bm.readFile("bookings.txt");
            System.out.println("Read succesfully!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //3.2 Book the room
    public void bookTheRoom() {
        String rcode = inp.inputStringNoSpace("Enter room code: ");
        String scode = inp.inputStringNoSpace("Enter student code: ");

        Room r = rm.searchByRoomCode(rcode);
        if (sm.searchByStudentCode(scode) != null && r != null) {
            if (r.getBeds() > 0) {
                Date bdate = new Date();
                try {
                    bm.bookRoom(new Booking(rcode, scode, bdate, null, 1));
                    r.setBooked(r.getBooked() + 1);
                    System.out.println("Booking successfully!");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("This room is already full!");
            }
        } else {
            System.out.println("This student code/ room code does not exist");
        }
    }

    //3.3 Display data
    public void displayBookings(MyLinkedList bookings) {
        Node current = bookings.getFirst(); // Get the first node of the booking list
        Booking b;
        String ldate;

        // Header for display
        System.out.println(String.format("%-10s| %-10s| %-20s| %-20s| %-6s", "ROOM CODE", "STUDENT CODE", "BOOKING DATE", "LEAVE DATE", "STATUS"));
        System.out.println("-----------------------------------------------------------------------------------");

        // Traverse through the booking list
        while (current != null) {
            b = (Booking) current.getInfo(); // Cast the TreeNode's info to a Booking object
            try {
                ldate = formatter.format(b.getLdate());
            } catch (Exception e) {
                ldate = "null";
            }

            // Print booking details using the existing toScreen method or format it directly
            System.out.printf(String.format("%-10s| %-13s| %-20s| %-20s| %-6d\n",
                    b.getRcode(), // Room code
                    b.getScode(), // Student code
                    formatter.format(b.getBdate()),
                    ldate,
                    b.getState() // Status (state)
            ));

            current = bm.getBookingList().getNext(current); // Move to the next booking
        }
        System.out.println(); // Blank line after the display
    }

    public void displayAllBookings() {
        displayBookings(bm.getBookingList());
    }

    //3.4 Save booking list to file
    public void writeFileBook() {
        try {
            bm.saveToFile("temp.txt");
            System.out.println("Save successfully!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //3.5 Sort by rcode + scode
    public void descendingSortByRcodeAndscodeBook() {
        try {
            bm.sortByRcodeAndScode();
            System.out.println("Sorted by rcode and scode in descending order.");
            displayAllBookings();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //3.6 Leave the room by rcode + scode
    public void leaveRoom() {
        String rcode = inp.inputStringNoSpace("Enter room code: ");
        String scode = inp.inputStringNoSpace("Enter student code: ");

        Room r = rm.searchByRoomCode(rcode);
        if (bm.leaveRoom(rcode, scode)) {
            try {
                r.setBooked(r.getBooked() - 1);
                System.out.println("Leave room successfully");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Invalid leaving room, or this student has been already leves the room");
        }
    }

}
