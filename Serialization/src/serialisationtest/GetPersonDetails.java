/* Reads personal data
   from a file

*/

package serialisationtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class GetPersonDetails {
    private List myDetails;

    // no-parameter constructor
    public GetPersonDetails() {
        // file for reading
        String filename = "person.txt";
        List pDetails = null;
        // file & object stream declarations
        FileInputStream fis;
        ObjectInputStream in;
        try {
            // openes file for reading
            fis = new FileInputStream(filename);
            // openes object input stream
            in = new ObjectInputStream(fis);
            // reads object from a file
            pDetails = (ArrayList) in.readObject();
            // stores the object in an instance variable
            myDetails = pDetails;
            // closes the file
            in.close();
        } catch (IOException ex) {
            // prints IO exception info
            ex.printStackTrace(); 
        } catch (ClassNotFoundException ex) {
            // prints ClassNotFound exception info
            ex.printStackTrace(); 
        }
        // print out the size
        System.out.println("Person Details Size: " + pDetails.size());
        System.out.println();
    }

    public List retrieveDetails() {
        // returns the persons data
        return myDetails;
    }
}