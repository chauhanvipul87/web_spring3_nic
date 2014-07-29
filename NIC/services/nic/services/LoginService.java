package nic.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nic.utility.GlobalVariables;
import nic.utility.LogUtility;
import nic.utility.db.DbUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public class LoginService {

	Logger logger = Logger.getLogger(this.getClass());
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String query ="";
	String errorMsg ="";
	
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: login() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String username = req.getParameter("username") ==null ? "" :req.getParameter("username");
			String password = req.getParameter("password") ==null ? "" :req.getParameter("password");
			
			if(username.equalsIgnoreCase("")&& password.equalsIgnoreCase("")){
				errorMsg ="Please Enter Username and Password.";
			}else if(username.equalsIgnoreCase("")){
				errorMsg ="Please Enter Username.";
			}else if(password.equalsIgnoreCase("")){
				errorMsg ="Please Enter Password.";
			}
			if(!errorMsg.equalsIgnoreCase("")){
				model.addObject("errorMsg",errorMsg);
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.setViewName("home/login");
				return model;
			}
			query ="select id from user_login where username = ? and user_password = password(?) and delete_flag =0";
			Map<String,Object> userDetails =qrun.query(query, new MapHandler(),username,password);
			//System.out.println("count ::"+count);
			if(userDetails !=null && !userDetails.isEmpty()){
				HttpSession session = req.getSession(true);
				session.setAttribute("username", username);
				session.setAttribute("user_id", userDetails.get("id").toString());
				model.setViewName("home/redirect_home");
				return model;
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				errorMsg ="Please Enter valid username and password.<br/>Try Again.";
				model.addObject("errorMsg",errorMsg);
				model.setViewName("home/login");
				return model;
			}
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			errorMsg ="Problem occured while validating User Account.";
			model.addObject("errorMsg",errorMsg);
			model.setViewName("home/login");
			LogUtility.logError(e.getMessage()+"AdminService :: login() End", logger);
			
		}
		LogUtility.logInfo("AdminService :: login() End", logger);
		return model;
	}

	/* method used for a validating the user authentication...*/	
	public ModelAndView logout(HttpServletRequest req,HttpServletResponse res)
	{
		LogUtility.logInfo("Enter class :invoke login() ",logger);
		try{
			HttpSession session = req.getSession();
			ModelAndView model=new ModelAndView();
//			System.out.println("in .... logout....");
			if(session.getAttribute("username")!=null){
				session.removeAttribute("username"); // remove username from session 
				session.removeAttribute("user_id");  //user id
				session.invalidate();
			}
			session = null;
			model.clear();
			model.setViewName("home/login");
			LogUtility.logInfo("Exit logout class :end login() ",logger);
			return model;
		}catch(Exception e){
			ModelAndView model=new ModelAndView();
			model.addObject("errorMsg","Session Problem");
			model.setViewName("home/login");
			LogUtility.logError(e.getMessage()+"Exit logout class :end login() ",logger);
			e.printStackTrace();
			return model; 
		}
	}

}
