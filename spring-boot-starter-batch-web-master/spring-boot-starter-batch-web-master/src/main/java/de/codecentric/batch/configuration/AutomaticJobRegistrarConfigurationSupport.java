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

package de.codecentric.batch.configuration;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.AutomaticJobRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

/**
 * Extend this class to add custom {@link ApplicationContextFactory}.
 * 
 * @author Thomas Bosch
 */
public abstract class AutomaticJobRegistrarConfigurationSupport {

	@Autowired
	private AutomaticJobRegistrar automaticJobRegistrar;

	@PostConstruct
	public void initialize() throws Exception {
		// Default order for the AutomaticJobRegistrar is Ordered.LOWEST_PRECEDENCE. Since we want to register
		// listeners after the jobs are registered through the AutomaticJobRegistrar, we need to decrement its
		// order value by one. The creation of the AutomaticJobRegistrar bean is hidden deep in the automatic
		// batch configuration, so we unfortunately have to do it here.
		automaticJobRegistrar.setOrder(Ordered.LOWEST_PRECEDENCE-1);
		addApplicationContextFactories(automaticJobRegistrar);
	}

	/**
	 * Add ApplicationContextFactories to the given job registrar.
	 * 
	 * @param automaticJobRegistrar
	 *            Bean
	 * @throws Exception
	 *             Some error.
	 */
	protected abstract void addApplicationContextFactories(AutomaticJobRegistrar automaticJobRegistrar)
			throws Exception;

}
