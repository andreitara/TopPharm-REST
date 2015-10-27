package md.pharm.external.email;

import md.pharm.external.backup.BackupTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.MailSender;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by c-andrtara on 10/21/2015.
 */
public class EmailUtil {

    public static String[] emails;
    public static String days = "";
    public static String time = "";

    public static String cronString = "";

    static {
        Properties emailProperties = new Properties();
        try {
            emailProperties.load(new FileInputStream("email.properties"));

            String email = emailProperties.getProperty("emails");
            emails = email.split(",");

            days = emailProperties.getProperty("days");
            time = emailProperties.getProperty("time");

            int hour = Integer.parseInt(time.split(":")[0]);
            int minute = Integer.parseInt(time.split(":")[1]);
            cronString = "0 " + minute + " " + hour + " ? * " + days;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmail(String to, String subject, String message){
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
        msg.setTo(to);
        msg.setText(message);
        msg.setSubject(subject);

        try{
            mailSender.send(msg);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void startTrigger(){
        System.out.println(java.util.Calendar.getInstance().getTime() + " : Email Trigger was started with cron argument = " + cronString);
        JobDetail job = JobBuilder.newJob(EmailTrigger.class)
                .withIdentity("dummyJobName", "emailTrigger").build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("dummyTriggerName", "emailTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronString))
                .build();

        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        }catch (SchedulerException e){
            e.printStackTrace();
        }
    }
}
