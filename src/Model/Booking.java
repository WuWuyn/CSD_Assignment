/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author iamwu
 */

public class Booking{
   
    private String rcode;  
    private String scode;  
    private Date bdate;    
    private Date ldate;    
    private int state;     

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    public Booking(String rcode, String scode, Date bdate, Date ldate, int state) {
        this.rcode = rcode;
        this.scode = scode;
        this.bdate = bdate;
        if(ldate == null) this.ldate = null;
        else this.ldate = ldate;
        this.state = state;
    }

    @Override
    public String toString() {
        String ld = "null";
        if(ldate != null) ld = formatter.format(ldate);
        return rcode + "," + scode + "," + formatter.format(bdate) + "," + ld + "," + state;
    }

    
    
    // Getters
    public String getRcode() {
        return rcode;
    }

    public String getScode() {
        return scode;
    }

    public int getState() {
        return state;
    }

    public Date getBdate() {
        return bdate;
    }

    public Date getLdate() {
        if(ldate == null) return null;
        return ldate;
    }

    public void setLdate(Date ldate) {
        this.ldate = ldate;
    }

    public void setState(int state) {
        this.state = state;
    }
}
