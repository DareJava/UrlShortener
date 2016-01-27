package com.amacodecode.urlshortener;

import java.text.DateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.amacodecode.entities.UrlDescriptor;
import com.amacodecode.services.ShortenerInterface;
import com.amacodecode.util.Base62;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ShortenerInterface shortenerInterface;
	
	
	public ShortenerInterface getShortenerInterface() {
		return shortenerInterface;
	}


	public void setShortenerInterface(ShortenerInterface shortenerInterface) {
		this.shortenerInterface = shortenerInterface;
	}


	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/p/{url}", method = RequestMethod.GET)
	public @ResponseBody String persist(HttpServletRequest request,Model model,@PathVariable("url")String url) {
		 if(url.isEmpty()){
			 // error message when no param
			 return "Wrong request.. it should be in this format {http://localhost:8082/urlshortener/p/<your-url>}";
		 }
		 else{
		 int response =shortenerInterface.persistUrl(url);
		 // cinvert that to base 62
		 final String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		 String changed ="http://localhost:8082/urlshortener/"+Base62.generateRandomAlphanumeric()+Base62.fromBase10(response);
		return changed;
		 }
	}
	
	@RequestMapping(value = "/{urlshort}", method = RequestMethod.GET)
	public @ResponseBody String retrieve(HttpServletRequest request,Model model,@PathVariable("urlshort")String url) {
		 if(url.isEmpty()){
			 // error message when no param
			 return "Wrong request.. it should be in this format {http://localhost:8082/urlshortener/<alphanumeric-key>}";
		 }
		 else{
		return shortenerInterface.getFullUrlFromUsingString(url);
		 }
	} 
}
