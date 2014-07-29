package nic.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nic.utility.DateTimeFormater;
import nic.utility.GlobalVariables;
import nic.utility.LogUtility;
import nic.utility.db.DbUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.apache.tomcat.jni.Global;
import org.springframework.web.servlet.ModelAndView;



public class AdminService {
	Logger logger = Logger.getLogger(this.getClass());
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String query ="";
	String errorMsg ="";
	
	public ModelAndView adminLogin(HttpServletRequest req,	HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: adminLogin() Start", logger);
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
				model.setViewName("home/adminpanel");
				
				return model;
			}
			query ="select id from user_login where username = ? and user_password = password(?) and delete_flag =0 and usertype_id = 1";
			Map<String,Object> userDetails =qrun.query(query, new MapHandler(),username,password);
			//System.out.println("count ::"+count);
			if(userDetails !=null && !userDetails.isEmpty()){
				HttpSession session = req.getSession(true);
				session.setAttribute("username", username);
				session.setAttribute("user_id", userDetails.get("id").toString());
				model.setViewName("home/redirect_admin_home");
				return model;
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				errorMsg ="Please Enter valid username and password.<br/>Try Again.";
				model.addObject("errorMsg",errorMsg);
				model.setViewName("home/adminpanel");
				return model;
			}
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			errorMsg ="Problem occured while validating User Account.";
			model.addObject("errorMsg",errorMsg);
			model.setViewName("home/adminpanel");
			LogUtility.logError(e.getMessage()+"AdminService :: adminLogin() End", logger);
			
		}
		LogUtility.logInfo("AdminService :: adminLogin() End", logger);
		return model;
	}

	public ModelAndView showMemberList(HttpServletRequest req,	HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: showMemberList() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			query ="SELECT id, username,if(usertype_id=1,'Admin User','Staff User') as usertype , fname, lname,phone, birthdate, email, doj FROM user_login where delete_flag =0";
			List<Map<String,Object>>listUser = qrun.query(query,new MapListHandler());
			model.addObject("listUser", listUser);
			model.addObject("option","listUser");
			
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			errorMsg ="Error occured while fetching list user data.";
			model.addObject("errorMsg",errorMsg);
			LogUtility.logError(e.getMessage()+"AdminService :: showMemberList() End", logger);
			
		}
		LogUtility.logInfo("AdminService :: showMemberList() End", logger);
		model.setViewName("home/listuser");
		return model;
	}

	public ModelAndView addNewUser(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: addNewUser() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String username = req.getParameter("username") ==null ? "" :req.getParameter("username");
			String password = req.getParameter("password") ==null ? "" :req.getParameter("password");
			String fname 	= req.getParameter("fname")	   ==null ? "" :req.getParameter("fname");
			String lname 	= req.getParameter("lname")	   ==null ? "" :req.getParameter("lname");
			String address 	= req.getParameter("address")  ==null ? "" :req.getParameter("address");
			String phone 	= req.getParameter("phone")    ==null ? "" :req.getParameter("phone");
			String dob 		= req.getParameter("dob") 	   ==null ? "" :req.getParameter("dob");
			String doj 		= req.getParameter("doj") 	   ==null ? "" :req.getParameter("doj");
			String email 	= req.getParameter("email")    ==null ? "" :req.getParameter("email");
			String usertype = req.getParameter("usertype") ==null ? "2" :req.getParameter("usertype");
			
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
				model.setViewName("home/listuser");
				return model;
			}
			
			query ="INSERT INTO user_login (username, user_password, usertype_id, fname, " +
					" lname, address, phone, birthdate, email, doj,modified_datetime) " +
					" VALUES(?,password(?),?,?,?,?,?,?,?,?,?)";
			Object[] param= {username,password,usertype,fname,lname,address,phone,dob,email,doj,DateTimeFormater.format_date()};
			int affectedRows  = qrun.update(query,param);
			if(affectedRows >0){
				model.addObject("errorClass",GlobalVariables.successClass);
				errorMsg ="User Added Successfully.";
				model.addObject("errorMsg",errorMsg);
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				errorMsg ="Something went wrong while adding new user.";
				model.addObject("errorMsg",errorMsg);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			errorMsg ="Something went wrong while adding new user.";
			model.addObject("errorMsg",errorMsg);
			LogUtility.logError(e.getMessage()+"AdminService :: addNewUser() End", logger);
			
		}
		LogUtility.logInfo("AdminService :: addNewUser() End", logger);
		model.setViewName("home/listuser");
		return model;
	}

	public ModelAndView editUserDetails(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: editUserDetails() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String user_id = req.getParameter("user_id") ==null ? "" :req.getParameter("user_id");
			errorMsg ="";
			if(user_id.equalsIgnoreCase("")){
				errorMsg ="Data is not sufficient for processing your request.";
			}
			if(!errorMsg.equalsIgnoreCase("")){
				model.addObject("errorMsg",errorMsg);
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.setViewName("home/editUserDetails");
				return model;
			}
			query ="select id, username, usertype_id, fname, lname, address, phone, birthdate, email, doj  from user_login where id = ? and delete_flag =0";
			Map<String,Object> userDetailMap = (Map<String,Object>) qrun.query(query, new MapHandler(),user_id);
			if(userDetailMap!=null && userDetailMap.size()>0){
					model.addObject("userMap", userDetailMap);
					model.addObject("option" , "EditUserDetails");
					
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				errorMsg ="There is no user found having user id = "+user_id;
				model.addObject("errorMsg",errorMsg);
			}	
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			errorMsg ="Error occured while fetching User Details";
			model.addObject("errorMsg",errorMsg);
			model.setViewName("home/editUserDetails");
			LogUtility.logError(e.getMessage()+"AdminService :: editUserDetails() End", logger);
			
		}
		model.setViewName("home/editUserDetails");
		LogUtility.logInfo("AdminService :: editUserDetails() End", logger);
		return model;
	}

	public ModelAndView updateUserDetails(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: updateUserDetails() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String username = req.getParameter("edit_username") ==null ? "" :req.getParameter("edit_username");
			String password = req.getParameter("edit_password") ==null ? "" :req.getParameter("edit_password");
			String fname 	= req.getParameter("edit_fname")    ==null ? "" :req.getParameter("edit_fname");
			String lname 	= req.getParameter("edit_lname")	==null ? "" :req.getParameter("edit_lname");
			String address 	= req.getParameter("edit_address")  ==null ? "" :req.getParameter("edit_address");
			String phone 	= req.getParameter("edit_phone")    ==null ? "" :req.getParameter("edit_phone");
			String dob 		= req.getParameter("edit_dob") 	    ==null ? "" :req.getParameter("edit_dob");
			String doj 		= req.getParameter("edit_doj") 	    ==null ? "" :req.getParameter("edit_doj");
			String email 	= req.getParameter("edit_email")    ==null ? "" :req.getParameter("edit_email");
			String usertype = req.getParameter("edit_usertype") ==null ? "2" :req.getParameter("edit_usertype");
			String user_id = req.getParameter("edit_userid") ==null ? "" :req.getParameter("edit_userid");
			
			
			errorMsg ="";
			 if(username.equalsIgnoreCase("")){
				errorMsg ="Please Enter Username.";
			}
			 
			if(!errorMsg.equalsIgnoreCase("")){
				model.addObject("errorMsg",errorMsg);
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.setViewName("home/editUserDetails");
				return model;
			}
			
			errorMsg ="";
			if(user_id.equalsIgnoreCase("")){
				errorMsg ="Data is not sufficient for processing your request.";
			}
			if(!errorMsg.equalsIgnoreCase("")){
				model.addObject("errorMsg",errorMsg);
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.setViewName("home/editUserDetails");
				return model;
			}
			
			StringBuilder sql = new StringBuilder("UPDATE user_login SET username = ? ,usertype_id = ? ");
			sql.append(", fname = ?, lname = ?, address = ?, phone = ?, birthdate = ?, email = ?, doj = ? ");
			if(!password.equalsIgnoreCase("")){
				sql.append(",user_password = password('"+password+"')");
			}
			
			sql.append(" where id = ? and delete_flag =0");
			
			Object[]params = {username,usertype,fname,lname,address,phone,dob,email,doj,user_id};
			int affectedRows  = qrun.update(sql.toString(), params);
			
			if(affectedRows>0){
				model.addObject("errorClass",GlobalVariables.successClass);
				errorMsg ="User Details Updated  Successfully.";
				model.addObject("errorMsg",errorMsg);
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				errorMsg ="There is no user found having user id = "+user_id;
				model.addObject("errorMsg",errorMsg);
			}	
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			errorMsg ="Error occured while updating User Details";
			model.addObject("errorMsg",errorMsg);
			model.setViewName("home/editUserDetails");
			LogUtility.logError(e.getMessage()+"AdminService :: updateUserDetails() End", logger);
			
		}
		model.setViewName("home/editUserDetails");
		LogUtility.logInfo("AdminService :: updateUserDetails() End", logger);
		return model;
	}

	public ModelAndView viewUserDetails(HttpServletRequest req,	HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: viewUserDetails() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String user_id = req.getParameter("user_id") ==null ? "" :req.getParameter("user_id");
			errorMsg ="";
			if(user_id.equalsIgnoreCase("")){
				errorMsg ="Data is not sufficient for processing your request.";
			}
			if(!errorMsg.equalsIgnoreCase("")){
				model.addObject("errorMsg",errorMsg);
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.setViewName("home/viewUserDetails");
				return model;
			}
			query ="select id, username,if(usertype_id=1,'Admin User','Staff User') as usertype, fname, lname, address, phone, birthdate, email, doj  from user_login where id = ? and delete_flag =0";
			Map<String,Object> userDetailMap = (Map<String,Object>) qrun.query(query, new MapHandler(),user_id);
			if(userDetailMap!=null && userDetailMap.size()>0){
					model.addObject("userMap", userDetailMap);
					model.addObject("option" , "viewUserDetails");
					
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				errorMsg ="There is no user found having user id = "+user_id;
				model.addObject("errorMsg",errorMsg);
			}	
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			errorMsg ="Error occured while fetching User Details";
			model.addObject("errorMsg",errorMsg);
			model.setViewName("home/viewUserDetails");
			LogUtility.logError(e.getMessage()+"AdminService :: viewUserDetails() End", logger);
			
		}
		model.setViewName("home/viewUserDetails");
		LogUtility.logInfo("AdminService :: viewUserDetails() End", logger);
		return model;	
			
	}

	public ModelAndView deleteUserDetails(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: deleteUserDetails() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String user_id = req.getParameter("user_id") ==null ? "" :req.getParameter("user_id");
			errorMsg ="";
			if(user_id.equalsIgnoreCase("")){
				errorMsg ="Data is not sufficient for processing your request.";
			}
			if(!errorMsg.equalsIgnoreCase("")){
				model.addObject("errorMsg",errorMsg);
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.setViewName("home/viewUserDetails");
				return model;
			}
			query ="Update user_login set delete_flag = 1 where id  = ? and delete_flag =0";
			int affectedRows = qrun.update(query,user_id);
			if(affectedRows>0){
					model.addObject("errorClass",GlobalVariables.successClass);
					errorMsg ="User Deleted Successfully.";
					model.addObject("errorMsg",errorMsg);
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				errorMsg ="Unable to delete user id : "+user_id+".";
				model.addObject("errorMsg",errorMsg);
			}	
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			errorMsg ="Error occured while deleting User Details";
			model.addObject("errorMsg",errorMsg);
			model.setViewName("home/viewUserDetails");
			LogUtility.logError(e.getMessage()+"AdminService :: deleteUserDetails() End", logger);
			
		}
		model.setViewName("home/viewUserDetails");
		LogUtility.logInfo("AdminService :: deleteUserDetails() End", logger);
		return model;	
		
	}

	public ModelAndView adminLogout(HttpServletRequest req,	HttpServletResponse res) {
		LogUtility.logInfo("Enter class AdminService :adminLogout login() ",logger);
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
			model.setViewName("home/adminpanel");
			LogUtility.logInfo("Exit AdminService class :end adminLogout() ",logger);
			return model;
		}catch(Exception e){
			ModelAndView model=new ModelAndView();
			model.addObject("errorMsg","Session ending Problem");
			model.setViewName("home/adminpanel");
			LogUtility.logError(e.getMessage()+"Exit AdminService class :end adminLogout() ",logger);
			e.printStackTrace();
			return model; 
		}
	}
	
	public ModelAndView showWorkDoneByUser(HttpServletRequest req,	HttpServletResponse res){
		LogUtility.logInfo("Enter class AdminService :showWorkDoneByUser() ",logger);
		ModelAndView model=new ModelAndView();
		try{
			String user_id	 = req.getParameter("user_id") 	 ==null ? "" :req.getParameter("user_id");
			String startDate = req.getParameter("startDate") ==null ? "" :req.getParameter("startDate");
			String endDate   = req.getParameter("endDate")   ==null ? "" :req.getParameter("endDate");
			errorMsg ="";
			if(user_id.equalsIgnoreCase("")){
				errorMsg ="Data is not sufficient for processing your request.";
			}
			if(!errorMsg.equalsIgnoreCase("")){
				model.addObject("errorMsg",errorMsg);
				model.addObject("errorClass",GlobalVariables.errorClass);
				LogUtility.logInfo("Exit AdminService class :end showWorkDoneByUser() ",logger);
				return model;
			}
		
			query  =" SELECT ud.userdetails_id, ud.name, ud.address, ud.city, ud.phone,pd.modified_datetime, "+  
					" ud.email,pd.policy_no,pd.status,pd.amount,pd.exp_date from userdetails ud ,policydetails pd "+
					" where ud.userdetails_id = pd.userdetails_id and pd.status in('pending') "+
					" and ud.delete_flag = 0 and pd.user_id =? ";
			if(!startDate.equals("") && !endDate.equals("")){
				query +="AND UNIX_TIMESTAMP(substring(pd.modified_datetime,1,10)) >= UNIX_TIMESTAMP('"+startDate+"') AND "; 
				query +="UNIX_TIMESTAMP(substring(pd.modified_datetime,1,10)) <= UNIX_TIMESTAMP('"+endDate+"') order by substring(pd.modified_datetime,1,10) ";
			}
			
//			System.out.println("Query ::"+query);
//			System.out.println("user_id ::"+user_id);
			List<Map<String,Object>>listUserWork =qrun.query(query,new MapListHandler(),user_id);
			model.addObject("listUserWorked", listUserWork);
			model.addObject("totalEntry", listUserWork.size());
			LogUtility.logInfo("Exit AdminService class :end showWorkDoneByUser() ",logger);
			return model;
		}catch(Exception e){
			model.addObject("errorMsg","Error occured while fetching Work Done By User");
			LogUtility.logError(e.getMessage()+"Exit AdminService class :end showWorkDoneByUser() ",logger);
			e.printStackTrace();
			return model; 
		}
		
	}
	

	

}
