/* Java application class

 */

import java.util.ArrayList;
import java.util.List;

public class Main {

    List userDetails;

    public Main() {
        // creates an array list
        userDetails = new ArrayList();
        // first person
        PersonDetails person1 = new PersonDetails("Richard", -10, "Male");
        // second person
        PersonDetails person2 = new PersonDetails("Bob", 12, "Male");
        // third person
        PersonDetails person3 = new PersonDetails("Jane", 350, "Female");
        // adds the first person to the list
        userDetails.add(person1);
        // adds the second person to the list
        userDetails.add(person2);
        // adds the third person to the list
        userDetails.add(person3);
    }

    public void printData() {
        // prints the information for each person
        System.out.printf("%20s  %3s  %6s\n","Name", "Age", "Gender");
        System.out.printf("%30s\n","---------------------------------");        
        for (int i = 0; i < userDetails.size(); i++) {
            // extracts an object from the array list
            PersonDetails pd = (PersonDetails) userDetails.get(i);
            // prints each person's details 
            System.out.printf("%20s  %3d  %6s\n",pd.name, pd.age, pd.gender);
        }
    }

    public static void main(String[] args) {
        // creats an object
        Main obj = new Main();
        // prints entries in the database
        obj.printData();
    }
}
