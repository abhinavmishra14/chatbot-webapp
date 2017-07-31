/*
 * Created By: Abhinav Kumar Mishra
 * Copyright &copy; 2017. Abhinav Kumar Mishra. 
 * All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.abhinavmishra14.rest.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Class ChatResponse.
 */
public class ChatResponse {

	/** The user text. */
	private String userText;
	
	/** The bot message. */
	private String botMessage;

	/** The validated. */
	private boolean validated;
	
	/** The error messages. */
	private Map<String, String> errorMessages;

	/**
	 * Gets the user text.
	 *
	 * @return the user text
	 */
	public String getUserText() {
		return userText;
	}

	/**
	 * Sets the user text.
	 *
	 * @param userText the new user text
	 */
	public void setUserText(final String userText) {
		this.userText = userText;
	}

	/**
	 * Gets the bot message.
	 *
	 * @return the bot message
	 */
	public String getBotMessage() {
		return botMessage;
	}

	/**
	 * Sets the bot message.
	 *
	 * @param botMessage the new bot message
	 */
	public void setBotMessage(final String botMessage) {
		this.botMessage = botMessage;
	}

	/**
	 * Checks if is validated.
	 *
	 * @return true, if is validated
	 */
	public boolean isValidated() {
		return validated;
	}

	/**
	 * Sets the validated.
	 *
	 * @param validated the new validated
	 */
	public void setValidated(final boolean validated) {
		this.validated = validated;
	}

	/**
	 * Gets the error messages.
	 *
	 * @return the error messages
	 */
	public Map<String, String> getErrorMessages() {
		if (this.errorMessages == null) {
			this.errorMessages = new ConcurrentHashMap<String, String> ();
		}
		return errorMessages;
	}

	/**
	 * Sets the error messages.
	 *
	 * @param errorMessages the error messages
	 */
	public void setErrorMessages(final Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
}
