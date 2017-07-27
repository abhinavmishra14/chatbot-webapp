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
package com.github.abhinavmishra14.web.controller;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.abhinavmishra14.web.model.Chat;
import com.github.abhinavmishra14.web.model.ChatResponse;

/**
 * The Class ChatBotController.
 */
@Controller
@RequestMapping("/")
public class ChatBotController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatBotController.class);
	
	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Welcome.
	 *
	 * @param locale
	 *            the locale
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String welcome(final Locale locale, final ModelMap model) {
		model.addAttribute("greeting",
				"Hello There! <br/>" + messageSource.getMessage("chatbot.welcome", null, locale));
		return "welcome";
	}

	/**
	 * Welcome.
	 *
	 * @param locale
	 *            the locale
	 * @param model
	 *            the model
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(final Locale locale, final ModelMap model, @RequestParam final String userName) {
		model.addAttribute("greeting", messageSource.getMessage("chatbot.greetings", new String[] { userName }, locale)
				+ "<br/>" + messageSource.getMessage("chatbot.welcome", null, locale));
		return "chat";
	}

	/**
	 * Chat.
	 *
	 * @param locale
	 *            the locale
	 * @param model
	 *            the model
	 * @param chat
	 *            the chat
	 * @param result
	 *            the result
	 * @return the string
	 */
	@RequestMapping(value = "/chat", method = RequestMethod.POST)
	public ChatResponse chat(final Locale locale, @ModelAttribute @Valid final Chat chat, final BindingResult result) {
		LOGGER.info("chat invoked..");
		final ChatResponse chatResp = new ChatResponse();
		if (result.hasErrors()) {
			final Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			chatResp.setValidated(false);
			chatResp.setErrorMessages(errors);
		} else {
			chatResp.setValidated(true);
			chatResp.setChat("hello");
		}
		return chatResp;
	}
}