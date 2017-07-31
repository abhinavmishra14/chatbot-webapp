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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Class Chat.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userName",
    "chat",
    "userText"
})
public class Chat {
	
	/** The user name. */
	@JsonProperty("userName")
	private String userName;

	/** The chat. */
	@JsonProperty("chat")
	private String chat;

	/** The user text. */
	@JsonProperty("userText")
	private String userText;

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	@JsonProperty("userName")
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	@JsonProperty("userName")
	public void setUserName(final String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the chat.
	 *
	 * @return the chat
	 */
	@JsonProperty("chat")
	public String getChat() {
		return chat;
	}

	/**
	 * Sets the chat.
	 *
	 * @param chat the new chat
	 */
	@JsonProperty("chat")
	public void setChat(final String chat) {
		this.chat = chat;
	}

	/**
	 * Gets the user text.
	 *
	 * @return the user text
	 */
	@JsonProperty("userText")
	public String getUserText() {
		return userText;
	}

	/**
	 * Sets the user text.
	 *
	 * @param userText the new user text
	 */
	@JsonProperty("userText")
	public void setUserText(final String userText) {
		this.userText = userText;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Chat [userName=" + userName + ", chat=" + chat + ", userText=" + userText + "]";
	}
}
