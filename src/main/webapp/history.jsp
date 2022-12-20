<%@page import ="java.util.ArrayList"%>
<%@page import ="com.Accio.HistoryResult"%>

<html>
    <head>
            <link rel="stylesheet" type="text/css" href="styles.css">

        </head>
    <body style="background-image: radial-gradient( circle 344px at 1.4% 0%,  rgba(242,185,252,1) 19.3%, rgba(250,254,190,1) 80.7% );">
    <div class="resultTable">
     <form action = "Search">
                        <input type = "text" name = "keyword">
                        <button type = "submit"> Search </button>
                    </form>
        <table border= 3>
            <tr>
                <td style"font-size=50">KEYWORD</td>
                <td style="font-size=50">LINK</td>
            </tr>
            <%
                ArrayList<HistoryResult> results=(ArrayList<HistoryResult>)request.getAttribute("results");
                for(HistoryResult result:results){
            %>
            <tr>
                <td><%out.println(result.getKeyword());%></td>
                 <td><a href="<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>
            </tr>
                <%
                    }
                %>
        </table>
        </div>
    </body>
</html>