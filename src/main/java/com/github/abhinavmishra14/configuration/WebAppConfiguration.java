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
package com.github.abhinavmishra14.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.github.abhinavmishra14.interceptor.LoggerInterceptor;

/**
 * The Class WebAppConfiguration.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.github.abhinavmishra14.web","com.github.abhinavmishra14.rest"})
public class WebAppConfiguration extends WebMvcConfigurerAdapter{
	
	/**
	 * View resolver.
	 *
	 * @return the view resolver
	 */
	@Bean(name="chatbot-webapp")
	public ViewResolver viewResolver() {
		final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
	 * Message source.
	 *
	 * @return the message source
	 */
	@Bean(name ="messageSource")
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(new String [] {"classpath:messages/chatbot","classpath:messages/views"});
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
	/**
	 * Locale resolver.
	 *
	 * @return the locale resolver
	 */
	@Bean(name="localeResolver")
	public LocaleResolver localeResolver(){
	    final CookieLocaleResolver resolver = new CookieLocaleResolver();
	    resolver.setDefaultLocale(Locale.ENGLISH);
	    resolver.setCookieName("chatBotLocaleCookie");
	    resolver.setCookieMaxAge(3600);
	    return resolver;
	} 
	
	/**
	 * Locale interceptor.
	 *
	 * @return the locale change interceptor
	 */
	@Bean(name="localeInterceptor")
    public LocaleChangeInterceptor localeInterceptor(){
        final LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
        registry.addInterceptor(new LoggerInterceptor());
    }
    
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
     */
    @Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    	registry.addResourceHandler(new String [] {"/resources/**"}).addResourceLocations("/resources/");;
	}
}