package JavaObjects;

import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named(value = "email")
@RequestScoped
public class Email {

    public static void send(String regEmail, int num) {
       // System.out.println("Before try");
        
        try{
            //System.out.println("IN TRY ");
            
            // variables
            String host ="smtp.gmail.com" ;
            String user = "Vircoms1@gmail.com";
            String pass = "VircomsOne";
            String to = regEmail;
            String from = "Vircoms";
            String subject = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account.";
            String messageText = "Enter the following code on the website : " + num;
            boolean sessionDebug = false;
            
            // Properties
            Properties props = System.getProperties();
            props.setProperty("mail.smtp.starttls.enable","true");
            props.setProperty("mail.smtp.host",host);
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "false");
             
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug); // false
            
            //msg settings
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));            
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);           
            msg.setText(messageText);
            msg.setSubject("Vircoms validation key");

            //transfort settings            
           Transport transport = mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    

      // System.out.println("End of Send");
       
    }

    @PostConstruct
    public void Email() {
    }

}