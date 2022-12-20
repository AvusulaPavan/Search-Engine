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
import java.util.Collections;

@WebServlet("/History")

public class History extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Connection connection = DatabaseConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from history;");
            ArrayList<HistoryResult> results = new ArrayList<HistoryResult>();
            while (resultSet.next()){
                HistoryResult historyResult=new HistoryResult();
                historyResult.setKeyword(resultSet.getString("keyword"));
                historyResult.setLink(resultSet.getString("searchlink"));
                results.add(historyResult);
            }
            request.setAttribute("results", results);
            request.getRequestDispatcher("/history.jsp").forward(request, response);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
       catch (ServletException servletException){
            servletException.printStackTrace();
       }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
