package com.opencredo.sandbox.gawain.springbatch.remote.chunking;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.apache.activemq.broker.BrokerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BrokerContext extends AbstractStartable<Integer> {
	
	private static final Log logger = LogFactory.getLog(BrokerContext.class);
	private final String CONTEXT_PATH;
	
	public BrokerContext(String contextPath) {
		this.CONTEXT_PATH = contextPath; 
	}

	@Override
	public Integer call() throws Exception {
		try {
			applicationContext = new ClassPathXmlApplicationContext(CONTEXT_PATH);
		} catch (Exception e) {
			logger.error("error initializing slave context with path " + CONTEXT_PATH, e);
			throw new RuntimeException(e);
		}
		return 0;
	}
	
}
