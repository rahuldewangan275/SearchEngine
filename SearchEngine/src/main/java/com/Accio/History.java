package com.Accio;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/History")
public class History extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        Connection connection = DataBaseConnection.getConnection();

        try{
            // the result from data base return in form of ResultSet Object
            ResultSet resultSet = connection.createStatement().executeQuery("Select * from history;");
            // store in ArrayList
            ArrayList<HistoryResult> results = new ArrayList<>();
            while(resultSet.next()){
                HistoryResult historyResult = new HistoryResult();
                historyResult.setKeyword(resultSet.getString("keyword"));
                historyResult.setLink(resultSet.getString("link"));
                results.add(historyResult);
            }
          // forward this result arrayList to the front end
            request.setAttribute("results", results);
            // forwarding to history.jsp file
            request.getRequestDispatcher("history.jsp").forward(request,response);  // throw two exception servlet and IOE
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        catch(ServletException servletException){
            servletException.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
