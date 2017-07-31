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
package com.github.abhinavmishra14.rest.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.abhinavmishra14.json.utils.JSONUtils;
import com.github.abhinavmishra14.pojo.BotConfig;
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

	/** The session. */
	@Autowired
	private HttpSession session;

	/** The bot config. */
	private BotConfig  botConfig;

	/**
	 * Chat bot interaction.
	 *
	 * @param locale the locale
	 * @param chat the chat
	 * @param request the request
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
			//TODO:: (Here>>) Persist the chat log into DB or Cache in order to export at the end of session
			chatResp.setValidated(true);
			final Object userSession = session.getAttribute("userName");
			if (userSession == null) {
				session.setAttribute("userName", chat.getUserName());	
			}

			final String userText = chat.getUserText();
			chatResp.setUserText(chat.getUserName()+ ": "+ userText); //return user provided input back.
			final boolean helpFlag = getFlag("helpFlag");

			if(helpFlag) {
				LOGGER.info("Help enabled: {}", helpFlag);
				final boolean isQuestioning = getFlag("questioning");
				if (userText.toLowerCase().contains("yes") || isQuestioning) {
					final List<String> questions = getQuestions();
					if (questions != null && !questions.isEmpty()) {
						final Object questionCount = session.getAttribute("questionCount");
						int index = questionCount != null ? (int) questionCount : 0;
						if(index < questions.size()) {
							botResponse(questions.get(index), chatResp);
							index++;
							session.setAttribute("questionCount", index); 
							if (!isQuestioning) {
								session.setAttribute("questioning", true); 
							}
						} else {
							botResponse(messageSource.getMessage("thankYou", null, locale), chatResp);
							//TODO:: Export the chat log as JSON from cache or persistence object and invalidate the session
							//TODO:: This JSONObject below is just to show how to export the chat data into JSON.
							// Currently utilizing the request data for demo.
							final JSONObject exportedChat = new JSONObject(
									JSONUtils.convertFromJsonObjectToString(chat));
							LOGGER.info("ChatLog: "+ exportedChat.getString("chat"));
							if (session != null) {
								session.invalidate();
							}
						}	
					} else {
						botResponse(messageSource.getMessage("failed_to_find_questions", null, locale), chatResp);
						session.setAttribute("helpFlag", false);
					}
				} else {
					engageUser(locale, chatResp, userText);
				}
			} else {
				if (userText.toLowerCase().contains("help")) {
					botResponse(messageSource.getMessage("help", null, locale), chatResp);
					session.setAttribute("helpFlag", true);
				} else {
					engageUser(locale, chatResp, userText);
				}
			}
		}
		return chatResp;
	}

	/**
	 * Chat bot interaction exit.
	 *
	 * @param locale the locale
	 */
	@RequestMapping(value = "/chatBotInteractionExit", method = RequestMethod.GET)
	public void chatBotInteractionExit(final Locale locale) {
		LOGGER.info("chatBotInteractionExit invoked..");
		//TODO:: Export the chat log as JSON from cache or persistence object and invalidate the session
		if (session != null) {
			session.invalidate();
		}
	}
	
	/**
	 * Gets the questions.
	 *
	 * @return the questions
	 */
	private List<String> getQuestions() {
		if (botConfig == null) {
			botConfig = (BotConfig) JSONUtils.convertToJsonObjectFromInputStream(
					ChatBotInteractionController.class.getResourceAsStream("/config/bot-config.json"), BotConfig.class);
		}
		return botConfig.getIntents().getQuestions();
	}

	/**
	 * Gets the flag.
	 *
	 * @param attribute the attribute
	 * @return the flag
	 */
	private boolean getFlag(final String attribute) {
		final Object flagObj = session.getAttribute(attribute);
		return flagObj != null ? (boolean) flagObj : false;
	}

	/**
	 * Engage user.
	 *
	 * @param locale the locale
	 * @param chatResp the chat resp
	 * @param userText the user text
	 */
	private void engageUser(final Locale locale, final ChatResponse chatResp, final String userText) {
		if (getFlag("helpFlag")) {// Set the flag to false whenever engaging user if flag was set to true
			session.setAttribute("helpFlag", false); 
		}
		final int decider = (int) (Math.random() * 2 + 1);
		if (userText.toLowerCase().contains("hi") 
				|| userText.toLowerCase().contains("hello")) {
			botResponse(messageSource.getMessage("hi", null, locale), chatResp);
		} else if (userText.toLowerCase().contains("how are you") 
				|| userText.toLowerCase().contains("what's up")
				|| userText.toLowerCase().contains("whats up")) {
			botResponse(messageSource.getMessage("how are you".replaceAll(" ", "_")+decider, null, locale), chatResp);
		} else {
			botResponse(messageSource.getMessage("rephrase" + decider, null, locale), chatResp);
		}
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
