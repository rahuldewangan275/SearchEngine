package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// data base connection to java-> use singlton class
// we will be using jdbc(java database connectivity) libray(my sql connector) to connect database to java
public class DataBaseConnection {
    static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        String user = "root";
        String pwd = "Rahul@0269"; // password of mysql
        String db = "searchengineapp"; // my data base
        return (getConnection(user, pwd, db));
    }

    private static Connection getConnection(String user, String pwd, String db) { // db = database
        try {
            //we load oracle drivers class file into memory at the runtime
            Class.forName("com.mysql.cj.jdbc.Driver");
            // using Connection class Object
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db + "?user=" + user + "&password=" + pwd);
        }
        catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.getStackTrace();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }
}
