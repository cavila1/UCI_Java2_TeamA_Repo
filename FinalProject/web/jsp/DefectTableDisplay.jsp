<%-- 
    Document   : DefectTableDisplay
    Created on : Mar 12, 2015, 7:55:21 PM
    Author     : angc
--%>
<%@page import = "java.util.List"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "java.util.List"%>
<%@page import = "com.uci.java.teama.User"%>
<%@page import = "com.uci.java.teama.DBIO"%>
<%@page import = "com.uci.java.teama.Defect"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <head>
        <link rel="stylesheet" href="css/maincss.css" type="text/css"/>
        <title>Admin Page</title>
    </head>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Originator</th>
            <th>Description</th>
            <th>Assignee</th>
            <th>Case Status</th>
            <th>Summary</th>
            <th>Priority</th>
        </tr>
        <%
            ArrayList<Defect> defectDisplay = new ArrayList<Defect>();
            //ArrayList<Defect> defectDisplay = (ArrayList<Defect>)request.getAttribute("theDefects");
            defectDisplay = (ArrayList<Defect>)session.getAttribute("theDefects");
            for (Defect myDefect : defectDisplay) {
                out.println("<tr>");
                    out.println("<td>");
                        out.println(String.valueOf(myDefect.getId()));
                    out.println("</td>");
                    out.println("<td>");
                        out.println(String.valueOf(myDefect.getOriginator()));
                    out.println("</td>");
                    out.println("<td>");
                        out.println(String.valueOf(myDefect.getDescription()));
                    out.println("</td>");
                    out.println("<td>");
                        out.println(String.valueOf(myDefect.getAssignee()));
                    out.println("</td>");
                    out.println("<td>");
                        out.println(String.valueOf(myDefect.getStatus()));
                    out.println("</td>");
                    out.println("<td>");
                        out.println(String.valueOf(myDefect.getSummary()));
                    out.println("</td>");
                    out.println("<td>");
                        out.println(String.valueOf(myDefect.getPriority()));
                    out.println("</td>");
                out.println("</tr>");
            }
        %>
    </table>
