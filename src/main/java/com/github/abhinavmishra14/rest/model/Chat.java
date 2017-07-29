package com.github.abhinavmishra14.rest.model;

/**
 * The Class Chat.
 */
public class Chat {
	
	/** The user name. */
	private String userName;

	/** The chat. */
	private String chat;

	/** The user text. */
	private String userText;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Chat [userName=" + userName + ", chat=" + chat + ", userText=" + userText + "]";
	}
}
