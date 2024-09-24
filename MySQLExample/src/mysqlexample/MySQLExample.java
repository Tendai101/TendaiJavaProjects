/* This is the application file for the MySQL example
*/

package mysqlexample;

import java.util.Date;


public class MySQLExample {

    public static void main(String[] args) {
        // creates a new object of class MySQLAccess
        MySQLAccess mydb = new MySQLAccess(); 
        // Inserts a record in the table "comments" from the database "feedback"
        mydb.doInsert("John", "john.cooper@ibm.com", "www.ibm.com", new Date(), "Summary 1", "My last comment");
        // retrieves the content of the table "comments"
        mydb.doSelect();
        // retrieves the meta data i.e. name of the table fields
        mydb.writeMetaData();
        // prints the content of the table "comments" 
        mydb.writeResultSet();

        // updates the table "comments"
        mydb.doUpdate();
        // retrieves the content of the table "comments"
        mydb.doSelect();
        // retrieves the meta data i.e. name of the table fields
        mydb.writeMetaData();
        // prints the content of the table "comments" 
        mydb.writeResultSet();
        
        // deletes records from the table "comments"
        mydb.doDelete();
        // retrieves the content of the table "comments"
        mydb.doSelect();
        // retrieves the meta data i.e. name of the table fields
        mydb.writeMetaData();
        // prints the content of the table "comments"
        mydb.writeResultSet();
        // closes the connection to the database
        mydb.close();
    }
}