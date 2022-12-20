<%@page import ="java.util.ArrayList"%>
<%@page import ="com.Accio.SearchResult"%>

<html>
    <head>
            <link rel="stylesheet" type="text/css" href="styles.css">

        </head>
    <body style="background-image: linear-gradient( 174.2deg,  rgba(255,244,228,1) 7.1%, rgba(240,246,238,1) 67.4% );">
    <div class="resultTable">
     <form action = "Search">
                        <input type = "text" name = "keyword">
                        <button type = "submit"> Search </button>
                    </form>
        <table border= 3>
            <tr>
                <td style"font-size=50">TITLE</td>
                <td style"font-size=50">LINK</td>
            </tr>
            <%
                ArrayList<SearchResult> results=(ArrayList<SearchResult>)request.getAttribute("results");
                for(SearchResult result:results){
            %>
            <tr>
                <td><%out.println(result.getPagetitle());%></td>
                 <td><a href="<%out.println(result.getPageslink());%>"><%out.println(result.getPageslink());%></a></td>
            </tr>
                <%
                    }
                %>
        </table>
        </div>
    </body>
</html>