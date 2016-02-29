package md.pharm.external.taskSubmitted;

import md.pharm.hibernate.task.ManageTask;
import md.pharm.hibernate.task.Task;
import org.apache.commons.io.FileUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrei on 1/9/2016.
 */
public class TaskSubmittedTrigger implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        submitTodayTasks("MD");
        submitTodayTasks("RO");
    }

    public void submitTodayTasks(String country){
        ManageTask manageTask = new ManageTask(country);
        manageTask.updateCurrentSubmittedTasks();
    }

}
