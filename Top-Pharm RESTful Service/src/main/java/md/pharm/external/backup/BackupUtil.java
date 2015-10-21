package md.pharm.external.backup;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.product.Objective;
import md.pharm.restservice.service.util.Country;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.util.Calendar;
import java.util.List;

/**
 * Created by c-andrtara on 10/21/2015.
 */
public class BackupUtil {

    private Session session;

    private static String WINDOWS_PATH = "D:\\TopPharm/";
    private static String LINUX_PATH = "/home/TopPharm/";

    Country md = Country.valueOf("MD");
    String mdDataBaseName = "TopPharmMD";

    Country ro = Country.valueOf("RO");
    String roDataBaseName = "TopPharmRO";

    public BackupUtil(){}

    public void createMDBackup(){
        session = HibernateUtil.getSession(md);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int minute = Calendar.getInstance().get(Calendar.MINUTE);
            String date = "" + year + month + day + "_" + hour + minute;
            String folder = WINDOWS_PATH + "md/" + year + "/" + month + "/";
            File file = new File(folder);
            if(!file.exists())
                file.mkdirs();
            session.createSQLQuery("BACKUP DATABASE TopPharmMD TO DISK = '"+ folder + "TopPharmMD_bak_" + date + ".txt'").executeUpdate();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
    }

    public void createROBackup(){
        session = HibernateUtil.getSession(ro);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int minute = Calendar.getInstance().get(Calendar.MINUTE);
            String date = "" + year + month + day + "_" + hour + minute;
            String folder = WINDOWS_PATH + "ro/" + year + "/" + month + "/";
            File file = new File(folder);
            if(!file.exists())
                file.mkdirs();
            session.createSQLQuery("BACKUP DATABASE TopPharmRO TO DISK = '"+ folder + "TopPharmRO_bak_" + date + ".txt'").executeUpdate();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
    }

}
