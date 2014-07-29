package nic.control;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nic.services.AdminService;
import nic.utility.LogUtility;
import nic.utility.db.DbUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminPanelController {
	Date date = new Date();
	Logger logger = Logger.getLogger(this.getClass());
	
	
	@RequestMapping("/adminpanel") 
	//show admin panel login page
	public ModelAndView  showAdminPanel(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: showAdminPanel() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model.setViewName("home/adminpanel");
		LogUtility.logInfo("AdminController :: showAdminPanel() Method End", logger);
		return model;
		}

	@RequestMapping("/adminlogin")
	// validate login request.
	public ModelAndView  adminLogin(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: adminLogin() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().adminLogin(req,res);
		LogUtility.logInfo("AdminController :: adminLogin() Method End", logger);
		return model;
		}
	
	@RequestMapping("/adminlogout")
	// logout from system
	public ModelAndView  adminlogout(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: adminlogout() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().adminLogout(req,res);
		LogUtility.logInfo("AdminController :: adminlogout() Method End", logger);
		return model;
		}

	@RequestMapping("/adminhome")
	// show admin panel home page
	public ModelAndView  adminHome(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: adminHome() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model.setViewName("home/adminhome");
		LogUtility.logInfo("AdminController :: adminHome() Method End", logger);
		return model;
    }
	
	@RequestMapping("/showMemberList")
	// show user list
	public ModelAndView  showMemberList(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: showMemberList() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().showMemberList(req,res);
		LogUtility.logInfo("AdminController :: showMemberList() Method End", logger);
		return model;
		}

	@RequestMapping("/addnewuser")
	// Add new user
	public ModelAndView  addNewUser(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: addNewUser() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().addNewUser(req,res);
		LogUtility.logInfo("AdminController :: addNewUser() Method End", logger);
		return model;
		}

	@RequestMapping("/editUserDetails")
	// open edit user details.
	public ModelAndView  editUserDetails(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: editUserDetails() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().editUserDetails(req,res);
		LogUtility.logInfo("AdminController :: editUserDetails() Method End", logger);
		return model;
		}
	
	
	@RequestMapping("/updateUserDetails")
	// update user details.
	public ModelAndView  updateUserDetails(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: updateUserDetails() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().updateUserDetails(req,res);
		LogUtility.logInfo("AdminController :: updateUserDetails() Method End", logger);
		return model;
		}
	

	@RequestMapping("/viewUserDetails")
	// view User  details.
	public ModelAndView  viewUserDetails(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: viewUserDetails() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().viewUserDetails(req,res);
		LogUtility.logInfo("AdminController :: viewUserDetails() Method End", logger);
		return model;
		}
	
	@RequestMapping("/deleteUserDetails")
	// view User  details.
	public ModelAndView  deleteUserDetails(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: deleteUserDetails() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().deleteUserDetails(req,res);
		LogUtility.logInfo("AdminController :: deleteUserDetails() Method End", logger);
		return model;
		}
	
	
	@RequestMapping("/viewUserWork")
	// view User  details.
	public ModelAndView  viewUserWork(HttpServletRequest req,HttpServletResponse res){
		LogUtility.logInfo("AdminController :: viewUserWork() Method Start", logger);
		ModelAndView model = new ModelAndView();
		String action = req.getParameter("action") ==null  ? "showForm" : req.getParameter("action");
		if(action.equals("showForm")){
			QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
			String query ="select id,username from user_login where delete_flag = 0";
			try {
				model.addObject("userList", qrun.query(query, new MapListHandler()));
				model.setViewName("admin/viewUserWork");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			model = new AdminService().showWorkDoneByUser(req,res);
			model.setViewName("admin/viewUserWorkResponse");
		}
		LogUtility.logInfo("AdminController :: viewUserWork() Method End", logger);
		return model;
		}
	
	
	
}
