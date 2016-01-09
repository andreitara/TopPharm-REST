package md.pharm.external.log;

import md.pharm.external.backup.BackupUtil;
import org.apache.commons.io.FileUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Andrei on 1/9/2016.
 */
public class LogTrigger implements Job{

    private static String LINUX_PATH = "/home/toppharm/";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        backupLogFile();
    }

    public void backupLogFile(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String reportDate = df.format(Calendar.getInstance().getTime());
        String folder = LINUX_PATH + "logs/" + year + "/" + month + "/";
        File file = new File(folder);
        if(!file.exists())
            file.mkdirs();
        File nohup = new File(LINUX_PATH+"nohup.out");
        File bakup = new File(folder + "nohup_" + reportDate + ".out");
        try {
            FileUtils.copyFile(nohup,bakup);
            FileUtils.writeStringToFile(nohup, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
