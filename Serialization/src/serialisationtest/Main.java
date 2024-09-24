/* Java application class
*/

package serialisationtest;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // creates a database
        PersonPersist p = new PersonPersist();
        // adds two people to the database
        p.addPerson("Conor", 45, "Male", "Conor@ass.com", "Conor_yah", "045 887 207");
        p.addPerson("Vanessa", 32, "Female", "Vanessa@ass.com", "Vaness_yeah", "045 777 237");
        // saves the database object in a file
        p.serialiseToFile();
        // retrieves object from a file
        GetPersonDetails g = new GetPersonDetails();
        // stores data in a List
        List myList = g.retrieveDetails();
        // prints the information for each person   
        System.out.printf("%10s  %3s  %6s  %6s %6s %6s\n","Name", "Age", "Gender", "Email", "Instagram", "Phone");
        System.out.printf("%30s\n","---------------------------------");   
        for (Object obj : myList) {
            // extracts an object from the array list
            PersonDetails pd = (PersonDetails) obj;
            // prints each person's details 
            System.out.printf("%10s  %3s  %6s  %6s %6s %6s\n",pd.getName(), pd.getAge(), pd.getGender(), pd.getEmail(), pd.getInstagram(), pd.getPhone());
        }
    }
}
