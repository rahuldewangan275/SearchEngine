package com.Accio;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
protected  void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
    //Getting keyword from Front-End
    String keyword = request.getParameter("keyword");
    // Setting up connection to database
    Connection connection = DataBaseConnection.getConnection();
    try {
        // Store the query of user(adding in History )
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?,?);");
        preparedStatement.setString(1,keyword);
        preparedStatement.setString(2,"http://localhost:8080/SearchEngine/Search?keyword="+keyword);
        preparedStatement.executeUpdate();



         // Getting results after running the ranking query (recieved or store in ResultSet)
        // data base return the response of query to backend in a special object which is ResultSet
        ResultSet resultSet = connection.createStatement().executeQuery("select pageTitle, pageLink, (length(pageText)-length(replace(lower(pageText),'" + keyword.toLowerCase() + "','')))/length('" + keyword.toLowerCase() + "') as countOccurence from pages2 order by countOccurence desc limit 30");
        ArrayList<SearchResult>  results = new ArrayList<>();
        // Transferring values from resultSet to result ArrayList
        while(resultSet.next()){
            SearchResult searchResult = new SearchResult();
            searchResult.setTitle(resultSet.getString("pageTitle"));
            searchResult.setLink(resultSet.getString("pageLink"));
            results.add(searchResult);
        }

        // displaying results arrayList in console
        for(SearchResult result : results){
            System.out.println(result.getTitle()+ "\n" + result.getLink());
        }

        // string , object
        request.setAttribute("results" , results);
        request.getRequestDispatcher("search.jsp").forward(request , response);


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//    out.println("<h3>this is the key word you have entered "+ keyword + "</h3>");
    }
    catch (SQLException sqlException){
        sqlException.printStackTrace();
    }
    catch(ServletException servletException){
        servletException.printStackTrace();
    }
}
}
