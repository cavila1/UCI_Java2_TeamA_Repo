/*
 * The system should provide several features: 
 * 
 * 		� The ability to submit a defect against an application, 
 *        which includes a status, priority, assignee, summary, 
 *        and detailed description.
 * 		� The ability to update an existing defect.
 * 		� The ability to assign a defect request to a person.
 * 		� The ability to email status information to a user.
 * 		� The ability to view a list of all open defects. 
 * 
 */

package com.uci.java.teama;

public class Defect {
    private int 	id;             // unique index assigned to the defect
    private String 	originator;     // email address of the originator of the defect
    private String      status;         // status of the defect/bug
    private String      assignee;       // name of person assigned to resolve defect/bug
    private String      summary;        // summary of defect/bug
    private String      description;	// detailed description of defect/bug
    private int 	priority;	// priority number given to the defect/bug
                                        // 0 = unassigned
                                        // 1 = highest priority;  10 = lowest priority
    // Default no parameter default constructor
    public Defect() {
        // initialize fields
        this.id = 0;
        this.originator = null;
        this.description = null;
        this.assignee = null;
        this.status = "open";
        this.summary = null;
        this.priority = 0;              // set initially to 0 = unassigned
    }
    // Six parameter constructor
    public Defect(String originator, String description, String assignee, String status, String summary, int priority) {
        // initialize fields
        //do not set ID as ID is automatically assigned by system
        this.originator = originator;
        this.description = description;
        this.assignee = assignee;
        this.status = status;
        this.summary = summary;
        this.priority = 0;		// initialize with 0 = unassigned
    }
    // Seven parameter constructor
    public Defect(int id, String originator, String description, String assignee, String status, String summary, int priority) {
        // initialize fields
        this.id = id;
        this.originator = originator;
        this.description = description;
        this.assignee = assignee;
        this.status = status;
        this.summary = summary;
        this.priority = 0;		// initialize with 0 = unassigned
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Defect [id=" + id + ", originator=" + originator + ", status="
            + status + ", assignee=" + assignee + ", summary=" + summary
            + ", description=" + description + ", priority=" + priority
            + "]";
    }
}
