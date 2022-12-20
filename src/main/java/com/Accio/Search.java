package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        try{
            Connection connection = DatabaseConnection.getConnection();
            // add keyword into histoey table
            PreparedStatement preparedStatement=connection.prepareStatement("Insert into history values(?,?)");
            preparedStatement.setString(1,keyword);
            preparedStatement.setString(2,"http://localhost:8080/AccioSearchEngine/Search?="+keyword);
            preparedStatement.executeUpdate();

            //get results form pages table
            ResultSet resultSet = connection.createStatement().executeQuery("select pagetitle,pageslink,(length(pagesdata)-length(replace(pagesdata,\"keyword\",\"\")))/length(\"keyword\")as countoccurences from pages order by countoccurences desc;");
            ArrayList<SearchResult> results = new ArrayList<>();
            while (resultSet.next()){

                com.Accio.SearchResult searchResult=new com.Accio.SearchResult();
                searchResult.setPagetitle(resultSet.getString("pagetitle"));
                searchResult.setPageslink(resultSet.getString("pageslink"));
                results.add(searchResult);
            }
            for(com.Accio.SearchResult result:results){
                System.out.println(result.getPageslink()+" "+result.getPagetitle()+"\n");
            }
            request.setAttribute("results", results);
            request.getRequestDispatcher("/search.jsp").forward(request, response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }

    }
}
