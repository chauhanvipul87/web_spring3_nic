package nic.control;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nic.dao.CustomerDao;
import nic.services.PolicyServices;
import nic.utility.DateTimeFormater;
import nic.utility.LogUtility;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PolicyController {

	Date date = new Date();
	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/addnewpolicy")
	// Add new policy 
	public ModelAndView  addNewPolicy(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("PolicyController :: addNewPolicy() Method Start", logger);
		String action = req.getParameter("action")     ==null ? "showaddfrm" :req.getParameter("action");
		ModelAndView model=new ModelAndView();
		 if(!action.equals("showaddfrm")){
			model = new PolicyServices().addNewPolicy(req, res);
  		   
		}
		model.addObject("policyList", new CustomerDao().getPolicyTypeList());
		model.addObject("option", "addnewpolicy");
		model.setViewName("policy/addnewpolicy");
	
		LogUtility.logInfo("PolicyController :: addNewPolicy() Method End", logger);
		return model;
    }

	@RequestMapping("/renew_index")
	//Renew Policy
	public ModelAndView  renew_index(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("PolicyController :: renew_index() Method Start", logger);
		ModelAndView model=new ModelAndView();
		String action = req.getParameter("action")     ==null ? "showIndexFrm" :req.getParameter("action");
		 if(!action.equals("showIndexFrm")){
			model = new PolicyServices().searchPolicyDetails(req, res);
			model.addObject("DateTimeFormater", new DateTimeFormater());
			model.setViewName("renewpolicy/index");
		 }else{
			 model.setViewName("renewpolicy/index");
		 }
		LogUtility.logInfo("PolicyController :: renew_index() Method End", logger);
		return model;
	}
	

	@RequestMapping("/addRenewPolicyDetails")
	// view customer list page
	public ModelAndView  addRenewPolicyDetails(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("CustomerController :: addRenewPolicyDetails() Method Start", logger);
		ModelAndView model = new PolicyServices().addRenewPolicyDetails(req,res);
		model.setViewName("renewpolicy/response");
		LogUtility.logInfo("CustomerController :: addRenewPolicyDetails() Method End", logger);
		return model;
    }
	
}
