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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.abhinavmishra14.rest.model.WebhookResponse;

/**
 * The Class WebhookController.
 */
@RestController
@RequestMapping("/")
public class WebhookController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WebhookController.class);
	
	/**
	 * Webhook.
	 *
	 * @param aStr
	 *            the a string
	 * @return the webhook response
	 * @see <a href="https://api.ai/docs/fulfillment#request">WebHook Request</a>
	 */
	@RequestMapping(value = "/webhook", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody WebhookResponse webhook(@RequestBody final String aStr) {
		LOGGER.info("Input data: {}", aStr);
		return new WebhookResponse("Hello: "+aStr, "Sample displayText");
	}
}
