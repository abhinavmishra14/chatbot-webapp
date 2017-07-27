package com.github.abhinavmishra14.web.model;

import java.util.Map;

/**
 * The Class ChatResponse.
 */
public class ChatResponse {

	/** The chat. */
	private String chat;

	/** The validated. */
	private boolean validated;
	
	/** The error messages. */
	private Map<String, String> errorMessages;

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
