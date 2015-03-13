<%-- 
    Document   : index
    Created on : Feb 28, 2015, 1:55:06 PM
    Author     : kieky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Team A Defect Tracking System</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/maincss.css" type="text/css"/>
    </head>
    <body>
        <h1>Welcome to defect tracking system</h1>
        <div>
            <div style="width:400px; float:left;">
                <h1>Please log into system</h1>
                <p>If you have an account, please enter your email and password below.</p>
                <form action="../FinalProject/DefectTrackingServlet" method="POST">
                    <input type="hidden" name="action" value="login">
                    
                    <label>Email</label>
                    <input type="text" name="email" required><br>
                    
                    <label>Password</label>
                    <input type="password" name="password" required><br>
                    
                    <label>&nbsp;</label>
                    <input type="submit" value="Login" id="Login">
                </form>
            </div>                   
            <div style="width:400px; float:left;">
                <h1>Please sign into system</h1>
                <p>If you do not have an account, please sign up first.</p>
                <form action="../FinalProject/DefectTrackingServlet" method="POST">
                    <input type="hidden" name="action" value="signup">
                    
                    <label>Email</label>
                    <input type="text" name="newemail" required><br>
                    
                    <label>Password</label>
                    <input type="password" name="newpassword" required><br>
                    <label>Confirm Password</label>
                    <input type="password" name="newpassword2" required><br>
                    
                    <label>First Name</label>
                    <input type="text" name="newfirstname" required><br>
                    <label>Last Name</label>
                    <input type="text" name="newlastname" required><br>
                    
                    <label>Are you a customer</label>
                    <input type="checkbox" name="newcategory"><br>
                    
                    <label>&nbsp;</label>
                    <input type="submit" value="SignUp" id="Signup">
                </form>
            </div>
        </div>
        <%@include file="html/teama.html" %>
    </body>
</html>
