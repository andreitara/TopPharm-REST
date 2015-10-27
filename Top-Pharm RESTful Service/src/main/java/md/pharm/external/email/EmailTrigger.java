package md.pharm.external.email;

/**
 * Created by Andrei on 10/26/2015.
 */

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class EmailTrigger implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String subject = "My Subject";
        String message = "Message from TopPharm REST API";
        for (String email : EmailUtil.emails) {
            System.out.println(java.util.Calendar.getInstance().getTime() + " : Send Email to = " + email);
            EmailUtil.sendEmail(email, subject, message);
        }
    }
}
