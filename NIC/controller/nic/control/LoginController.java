package nic.control;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nic.services.LoginService;
import nic.services.PolicyServices;
import nic.utility.DateTimeFormater;
import nic.utility.LogUtility;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	
	Date date = new Date();
	Logger logger = Logger.getLogger(this.getClass());
	
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("LoginController :: index() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model.setViewName("home/login");
		LogUtility.logInfo("LoginController :: index() Method End", logger);
		return model;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("LoginController :: login() Method Start", logger);
		ModelAndView model = new LoginService().login(req,res);
		LogUtility.logInfo("LoginController :: login() Method End", logger);
		return model;
	}
	
	@RequestMapping("/home")
	// show admin panel home page
	public ModelAndView  home(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("LoginController :: home() Method Start", logger);
		ModelAndView model = new PolicyServices().getReminderPolicyList(req,res);
		model.addObject("DateTimeFormater", new DateTimeFormater());
		model.setViewName("home/home");
		LogUtility.logInfo("LoginController :: home() Method End", logger);
		return model;
    }
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView Logout(HttpServletRequest req, HttpServletResponse res) throws Exception{
	System.out.println("Logout Controller Start..");
	LogUtility.logInfo("Enter LogoutController class :invoke login()",logger);
    ModelAndView model=new LoginService().logout(req,res);
    LogUtility.logInfo("Exit LogoutController class :exit login()",logger);
	return model;
  }
	
	
}
