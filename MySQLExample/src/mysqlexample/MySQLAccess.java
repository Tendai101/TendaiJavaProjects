/* This is a MySQL database example
*/

package mysqlexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // no-parameter constructor
    public MySQLAccess() {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Setup the connection with the DB (feedback)
            connect = DriverManager.getConnection("jdbc:mysql://localhost/feedback", "sqluser", "sqluserpw");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // extract data from the table "comments" 
    public void doSelect() {
        try {
            resultSet = statement.executeQuery("SELECT * FROM comments");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Updates all table fields where the ID > 4
    public void doUpdate() {
        try {
            statement.executeUpdate("UPDATE comments SET summary='New summary' WHERE id > 1");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Delete from the table all records where myuser is "John" 
    public void doDelete() {
        try {
            statement.executeUpdate("DELETE FROM comments WHERE myuser='John'");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
    // Insters a record into the table using a formatted structure 
    public void doInsert(String myuser, String email, String webpage, Date date, String summary, String comments) {     
        try {
            preparedStatement = connect.prepareStatement("INSERT INTO comments VALUES (default, ?,?,?,?,?,?)");
            preparedStatement.setString(1, myuser);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, webpage);
            preparedStatement.setTimestamp(4, new Timestamp(date.getTime()));
            preparedStatement.setString(5, summary);
            preparedStatement.setString(6, comments);
            preparedStatement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }    

    // Displays the meta data (name of the fields/columns)
    public void writeMetaData() {
        String s = "";
        try {
            System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                s = s + String.format("%25s ", resultSet.getMetaData().getColumnName(i));
            }
            System.out.println(s);
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Prints a database table on the screen
    public void writeResultSet() {
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String user = resultSet.getString("myuser");
                String email = resultSet.getString("email");
                String website = resultSet.getString("webpage");
                Timestamp tm = resultSet.getTimestamp("date");
                String summary = resultSet.getString("summary");
                String comment = resultSet.getString("comments");
                System.out.printf("%25d %25s %25s %25s %25s %25s %25s\n", id, user, email, website, tm.toString(), summary, comment);
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