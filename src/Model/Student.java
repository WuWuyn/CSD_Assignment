/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author iamwu
 */
public class Student extends Model{
    String scode,name;
    int byear;
    
    public Student(String scode, String name, int byear) {
        LocalDate today = LocalDate.now();
        if(scode.trim()!="" && name.trim()!="" && (today.getYear()-byear>=18)){
        this.scode = scode;
        this.name = name;
        this.byear = byear;
        }
    }
    
    public String getScode() {
        return scode;
    }
    public String getName() {
        return name;
    }

    public int getByear() {
        return byear;
    }
    

    public void setName(String name) {
        this.name = name;
    }
    public void setByear(int byear) {
        LocalDate today = LocalDate.now();
        if((today.getYear()-byear)>=18){
            this.byear = byear;
        }
    }

    @Override
    public String toString() {
        return scode + "," + name + "," + byear;
    }

    @Override
    public String getKey() {
        return scode;
    }
}
