package it.polito.ezgas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  //This class is a Controller that manages Home screen interactions
/*Spring Boot allow to have an embedded server in the local machine: tomcat. 
 Important: every time a new Run of bootapplication is instantiated, Spring doesn't restart 
 the previous tomcat instance, so REMEMBER to stop previous execution*/
public class HomeController {
	@RequestMapping("/admin")	//This annotation defines that this method should be executed when the defined URL is queried.
	public String admin() {
		return "index";			//This "return" defines that, the method returns resources/static/views/index.html page on the browser
	}							//We use only "index" because the location and the extension is defined in resources/application.properties
	
	@RequestMapping("/")
	public String index() {
		return map();
	}
	
	@RequestMapping("/map")
	public String map() {
		return "map";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/update")
	public String update() {
		return "update";
	}
	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}
}
