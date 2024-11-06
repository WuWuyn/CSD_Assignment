/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import util.InputValidation;
import view.nevigator;
import view.onScreen;

/**
 *
 * @author iamwu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        InputValidation inp = new InputValidation();
        nevigator ne = new nevigator();
        
        int tmp,choice;
        boolean flag = true;
        while(flag){
            tmp = -1;
            onScreen.menu();
            choice = inp.inputInt("Enter your choice (1-4): ", 1, 4);
            switch(choice){
                case 1: 
                    while(tmp != 13){
                        view.onScreen.viewRoomMenu();
                        tmp = inp.inputInt("Enter your choice (1-13): ", 1, 13);
                        ne.nevigateRoom(tmp);
                    }
                    break;
                case 2:
                    while(tmp != 9){
                        view.onScreen.viewStudentMenu();
                        tmp = inp.inputInt("Enter your choice (1-9): ", 1, 9);
                        ne.nevigateStudent(tmp);
                    }
                    break;
                case 3:
                    while(tmp != 7){
                        view.onScreen.viewBookingMenu();
                        tmp = inp.inputInt("Enter your choice (1-7): ", 1, 7);
                        ne.nevigateBooking(tmp);
                    }
                    break;
                case 4:
                    flag=  false;
                    break;
            }
        }
    }
    
}
