package com.uci.java.teama;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBIO {
    private static final String CONNECTION_URL      = "jdbc:mysql://localhost:3306/defecttrackingdb";
    private static final String CONNECTION_USER     = "root";
    private static final String CONNECTION_PASSWORD = "password";
    private static final String USER_TABLE          = "user";
    private static final String DEFECT_TABLE        = "defect";

    private String  firstName;
    private String  lastName;
    private String  emailAddress;
    private String  password;
    private boolean category; 

    private int     id;                             // unique index assigned to the defect
    private String  originator,                     // email address of the originator of the defect
                    status,                         // status of the defect/bug
                    assignee,                       // name of person assigned to resolve defect/bug
                    summary,                        // summary of defect/bug
                    description;                    // detailed description of defect/bug
    private int     priority;                       // priority number given to the defect/bug
                                                    // 0 = unassigned
                                                    // 1 = highest priority;  10 = lowest priority

    // Constructor for user object
    public DBIO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.emailAddress = user.getEmailAddress();
        this.password = user.getPassword();
        this.category = user.isCategory();
    }

    // Constructor for defect object
    public DBIO(Defect defect) {
        this.id = defect.getId();
        this.originator = defect.getOriginator();
        this.status = defect.getStatus();
        this.assignee = defect.getAssignee();
        this.summary = defect.getSummary();
        this.description = defect.getDescription();
        this.priority = defect.getPriority();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
    }
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
	
    public void updateDefect() {
        // Update originator, description, assignee, status, summary, priority
        Connection connection   = null;
        Statement statement     = null;
        ResultSet result        = null;

        System.out.println("Updating the defect table with new defect...");
        System.out.println("ID: " + this.getId());
        System.out.println("Originator: " + this.getOriginator());
        System.out.println("Description: " + this.getDescription());
        System.out.println("Assignee: " + this.getAssignee());
        System.out.println("Status: " + this.getStatus());
        System.out.println("Summary: " + this.getSummary());
        System.out.println("Priority: " + this.getPriority());

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
            statement = connection.createStatement();

            String sqlStatement = null;

            if (this.getId() != 0) {
                // Update defect VALUES (originator, description, assignee, status, summary, priority);
                sqlStatement = "UPDATE defect SET originator = \'" + this.getOriginator() + "\'"
                    + "SET description = \'" + this.getDescription() + "\'"
                    + "SET assignee = \'" + this.getAssignee() + "\'"
                    + "SET status = \'" + this.getStatus() + "\'"
                    + "SET summary = \'" + this.getSummary() + "\'"
                    + "SET priority = \'" + this.getPriority() + "\'"
                    + "WHERE ID = " + this.getId();
                statement.executeUpdate(sqlStatement);	
            } else {
                // INSERT INTO defect VALUES (originator, description, assignee, status, summary, priority);
                sqlStatement = "INSERT INTO " + DEFECT_TABLE + " VALUES (0,\'"
                    + originator + "\', \'" + description + "\', \'" 
                    + assignee + "\', \'" + status + "\', \'" + summary + "\', \'" + priority + "\');";
                System.out.println("\nInserting a defect into the defect table...");
                // DEBUG ONLY: System.out.println(" " + sqlStatement);
                statement.executeUpdate(sqlStatement);	
            }
            // MySQL - Close the Statement and Connection
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        } 
    }

    public void emailStatus() {
        User u = new User();
		
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
		
        System.out.println("\nLookup the user's information...");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM " + USER_TABLE);

            while (result.next()) {
                // email, password, category, first_name, last_name
                if ( u.getEmailAddress().equals(result.getString("email")) ) {
                    u.setFirstName(result.getString("first_name"));
                    u.setLastName(result.getString("last_name"));
                    break;
                }
            }
            // MySQL - Close the Statement and Connection
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
	// Generate the email		
        StringBuffer emailBody = new StringBuffer();
		
        emailBody.append(u.getFirstName() + " " + u.getLastName() + "\n\n");
        emailBody.append("Please be informed that we are processing you working on your defect request.");
        emailBody.append("\nDescription:\n " + this.getDescription());
        emailBody.append("\nIt has been assigned to:\n " + this.getAssignee());
        emailBody.append("\nThe current status is:\n " + this.getStatus());
        emailBody.append("\nSummary:\n " + this.getSummary());
        emailBody.append("\nPriority:\n " + this.getPriority());
        emailBody.append("\n\n\nSincerely the A-Team");

        new Email().sendEmail("A-Team@defectprocessing.com", this.getOriginator(), this.description, emailBody.toString());
    }

    public List<Defect> viewListOfAllOpenDefects(boolean openOnly) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        int id = 0;
        String originator = null;
        String description = null;
        String assignee = null;
        String case_status = null;
        String summary = null;
        int priority = 0;

        List<Defect> openDefects = new ArrayList<Defect>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
            statement = connection.createStatement();

            if (openOnly) {
                System.out.println("SELECT * FROM " + DEFECT_TABLE + " WHERE case_status = \'open\';");
            } else {
                System.out.println("SELECT * FROM " + DEFECT_TABLE);
            }
            statement.execute("USE defecttrackingdb;");
            result = statement.executeQuery("SELECT * FROM " + DEFECT_TABLE + " WHERE case_status = \'open\'");

            if (openOnly) {
                while (result.next()) {
                    if (result.getString("case_status").equals("open")) {
                        id = result.getInt("ID");
                        originator = result.getString("originator");
                        description = result.getString("description");
                        assignee = result.getString("assignee");
                        case_status = result.getString("summary");
                        priority = result.getInt("priority");
                        // Add open defect to List of Defects
                        openDefects.add(new Defect(id, originator, description, assignee, case_status, summary, priority));
                    }
                }
            } else {
                while (result.next()) {
                    id = result.getInt("ID");
                    originator = result.getString("originator");
                    description = result.getString("description");
                    assignee = result.getString("assignee");
                    case_status = result.getString("summary");
                    priority = result.getInt("priority");
                    // Add any defect to List of Defects
                    openDefects.add(new Defect(id, originator, description, assignee, case_status, summary, priority));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the List of Defects
        return openDefects;
    }
	
    public boolean isValidUser() {
        Connection connection   = null;
        Statement statement     = null;
        ResultSet result        = null;

        System.out.println("\nValidating the User...");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM " + USER_TABLE);

            while (result.next()) {
                // email, password, category, first_name, last_name
                if ( this.emailAddress.equals(result.getString("email")) && this.password.equals(result.getString("user_password")) ) {
                    this.category = result.getBoolean("category");
                    this.firstName = result.getString("first_name");
                    this.lastName = result.getString("last_name");
                    System.out.println("\n" + this.firstName + " " + this.getLastName() + " was found in the user table and is a valid user!");
                    return true;
                }
            }
            // MySQL - Close the Statement and Connection
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        System.out.println(" Could not validate User [emailAddress=" + emailAddress + ", password=" + password + "] - is not a registered user!");
        return false;
    }

    public void addUser() {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        System.out.println("\nAdding the User to the DB...");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
            statement = connection.createStatement();
            // INSERT INTO USER VALUES (email, password, category, firstName, lastName);

            String sqlStatement = "INSERT INTO " + USER_TABLE + " VALUES (\'"
                            + this.getEmailAddress() + "\', \'" + this.getPassword() + "\', " 
                            + 0 + ", \'" + this.getFirstName() + "\', \'" + this.lastName + "\');";
            statement.executeUpdate(sqlStatement);

            // MySQL - Close the Statement and Connection
            statement.close();
            connection.close();
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
            try { if (result != null) result.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        System.out.println(this.toString() + "- user added!");		
    }
}
