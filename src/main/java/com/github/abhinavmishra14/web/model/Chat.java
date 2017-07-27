package com.github.abhinavmishra14.web.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The Class Chat.
 */
public class Chat {
	
	/** The user name. */
	@NotBlank(message = "UserName input must not be null in the request") 
	private String userName;

	/** The chat. */
	private String chat;

	/** The answer. */
	@NotBlank(message = "Answer input must not be null!")
	private String answer;

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(final String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the chat.
	 *
	 * @return the chat
	 */
	public String getChat() {
		return chat;
	}

	/**
	 * Sets the chat.
	 *
	 * @param chat the new chat
	 */
	public void setChat(final String chat) {
		this.chat = chat;
	}

	/**
	 * Gets the answer.
	 *
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Sets the answer.
	 *
	 * @param answer the new answer
	 */
	public void setAnswer(final String answer) {
		this.answer = answer;
	}
}
