package com.opencredo.sandbox.gawain.springbatch.remote.chunking;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.batch.core.BatchStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.FileSystemUtils;


@DirtiesContext
public class RemoteChunkingFailureIntegrationTest {

	private static final Log logger = LogFactory.getLog(RemoteChunkingFailureIntegrationTest.class);
	
	/**
	 * For remote failures to work some extra config is needed and special transports semantics apparently. 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRemoteChunkingShouldFailWithErrors() throws Exception {

		final BrokerContext broker = (BrokerContext) new BrokerContext("classpath:/broker/broker-context.xml").start();
		final MasterBatchContext masterBatchContext = new MasterBatchContext("testjob", "classpath:/master/master-batch-context.xml");
		final SlaveContext slaveContext = new SlaveContext("classpath:/slave/slave-batch-fail-context.xml");

		masterBatchContext.start();
		slaveContext.start();

		BatchJobTestHelper.waitForJobTopComplete(masterBatchContext);

		BatchStatus batchStatus = masterBatchContext.getBatchStatus();
		logger.debug("********** job finished with status: " + batchStatus);
		logger.info("slave chunks written: " + slaveContext.writtenCount() );
		Assert.assertEquals("Batch Job Status", BatchStatus.FAILED, batchStatus);

	}
	
	

}
