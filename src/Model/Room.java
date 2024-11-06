/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author iamwu
 */
public class Room extends Model{
    
    private String rcode;
    private String name;
    private String dom;
    private String floor;
    private String type;
    private int beds;
    private int booked;
    private double price;
    private int quantity;

    //Constructor for a new room
    public Room(String rcode, String name, String dom, String floor, String type) {
        if(rcode.trim() != "" && name.trim() != "" && dom.trim() != "" && floor.trim() != ""){
            this.rcode = rcode;
            this.name = name;
            this.dom = dom;
            this.floor = floor;
        }
        
        if(type.equalsIgnoreCase("double") || type.equalsIgnoreCase("triple")){
            this.type = type;
            this.beds = type.equalsIgnoreCase("double") ? 4 : 6;
            this.price = type.equalsIgnoreCase("double") ? 1040000 : 980000;
        }
        this.quantity = beds;
        this.booked = 0;
    }
    
    //Constructor for store information of existing room
    public Room(String rcode, String name, String dom, String floor, String type, int beds, int booked, double price) {
        if(rcode.trim() != "" && name.trim() != "" && dom.trim() != "" && floor.trim() != ""){
            this.rcode = rcode;
            this.name = name;
            this.dom = dom;
            this.floor = floor;
        }
        if(type.equalsIgnoreCase("double") || type.equalsIgnoreCase("triple")){
            this.type = type;
            this.quantity = type.equalsIgnoreCase("double") ? 4 : 6;
        }
        if(beds>=0 && beds<=quantity && beds == quantity - booked) this.beds = beds;
        if(booked>=0 && booked <= quantity ) this.booked = booked;
        if(price>=0) this.price = price;
    }
    

    @Override
    public String toString() {
        return rcode + "," + name + "," + dom + "," + floor + "," + type + "," + beds + "," + booked + "," + price;
    }
    
    public String toScreen(){
        return rcode + " \t " + name + " \t " + dom + " \t " + floor + " \t " + type + " \t " + beds + " \t " + booked + " \t\t" + price;
    }

    public String getRcode() {
        return rcode;
    }


    public String getName() {
        return name;
    }


    public String getDom() {
        return dom;
    }


    public String getFloor() {
        return floor;
    }


    public String getType() {
        return type;
    }


    public int getBeds() {
        return beds;
    }


    public int getBooked() {
        return booked;
    }

    //Moi quan he voi beds
    public void setBooked(int booked) throws Exception {
        if(booked<=quantity && booked >= 0){
            this.booked = booked;
            this.beds = quantity - booked;
        }
        else throw new Exception("This room is full!!!");
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getKey() {
        return rcode;
    }

    
}
