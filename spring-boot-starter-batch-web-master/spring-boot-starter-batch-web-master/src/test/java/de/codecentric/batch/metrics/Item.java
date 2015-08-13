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
package de.codecentric.batch.metrics;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Tobias Flohre
 */
public class Item {
	
	private Action firstAction;
	private Action secondAction;
	private String description;
	private Long id;
	public Set<Action> getActions() {
		Set<Action> actions = new HashSet<>();
		if (firstAction != null){
			actions.add(firstAction);
		}
		if (secondAction != null){
			actions.add(secondAction);
		}
		return actions;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Action getFirstAction() {
		return firstAction;
	}
	public void setFirstAction(Action firstAction) {
		this.firstAction = firstAction;
	}
	public Action getSecondAction() {
		return secondAction;
	}
	public void setSecondAction(Action secondAction) {
		this.secondAction = secondAction;
	}
	@Override
	public String toString() {
		return "Item [firstAction=" + firstAction + ", secondAction="
				+ secondAction + ", description=" + description + ", id=" + id
				+ "]";
	}

}
