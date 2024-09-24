/* Stores personal information 
   in a file
*/

package serialisationtest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonPersist {
    
    private List userDetails;
    private String filename;

    // no-parameter constructor
    public PersonPersist() {
        // output filename
        filename = "person.txt";
        // creates a database 
        userDetails = new ArrayList();
        PersonDetails person1 = new PersonDetails("Richard", 10, "Male", "Dennis@ass.com", "Richard_boy", "045 567 897");
        PersonDetails person2 = new PersonDetails("Bob", 12, "Male", "Bob@ass.com", "Bobby_boy", "045 345 837");
        PersonDetails person3 = new PersonDetails("Jane", 10, "Female", "Jane@ass.com", "Jane_girl", "045 555 797");
        userDetails.add(person1);
        userDetails.add(person2);
        userDetails.add(person3);
    }

    // adding another person to the database
    public void addPerson(String name, int age, String gender, String email, String instagram, String phone) {
        // adds another perosn to the database
        PersonDetails p = new PersonDetails(name, age, gender, email, instagram, phone);
        userDetails.add(p);
    }
    
    // save the database in a file
    public boolean serialiseToFile() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            // opens a file stream
            fos = new FileOutputStream(filename);
            // opens an object stream for writing
            out = new ObjectOutputStream(fos);
            // writes an object to a file
            out.writeObject(userDetails);
            // closes the output stream
            out.close();
            System.out.println("Object Persisted");
            return true;
        } 
        catch (IOException ex) {
            // prints exception information
            ex.printStackTrace();
            return false;
        }
    }
}