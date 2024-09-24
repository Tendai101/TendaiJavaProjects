/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment3;

import java.util.Date;

/**
 *
 * @author Tendai Chaka
 */
public class Assignment3Example {
     public static void main(String[] args) {
        // creates a new object of class assignment3
        assignment3 mydb = new assignment3(); 
        mydb.InsertRecord("Brendon", "Yessir", 75, "V94P86R", new Date());
        mydb.InsertRecord("Ryan", "Bob", 45, "V94GY78", new Date());
        mydb.printRecords();
        mydb.deleteRecord("Brendon");
        mydb.printRecords();   
        mydb.close();
    }
    
}