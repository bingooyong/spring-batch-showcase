package quickstart;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String[] springConfig =
                {
                        "spring/batch/config/context.xml",
                        "spring/batch/jobs/job-report.xml"
                };

        ApplicationContext context =
                new ClassPathXmlApplicationContext(springConfig);

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        try {
            for (int i = 0; i < 10; i++) {
                Job job = (Job) context.getBean("reportJob");
                JobParameters jobParameters =
                        new JobParametersBuilder()
                                .addString("uuid", UUID.randomUUID().toString())
                                .addLong("time", System.currentTimeMillis()).toJobParameters();
                JobExecution execution = jobLauncher.run(job, jobParameters);
                System.out.println(i + " Exit Status : " + execution.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done");

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
