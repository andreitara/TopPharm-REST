package md.pharm.external.email;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.MailSender;

import java.util.Properties;

/**
 * Created by c-andrtara on 10/21/2015.
 */
public class EmailUtil {

    public static void sendEmail(){
        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.ssl.trust","smtp.gmail.com");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(25);
        mailSender.setUsername("toppharmapi@gmail.com");
        mailSender.setPassword("Fdasf987ukjah345kl");
        mailSender.setJavaMailProperties(props);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("toppharmapi@gmail.com");
        msg.setTo("andrei.tara@edifecs.com");
        msg.setText("Hi from my app");
        msg.setSubject("Subject");

        try{
            mailSender.send(msg);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private class EmailTrigger implements Job{
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        }
    }

}
