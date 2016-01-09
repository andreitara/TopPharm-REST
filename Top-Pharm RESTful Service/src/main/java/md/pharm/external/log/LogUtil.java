package md.pharm.external.log;

import md.pharm.external.backup.BackupTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Andrei on 1/9/2016.
 */
public class LogUtil {

    public LogUtil(){}

    public static void startTrigger(String conTriggerArgument){
        JobDetail job = JobBuilder.newJob(LogTrigger.class)
                .withIdentity("dummyJobName", "logTrigger").build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("dummyTriggerName", "logTrigger")
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
}
