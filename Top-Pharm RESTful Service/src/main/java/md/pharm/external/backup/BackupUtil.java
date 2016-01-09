package md.pharm.external.backup;

import md.pharm.util.Country;
import md.pharm.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by c-andrtara on 10/21/2015.
 */
public class BackupUtil {

    private Session session;

    private static String WINDOWS_PATH = "D:\\TopPharm/";
    private static String LINUX_PATH = "/home/toppharm/";

    Country md = Country.valueOf("MD");
    String mdDataBaseName = "TopPharmMD";

    Country ro = Country.valueOf("RO");
    String roDataBaseName = "TopPharmRO";

    public BackupUtil(){}

    public static void startTrigger(String conTriggerArgument){
        JobDetail job = JobBuilder.newJob(BackupTrigger.class)
                .withIdentity("dummyJobName", "backupTrigger").build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("dummyTriggerName", "backupTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(conTriggerArgument))
                .build();

        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        }catch (SchedulerException e){
            e.printStackTrace();
        }
    }

    public void createMDBackup(){
        System.out.println(Calendar.getInstance().getTime() + " : TopPharmMD Backup was triggered");
        session = HibernateUtil.getSession(md);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String reportDate = df.format(Calendar.getInstance().getTime());
            String folder = LINUX_PATH + "bakup/md/" + year + "/" + month + "/";
            File file = new File(folder);
            if(!file.exists())
                file.mkdirs();
            session.createSQLQuery("BACKUP DATABASE TopPharmMD TO DISK = '"+ folder + "TopPharmMD_bak_" + reportDate + ".txt'").executeUpdate();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
    }

    public void createROBackup(){
        System.out.println(Calendar.getInstance().getTime() + " : TopPharmRO Backup was triggered");
        session = HibernateUtil.getSession(ro);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String reportDate = df.format(Calendar.getInstance().getTime());
            String folder = LINUX_PATH + "bakup/ro/" + year + "/" + month + "/";
            File file = new File(folder);
            if(!file.exists())
                file.mkdirs();
            session.createSQLQuery("BACKUP DATABASE TopPharmRO TO DISK = '"+ folder + "TopPharmRO_bak_" + reportDate + ".txt'").executeUpdate();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
    }

}
