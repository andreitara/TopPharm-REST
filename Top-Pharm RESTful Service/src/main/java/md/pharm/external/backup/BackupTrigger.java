package md.pharm.external.backup;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by Andrei on 10/24/2015.
 */
public class BackupTrigger implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BackupUtil backupUtil = new BackupUtil();
        backupUtil.createMDBackup();
        backupUtil.createROBackup();
    }
}
