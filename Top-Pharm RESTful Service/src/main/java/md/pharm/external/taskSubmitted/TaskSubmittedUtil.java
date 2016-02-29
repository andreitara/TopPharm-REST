package md.pharm.external.taskSubmitted;

import md.pharm.external.log.LogTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Andrei on 1/9/2016.
 */
public class TaskSubmittedUtil {

    public TaskSubmittedUtil(){}

    public static void startTrigger(String conTriggerArgument){
        JobDetail job = JobBuilder.newJob(TaskSubmittedTrigger.class)
                .withIdentity("dummyJobName", "taskSubmittedTrigger").build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("dummyTriggerName", "taskSubmittedTrigger")
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
