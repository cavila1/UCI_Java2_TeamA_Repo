package com.uci.java.teama;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * EmailClient.java
 * @author Gus Barraza
 * 
 * Class uses javax (mail.1.4.3.jar, activation-1.1.jar) dependency
 * Gmail account User: java2uci2015, password: defect2015
 *
 */
public class EmailClient {
    private String emailTo;
    private String emailFrom = "java2uci2015@gmail.com";
    private String emailSubject;
    private String emailMessage;
    private Defect defect;
    private User user;
    private String lastError;
    private final String EMAIL_PASSWORD = "defect2015";
	
    public EmailClient() {
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean sendMail() {		
        Properties props = getMailProps();
 
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("java2uci2015@gmail.com", EMAIL_PASSWORD);
            }
        });
 
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.getEmailFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getEmailTo()));
            message.setSubject(getEmailSubject());
            message.setText(getEmailMessage());

            Transport.send(message);

            System.out.println("Done");

            return true;
        } catch (MessagingException e) {
            setLastError("Error occurred sending email: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * sendMail - using User and Defect objects to populate email properties and send email.
     * @param userObject
     * @param defectObject
     * @return
     */
    public boolean sendMail(User userObject, Defect defectObject) {
        //load the objects into email client properties
        setObjectData(userObject, defectObject);
        return sendMail();
    }
	
    private void setObjectData (User userObject, Defect defectObject) {		
        setEmailTo(userObject.getEmailAddress());
        setEmailSubject("Defect:" + String.valueOf(defectObject.getId()) + " Notification");
        setEmailMessage(loadMessage(userObject,defectObject));
    }
	
    /**
     * Properties function sets email relates properties
     * @return
     */
    private Properties getMailProps() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        return props;
    }

    public String getLastError() {
        return lastError;
    }

    private void setLastError(String lastError) {
        this.lastError = lastError;
    }
	
    /**
     * loadMessage - utilizes Defect object to generate a default message for the email
     * @param objDefect
     * @return
     */
    private String loadMessage(User objUser, Defect objDefect) {
        StringBuffer msg = new StringBuffer();

        msg.append("Hello " + objUser.getFirstName() + " " + objUser.getLastName() + ",\n");
        msg.append("The following is an automated message to alert you regarding updates to the following Defect\n\n");
        msg.append("REPORTED BY: " + objDefect.getOriginator() + "\n");
        msg.append("ASSIGNED TO: " + objDefect.getAssignee() + "\n");
        msg.append("STATUS: " + objDefect.getStatus() + "\n");
        msg.append("PRIORITY: " + String.valueOf(objDefect.getPriority()) + "\n");
        msg.append("DESCRIPION: " + objDefect.getDescription() + "\n");
        msg.append("SUMMARY INFO: " + objDefect.getSummary() + "\n");

        return msg.toString();
    }
}
