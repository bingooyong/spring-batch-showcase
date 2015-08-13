/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.codecentric.batch.metrics.item;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;

import de.codecentric.batch.metrics.Action;
import de.codecentric.batch.metrics.BatchMetrics;
import de.codecentric.batch.metrics.Item;
import de.codecentric.batch.metrics.MetricNames;
import de.codecentric.batch.metrics.MetricsTestException;

/**
 * @author Tobias Flohre
 */
public class MetricsTestItemWriter implements ItemWriter<Item> {

	private static final Log log = LogFactory.getLog(MetricsTestItemWriter.class);

	private ItemWriter<Item> delegate;
	private BatchMetrics businessMetrics;

	public MetricsTestItemWriter(ItemWriter<Item> delegate,
			BatchMetrics businessMetrics) {
		this.delegate = delegate;
		this.businessMetrics = businessMetrics;
	}

	@Override
	public void write(List<? extends Item> items) throws Exception {
		delegate.write(items);
		for (Item item: items){
			log.debug("Written item: "+item);
			businessMetrics.increment(MetricNames.WRITE_COUNT.getName());
			businessMetrics.submit(MetricNames.WRITE_GAUGE.getName(), 5);
			if (item.getActions().contains(Action.FAIL_ON_WRITE)){
				throw new MetricsTestException(Action.FAIL_ON_WRITE);
			}
		}
	}

}
