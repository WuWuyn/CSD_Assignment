/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import control.Controller;
    
/**
 *
 * @author iamwu
 */
public class nevigator {
    
    Controller c = new Controller();
    
    
    public void nevigateRoom(int choice){
        switch(choice){
            case 1: 
                //Done
                c.readRoomFile();
                break;
            case 2:
                //Done
                c.inputAndAddNewRoom();
                break;
            case 3:
                //Done
                c.displayRoomData();
                break;
            case 4:
                //Done
                c.saveRoomTreeToFilePost_order();
                break;
            case 5:
                //Done
                c.searchRoomByRoomCode();
                break;
            case 6: 
                //Done
                c.deleteRcodeByCopying();
                break;
            case 7:
                //Done
                c.deleteRcodeByMerging();
                break;
            case 8:
                //Done
                c.simpleBalance();
                break;
            case 9:
                //Done
                c.displayRoomDataBFT();
                break;
            case 10:
                //Done
                c.countRooms();
                break;
            case 11:
                //Done
                c.searchRoomByName();
                break;
            case 12: 
                //Done
                c.searchBookedByRoomCode();
                break;
        }
    }
    
    public void nevigateStudent(int choice){
        switch(choice){
            case 1: 
                //Done
                c.readStudentFile();
                break;
            case 2:
                //Done
                c.addNewStudent();
                break;
            case 3:
                //Done
                c.displayStudentData();
                break;
            case 4:
                //Done
                c.saveStdTreeToFileIn_order();
                break;
            case 5:
                //Done
                c.searchStudentByScode();
                break;
            case 6: 
                //Done
                c.deleteScodeByCopying();
                break;
            case 7:
                //Done
                c.searchStudentByName();
                break;
            case 8:
                //Done
                c.getBookingRoomByScode();
                break;
        }
    }
    
    public void nevigateBooking(int choice){
        switch(choice){
            case 1: 
                //Done
                c.readFileBook();
                break;
            case 2:
                //Done
                c.bookTheRoom();
                break;
            case 3:
                //Done
                c.displayAllBookings();
                break;
            case 4:
                //Done
                c.writeFileBook();
                break;
            case 5:
                //Done
                c.descendingSortByRcodeAndscodeBook();
                break;
            case 6: 
                //Done
                c.leaveRoom();
                break;
        }
    }
}
