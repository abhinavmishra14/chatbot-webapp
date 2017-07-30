package com.github.abhinavmishra14.rest.controller;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.abhinavmishra14.rest.model.Chat;
import com.github.abhinavmishra14.rest.model.ChatResponse;

/**
 * The Class ChatBotInteractionController.
 */
@RestController
@RequestMapping("/")
public class ChatBotInteractionController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatBotInteractionController.class);


    /** The message source. */
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Chat bot interaction.
	 *
	 * @param locale the locale
	 * @param chat the chat
	 * @return the chat response
	 */
	@RequestMapping(value = "/chatBotInteraction", method = RequestMethod.POST)
	public ChatResponse chatBotInteraction(final Locale locale, @RequestBody final Chat chat) {
		LOGGER.info("chatBotInteraction invoked, chat: {}", chat);
		final ChatResponse chatResp = new ChatResponse();
		if(StringUtils.isBlank(chat.getUserName())){
			chatResp.setValidated(false);
			chatResp.getErrorMessages().put("UserName", messageSource.getMessage("userName.validation", null, locale));
		} else {
			chatResp.setValidated(true);

			final int decider = (int) (Math.random() * 2 + 1);
			final String userText = chat.getUserText();
			chatResp.setUserText(chat.getUserName()+ ": "+ userText); //return user provided input back.
			
			if (userText.toLowerCase().contains("hi") 
					|| userText.toLowerCase().contains("hello")) {
				botResponse(messageSource.getMessage("hi", null, locale), chatResp);
			} else if (userText.toLowerCase().contains("how are you") 
					|| userText.toLowerCase().contains("what's up")
					|| userText.toLowerCase().contains("whats up")) {
				botResponse(messageSource.getMessage("how are you".replaceAll(" ", "_")+decider, null, locale), chatResp);
			} else {
				botResponse(messageSource.getMessage("rephrase"+decider, null, locale), chatResp);
			}
		}
		return chatResp;
	}

	/**
	 * Bot response.
	 *
	 * @param botMsg the bot msg
	 * @param chatResp the chat resp
	 */
	private void botResponse(final String botMsg, final ChatResponse chatResp) {
		chatResp.setBotMessage("Bot: "+botMsg);
	}
}
