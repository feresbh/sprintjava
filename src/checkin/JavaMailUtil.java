/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkin;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author lenovo
 */
public class JavaMailUtil {
    public static void main(String recepient) throws Exception {
        
        System.out.println("preparing to send");
        Properties proporties = new Properties();
        
        proporties.put("mail.smtp.auth", "true");
        proporties.put("mail.smtp.starttls.enable", "true");
        proporties.put("mail.smtp.host", "smtp.gmail.com");
        proporties.put("mail.smtp.port", "587");
        
        
        String MyAccountEmail = "medazizbensmida@gmail.com";
        String password = "Iwillbeericher1003";
        
        Session session = Session.getInstance(proporties,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MyAccountEmail, password);
            }
      
        });
        
        Message message = prepareMessage(session, MyAccountEmail, recepient);
        
        Transport.send(message);
        System.out.println("message sent");
        
    }

    
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Travminers agency");
            String htmlCode = "<h1> Confirmation Message</h1> <br/> <h2><b>Hello customer, we are pleased to have you in our comunity, we cant wait to have you among us. </b></h2> <br/> <h3>For further information please visit our website</b></h3>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    
}
