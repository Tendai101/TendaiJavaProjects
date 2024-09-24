package assignment3;

/**
 *
 * @author Tendai Chaka
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class assignment3 {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // no-parameter constructor
    public assignment3() {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Setup the connection with the DB (feedback)
            connect = DriverManager.getConnection("jdbc:mysql://localhost/tendai", "tendizzy", "tendizzy");
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
        // Insters a record into the table using a formatted structure 
    public void InsertRecord(String name, String surname,int age, String eircode ,Date date ) {     
        try {
            preparedStatement = connect.prepareStatement("INSERT INTO details VALUES (default, ?,?,?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, eircode);
            preparedStatement.setTimestamp(5, new Timestamp(date.getTime()));
            preparedStatement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
     //deletes record specified by a name 
    public void deleteRecord(String name) {
        try {
            preparedStatement = connect.prepareStatement("DELETE FROM details WHERE name=(?)");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
        public void printRecords(){
        String s = "";
        try {
            
            resultSet = statement.executeQuery("SELECT * FROM details");
            
            System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                s = s + String.format("%25s ", resultSet.getMetaData().getColumnName(i));
            }
            System.out.println(s);
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
            
             while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String eircode = resultSet.getString("eircode");
                Timestamp tm = resultSet.getTimestamp("date");
                System.out.printf("%25d %25s %25s %25d %25s %25s\n", id, name, surname, age, eircode,tm.toString());
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
     }
    
    // Closes connection to the database
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}