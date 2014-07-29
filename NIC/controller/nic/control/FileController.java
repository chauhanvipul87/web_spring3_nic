package nic.control;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nic.dao.CustomerDao;
import nic.services.FileServices;
import nic.utility.GlobalVariables;
import nic.utility.LogUtility;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {

	Date date = new Date();
	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/importfile")
	// show import file page
	public ModelAndView  showimportFileForm(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("FileController :: showimportFileForm() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model.addObject("policytype", new CustomerDao().getPolicyTypeList());
		model.setViewName("home/importfile");
		LogUtility.logInfo("FileController :: showimportFileForm() Method End", logger);
		return model;
    }

	@RequestMapping(value="/file_entered", method=RequestMethod.POST)
	// show import file page
	public ModelAndView  file_entered(HttpServletRequest req,HttpServletResponse res,@RequestParam("file") MultipartFile file){
		LogUtility.logInfo("FileController :: file_entered() Method Start", logger);
		ModelAndView model = new ModelAndView();
		String policy_type = req.getParameter("policytype");
		if(policy_type == null || policy_type.equals("0")){
			model.addObject("policytype", new CustomerDao().getPolicyTypeList());
			model.addObject("errorClass", GlobalVariables.errorClass);
			model.addObject("errorMsg", "Please select type of policy.");
			model.setViewName("home/importfile");
			return model;
		}
		model = new FileServices().file_entered(req,res,file);
		model.addObject("policytype", new CustomerDao().getPolicyTypeList());
		LogUtility.logInfo("FileController :: file_entered() Method End", logger);
		return model;
    }

	
}
