package org.example;
import java.sql.Connection;
import org.jsoup.nodes.Document;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexer {
    static Connection connection = null;
    //select important elements of document
    Indexer(Document document , String url){
        String title = document.title();
        String text = document.text();
        //save these elements to database
        try {
           connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into pages2 values (?, ?, ?);"); // dynamic

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, url);
            preparedStatement.setString(3, text);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
