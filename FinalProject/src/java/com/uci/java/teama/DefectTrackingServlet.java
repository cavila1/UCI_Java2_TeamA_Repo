/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uci.java.teama;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author angc
 */
public class DefectTrackingServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "/index.jsp";
        
        // get current action
        String action = request.getParameter("action");
        if(action == null) {
            action = "welcome";
        }
        // real action
        if (action.equalsIgnoreCase("login")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            User myUser = new User();
            // set email address
            myUser.setEmailAddress(email);
            myUser.setPassword(password);
            // check if user is valid (i.e. in the database already
            DBIO myDBConnection = new DBIO(myUser);

            if (myDBConnection.isValidUser()) {
                if (myDBConnection.isCategory()) {
                    url = "/jsp/DefectTrackingPageAdmin.jsp";
                } else {
                    url = "/jsp/DefectTrackingPage.jsp";
                }
                
                myUser.setFirstName(myDBConnection.getFirstName());
                myUser.setLastName(myDBConnection.getLastName());
                request.setAttribute("aUser", myUser);
                request.setAttribute("theDefects", myDBConnection.viewListOfAllOpenDefects(true));
            } else {
                url = "/index.jsp";
                request.setAttribute("password_correct", "wrong");
            }
        } else if (action.equalsIgnoreCase("signup")) {
            String email        = request.getParameter("newemail");
            String password     = request.getParameter("newpassword");
            String password2    = request.getParameter("newpassword2");
            String firstName    = request.getParameter("newfirstname");
            String lastName     = request.getParameter("newlastname");
            boolean category    = false;
            
            if(request.getParameter("newcategory") == null) {
                category = false;
            } else if(request.getParameter("newcategory").equalsIgnoreCase("on")) {
                category = true;
            }

            if (password.equals(password2)) {
                // create new user object
                User myUser = new User(firstName, lastName, email, password, category);
                // create new DB object using User class
                DBIO myDBConnection = new DBIO(myUser);
                // add user to the user database (little risky if DB not found or cannot add user)
                myDBConnection.addUser();

                if (category) {
                    url = "/jsp/DefectTrackingPageAdmin.jsp";
                } else {
                    url = "jsp/DefectTrackingPage.jsp";
                }

                request.setAttribute("aUser", myUser);
            } else {
                url = "/index.jsp";
                request.setAttribute("password_correct", "wrong");
            }
        } else if(action.equalsIgnoreCase("newcase")) {
//            String description = request.getParameter("newdescription");
//            String originator  = (String)request.getSession().getAttribute("originator");
//            
//            Defect myDefect = new Defect();
//            myDefect.setDescription(description);
//            myDefect.setOriginator(originator);
//            
//            User oneUser = (User)request.getSession().getAttribute("originator");
//            
//            Defect oneDefect = new Defect(oneUser.getEmail(),description,"","open","",0);
//            DefectDB defectDB = new DefectDB("root","password","jdbc:mysql://localhost:3306/defecttrackingdb");
//            
//            boolean dbOK = defectDB.insert(oneDefect);
//            
//            request.setAttribute("user", oneUser);
//                
//            if(dbOK == true) {
//                request.setAttribute("newcaseok", "ok");
//                if(oneUser.getCategory() == true) {
//                    url = "/jsp/DefectTrackingPageAdmin.jsp";
//                } else {
//                    url = "/jsp/DefectTrackingPage.jsp";
//                }
//            } else {
//                request.setAttribute("newcaseok", "notok");
//                if(oneUser.getCategory() == true) {
//                    url = "/jsp/DefectTrackingPageAdmin.jsp";
//                } else {
//                    url = "/jsp/DefectTrackingPage.jsp";
//                }
//            }
        } else if(action.equalsIgnoreCase("updatecase")) {
            url = "/jsp/DefectTrackingPageAdmin.jsp";

            Defect myDefect = new Defect();
            
            String defectID     = request.getParameter("retrievecaseid");
            String originator   = request.getParameter("updateoriginator");
            String assignee     = request.getParameter("updateassignee");
            String description  = request.getParameter("updatedescription");
            String summary      = request.getParameter("updatesummary");
            String status       = request.getParameter("updatestatus");
            String priority     = request.getParameter("updatepriority");
            
            myDefect.setOriginator(originator);
            myDefect.setAssignee(assignee);
            myDefect.setDescription(description);
            myDefect.setPriority(Integer.parseInt(priority));
            myDefect.setStatus(status);
            myDefect.setSummary(summary);

            DBIO myDBConnection = new DBIO(myDefect);
            myDBConnection.updateDefect();
            
            request.setAttribute("defect", myDefect);
            request.setAttribute("defectID", defectID);
            
            User myUser = (User)request.getSession().getAttribute("originator");
            request.setAttribute("aUser", myUser);
            request.setAttribute("theDefects", myDBConnection.viewListOfAllOpenDefects(true));
        } else if(action.equalsIgnoreCase("retrievecase")) {
//            url = "/jsp/DefectTrackingPageAdmin.jsp";
//            
//            String defectID = request.getParameter("retrievecaseid");
//            Defect oneDefect = new Defect();
//            DefectDB defectDB = new DefectDB("root","password","jdbc:mysql://localhost:3306/defecttrackingdb");
//            oneDefect = defectDB.getDefect(Integer.parseInt(defectID));
//            request.setAttribute("defect", oneDefect);
//            request.setAttribute("defectID", defectID);
//            
//            User oneUser = (User)request.getSession().getAttribute("originator");
//            request.setAttribute("user", oneUser);
        }
        //forward request and response
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
