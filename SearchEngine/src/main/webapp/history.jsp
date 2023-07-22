<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Accio.HistoryResult"%>


<html>

<head>
<link rel = "stylesheet"  type = "text/css" href = "styles.css">
</head>

<body>
<h1>SIMPLE SEARCH ENGINE</h1>
<form action = "Search" >
<input type = "text" name = "keyword" class ="keyword" ></input>
<button type ="submit" class="search"> Search </button>
</form>
<form action = "History" >
<button type ="submit" class ="history"> History </button>
</form>

        <table border = 2  class= "resultTable"  >
            <tr>
                <th>Keyword</th>
                <th>Link</th>
            </tr>
                <%
                    ArrayList<HistoryResult> results = (ArrayList<HistoryResult>)request.getAttribute("results");
                    for(HistoryResult result : results){
                %>
            <tr>
                  <td><%out.println(result.getKeyword());%></td>
                  <td><a href = "<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>
            </tr>
            <%
              }
            %>
        </table>
</body>
</html>

