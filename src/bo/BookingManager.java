/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import Model.Booking;
import MyLinkedList.MyLinkedList;
import MyLinkedList.Node;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Maxim
 */
public class BookingManager {

    private MyLinkedList bookingList;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public BookingManager() {
        this.bookingList = new MyLinkedList(); // get available roomlist
    }

    public MyLinkedList getBookingList() {
        return bookingList;
    }

    public MyLinkedList searchAllBookingByStdCode(String scode) {
        MyLinkedList result = new MyLinkedList();
        if (bookingList.isEmpty()) {
            return result;
        }

        Booking b;
        Node p = bookingList.getFirst();

        while (p != null) {
            b = (Booking) p.getInfo();
            if (b.getScode().equals(scode)) {
                result.addLast(b);
            }
            p = bookingList.getNext(p);
        }

        return result;
    }

    public MyLinkedList searchCurrentBookingByRoomCode(String roomCode) {
        MyLinkedList result = new MyLinkedList();
        if (bookingList.isEmpty()) {
            return result;
        }

        Node p = bookingList.getFirst();
        Booking b;

        while (p != null) {
            b = (Booking) p.getInfo();
            if (b.getRcode().equals(roomCode) && b.getState() == 1) {
                result.addLast(b);
            }
            p = bookingList.getNext(p);
        }

        return result;
    }

    public void deleteBookingsByScode(String scode) {
        Node p = bookingList.getFirst();
        while (p != null) {
            Booking b = (Booking) p.getInfo();
            if (b.getScode().equals(scode)) {
                bookingList.remove(b);
            }
            p = bookingList.getNext(p);
        }
    }

    public void deleteBookingByRoomCode(String rcode) throws Exception {
        Node p = bookingList.getFirst();
        while (p != null) {
            Booking b = (Booking) p.getInfo();
            if (b.getRcode().equals(rcode)) {
                bookingList.remove(b);
            }
            p = bookingList.getNext(p);
        }
    }

    //1. Load data from file
    public void readFile(String filepath) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(filepath));
        String line = bf.readLine();
        String[] info;
        Booking booking = null;
        Date ldate, bdate;

        while (line != null) {
            try {
                info = line.split(",");
                String rcode = info[0];
                String scode = info[1];

                bdate = formatter.parse(info[2]);
                ldate = null;
                if (info[3].equalsIgnoreCase("null") || info[3].isEmpty()) {
                } else {
                    ldate = formatter.parse(info[3]);
                }
                int state = Integer.parseInt(info[4]);

                bookingList.addLast(new Booking(rcode, scode, bdate, ldate, state));
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
            line = bf.readLine();
        }
        bf.close();
    }

    //2. Book the room
    public void bookRoom(Booking b) throws Exception {
        if (isStdCurrentBooking(b.getScode())) {
            throw new Exception("Invalid booking, this student is still in a Dom!");
        } else {
            bookingList.addLast(b);
        }
    }

    public boolean isStdCurrentBooking(String scode) {
        Booking b;
        MyLinkedList result = searchAllBookingByStdCode(scode);
        Node p = result.getFirst();
        while (p != null) {
            b = (Booking) p.getInfo();
            if (b.getState() == 1) {
                return true;
            }
            p = bookingList.getNext(p);
        }
        return false;
    }

    //4. Save booking list to file
    public void saveToFile(String filepath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
        Node p = bookingList.getFirst();
        while (p != null) {
            bw.write(p.getInfo().toString());
            bw.newLine();
            p = bookingList.getNext(p);
        }
        bw.close();
    }

    //5. Sort by rcode + scode
    public void sortByRcodeAndScode() throws Exception {
        if (bookingList.isEmpty()) {
            throw new Exception("The list is empty, add more for sorting.");
        }
        if (bookingList.getFirst() == bookingList.getLast()) {
            throw new Exception("There is only one element.");
        }

        // Sắp xếp theo rcode giảm dần trước
        Node p, q;
        for (p = bookingList.getFirst(); p != null; p = bookingList.getNext(p)) {
            for (q = bookingList.getNext(p); q != null; q = bookingList.getNext(q)) {
                Booking bp = (Booking) p.getInfo();
                Booking bq = (Booking) q.getInfo();
                if (bp.getRcode().compareTo(bq.getRcode()) < 0) {
                    bookingList.swap(p, q);
                } // Nếu rcode bằng nhau, sắp xếp theo scode giảm dần
                else if (bp.getRcode().compareTo(bq.getRcode()) == 0 && bp.getScode().compareTo(bq.getScode()) < 0) {
                    bookingList.swap(p, q);
                }
            }
        }
    }

    //6. Leave room by rcode + scode
    public boolean leaveRoom(String rcode, String scode) {
        Node p = bookingList.getFirst();
        Booking b;

        while (p != null) {
            b = (Booking) p.getInfo();
            if (b.getRcode().equals(rcode) && b.getScode().equals(scode) && b.getState() == 1) {
                b.setState(0);
                b.setLdate(new Date());
                return true;
            }
            p = bookingList.getNext(p);
        }
        return false;
    }

}
