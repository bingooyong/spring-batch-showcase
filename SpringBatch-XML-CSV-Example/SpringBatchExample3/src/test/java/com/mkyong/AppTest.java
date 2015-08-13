package com.mkyong;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/batch/jobs/job-report.xml",
    "classpath:spring/batch/config/context.xml",
    "classpath:spring/batch/config/database.xml",
    "classpath:spring/batch/config/test-context.xml"})
public class AppTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    
    @Test
    public void launchJob() throws Exception {

        //JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");
        
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
        
    }
}
