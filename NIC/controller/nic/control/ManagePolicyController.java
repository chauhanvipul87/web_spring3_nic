package nic.control;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nic.dao.CustomerDao;
import nic.services.ManagePolicyService;
import nic.utility.DateTimeFormater;
import nic.utility.LogUtility;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManagePolicyController {

	Date date = new Date();
	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/viewCustomer")
	// view customer list page
	public ModelAndView  viewCustomer(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("ManagePolicyController :: viewCustomer() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model.addObject("policyList", new CustomerDao().getPolicyTypeList());
		model.setViewName("policy/index");
		LogUtility.logInfo("ManagePolicyController :: viewCustomer() Method End", logger);
		return model;
    }
	
	@RequestMapping("/listCustomer")
	// list customer page
	public ModelAndView  listCustomer(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("ManagePolicyController :: listCustomer() Method Start", logger);
		ModelAndView model = new ManagePolicyService().listCustomer(req, res);
		model.addObject("option", "listCustomers");
		model.addObject("DateTimeFormater", new DateTimeFormater());
		model.setViewName("policy/listcustomers");
		model.addObject("url","listCustomer.html");
		model.addObject("responseLayer","response");
		LogUtility.logInfo("ManagePolicyController :: listCustomer() Method End", logger);
		return model;
    }

	@RequestMapping("/viewCustomerDetail")
	// list customer page
	public ModelAndView  viewCustomerDetail(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("ManagePolicyController :: viewCustomerDetail() Method Start", logger);
		ModelAndView model = new ManagePolicyService().viewCustomerDetail(req, res);
		model.addObject("option", "viewCustomerDetail");
		model.addObject("DateTimeFormater", new DateTimeFormater());
		model.setViewName("policy/viewCustomerDetail");
		LogUtility.logInfo("ManagePolicyController :: viewCustomerDetail() Method End", logger);
		return model;
    }
	
	@RequestMapping("/editCustomerDetail")
	// list customer page
	public ModelAndView  editCustomerDetail(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("ManagePolicyController :: editCustomerDetail() Method Start", logger);
		ModelAndView model = new ManagePolicyService().editCustomerDetail(req, res);
		model.addObject("option", "editCustomerDetail");
		model.setViewName("policy/editCustomerDetail");
		LogUtility.logInfo("ManagePolicyController :: editCustomerDetail() Method End", logger);
		return model;
    }
	
	@RequestMapping("/updateCustomerDetails")
	// list customer page
	public ModelAndView  updateCustomerDetails(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("ManagePolicyController :: updateCustomerDetails() Method Start", logger);
		ModelAndView model = new ManagePolicyService().updateCustomerDetail(req, res);
		model.addObject("option", "editCustomerDetail");
		model.setViewName("policy/editCustomerDetail");
		LogUtility.logInfo("ManagePolicyController :: updateCustomerDetails() Method End", logger);
		return model;
    }
	
	@RequestMapping("/deleteCustomerDetails")
	// list customer page
	public ModelAndView  deleteCustomerDetails(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("ManagePolicyController :: deleteCustomerDetails() Method Start", logger);
		ModelAndView model = new ManagePolicyService().deleteCustomerDetails(req, res);
		model.addObject("option", "editCustomerDetail");
		model.setViewName("policy/editCustomerDetail");
		LogUtility.logInfo("ManagePolicyController :: deleteCustomerDetails() Method End", logger);
		return model;
    }
	
	@RequestMapping("/userProfile")
	// list customer page
	public ModelAndView  userProfile(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("ManagePolicyController :: userProfile() Method Start", logger);
		ModelAndView model = new ManagePolicyService().userProfile(req, res);
		model.setViewName("policy/userProfile");
		LogUtility.logInfo("ManagePolicyController :: userProfile() Method End", logger);
		return model;
    }
	
	@RequestMapping(value="/export" ,method = RequestMethod.POST)
	public void exportAllCustomer(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("ManagePolicyController:exportAllCustomer Start:::", logger);
		ModelAndView model=new ModelAndView();
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-Disposition", "attachment; filename=PolicyList.csv");
		
		System.out.println("action==>"+req.getParameter("action"));
		StringBuffer listPolicy =new ManagePolicyService().exportFile(req, res);
		System.out.println("listPolicy Result:: "+listPolicy);
		if(listPolicy !=null){
			try {
				res.getWriter().println(listPolicy);
			} catch (Exception e) {
				e.getMessage();
				LogUtility.logError("ManagePolicyController:exportAllCustomer End:::"+e.getMessage(), logger);
			}
		}else{
			System.out.println("Error:while generating csv file in customer.java");
			model.addObject("ErrorMsg","Error:while generating csv file");
		}
		LogUtility.logInfo("ManagePolicyController:exportAllCustomer End:::", logger);
	}
	
	
	
}
