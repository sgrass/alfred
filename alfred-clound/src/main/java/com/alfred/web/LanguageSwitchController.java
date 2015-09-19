package com.alfred.web;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Scope("prototype")
@Controller
@RequestMapping
public class LanguageSwitchController {
	
	private static Log log = LogFactory.getLog(LanguageSwitchController.class);
	
	private final String US = "en_US";
	private final String CN = "zh_CN";

	@Autowired
	@Qualifier("localeResolver")
	private CookieLocaleResolver localeResolver;
	
	@RequestMapping("/language/{locale}")
	public String language(@PathVariable String locale, HttpServletRequest request, HttpServletResponse response) {
		if (US.equals(locale)) {
			localeResolver.setDefaultLocale(new Locale(US));
			setLanguageCookie(request, response, locale);
		} else if (CN.equals(locale)) {
			localeResolver.setDefaultLocale(new Locale(CN));
			setLanguageCookie(request, response, locale);
		}
		
		return "redirect:/";
	}
	
	
	private void setLanguageCookie(HttpServletRequest request, HttpServletResponse response, String locale) {
		Cookie langCookie = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cook : cookies) {
			log.info(cook.getName()+"==========="+cook.getValue());
			if ("language".equals(cook.getName())) {
				langCookie = cook;
			}
		}
		if (langCookie == null) {
			langCookie = new Cookie("language", locale);
		} else {
			langCookie.setValue(locale);
		}
	}
}
