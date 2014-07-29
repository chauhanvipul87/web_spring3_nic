/**
 * 
 */
package nic.services;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import nic.dao.CustomerDao;
import nic.utility.CommonDAO;
import nic.utility.DateTimeFormater;
import nic.utility.GlobalVariables;
import nic.utility.LogUtility;
import nic.utility.db.DbUtils;

/**
 * @author vipul
 *
 */
public class PolicyServices {

	Logger logger = Logger.getLogger(this.getClass());
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String query ="";
	String errorMsg ="";
	
	public ModelAndView addNewPolicy(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("PolicyServices :: addNewPolicy() Start", logger);
		ModelAndView model = new ModelAndView();
		int userdetails_id = new CommonDAO().getautoGenerateID("userdetails","userdetails_id");
		try{
			
			String policy_type = req.getParameter("policytype")     ==null ? "1" :req.getParameter("policytype");
			String name		   = req.getParameter("name")           ==null ? "" :req.getParameter("name");
			String email	   = req.getParameter("email")          ==null ? "" :req.getParameter("email");
			String address	   = req.getParameter("address")        ==null ? "" :req.getParameter("address");
			String city		   = req.getParameter("city")     		==null ? "" :req.getParameter("city");
			String phone	   = req.getParameter("phone")     		==null ? "" :req.getParameter("phone");
			String vehicleno   = req.getParameter("vehicleno")      ==null ? "" :req.getParameter("vehicleno");
			
			String expdate     = req.getParameter("expdate")     	==null ? "" :req.getParameter("expdate");
			String amount     = req.getParameter("amount")     		==null ? "" :req.getParameter("amount");
			String policyno	   = req.getParameter("policyno")       ==null ? "" :req.getParameter("policyno");
			
			 errorMsg ="";
			 int affectedRows =0;
			 if(name.equals("")){
				 	model.addObject("errorClass", GlobalVariables.errorClass);
				 	model.addObject("errorMsg","Please send request with proper parameters to proceed further");
					LogUtility.logInfo("CustomerService :: addNewPolicy() Method Ends:::",logger);
					return model;
			}
				Map<String,Object>policyDetails = new CustomerDao().isPolityExits(policyno);
				if(policyDetails !=null && !policyDetails.isEmpty()){
					model.addObject("errorClass", GlobalVariables.errorClass);
				 	model.addObject("errorMsg","Policy no :"+policyno+" has already been existed.Please enter new policy no.");
					LogUtility.logInfo("CustomerService :: addNewPolicy() Method Ends:::",logger);
					return model;
			}
			if(policy_type.equalsIgnoreCase("4")){
				// vehicle policy..
				StringBuilder sql =new StringBuilder("INSERT INTO userdetails (userdetails_id, name, address, city, phone, email, " +
						" policy_type, vehicle_no,modified_datetime) ");
				sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
				affectedRows = qrun.update(sql.toString(),new Object[]{userdetails_id,name,address,city,phone,email,policy_type,vehicleno,DateTimeFormater.format_datetime()});
			}else{
				// other policy
				StringBuilder sql =new StringBuilder("INSERT INTO userdetails (userdetails_id, name, address, city, phone, email, " +
						" policy_type,modified_datetime) ");
				sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
				affectedRows = qrun.update(sql.toString(),new Object[]{userdetails_id,name,address,city,phone,email,policy_type,DateTimeFormater.format_datetime()});
			}
			if(affectedRows >0){
				
				HttpSession session = req.getSession();
				String user_id = session.getAttribute("user_id").toString(); 
				
				StringBuilder sql = new StringBuilder("INSERT INTO policydetails (policy_no, amount, exp_date, old_policy, status, userdetails_id, user_id, ");
				sql.append (" modified_datetime) VALUES "); 
				sql.append (" (?, ?, ?, ?, ?, ?, ?, ?);");
				
				affectedRows = qrun.update(sql.toString(),new Object[]{policyno,amount,expdate,policyno,GlobalVariables.pendingStatus,userdetails_id,user_id,DateTimeFormater.format_datetime()});
				if(affectedRows >0){
					model.addObject("errorClass",GlobalVariables.successClass);
					model.addObject("errorMsg","New policy added Successfully.");
				}else{
					model.addObject("errorClass",GlobalVariables.errorClass);
					model.addObject("errorMsg","Error occured while adding new poicy details.");
				}
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.addObject("errorMsg","Error occured while adding new poicy details.");
			}
			new CommonDAO().setautoGenerateID("userdetails","userdetails_id",(userdetails_id+1));
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("errorMsg","Error occured while adding new poicy details.");
			LogUtility.logError(e.getMessage()+"PolicyServices :: addNewPolicy() End", logger);
			new CommonDAO().setautoGenerateID("userdetails","userdetails_id",(userdetails_id+1));
			
		}
		LogUtility.logInfo("PolicyServices :: addNewPolicy() End", logger);
		return model;
	}

	public ModelAndView searchPolicyDetails(HttpServletRequest req,	HttpServletResponse res) {
		LogUtility.logInfo("PolicyServices :: searchPolicyDetails() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String policy_no = req.getParameter("filter_policy_no") ==null ? "" :req.getParameter("filter_policy_no").trim();
			model.addObject("policy_no",policy_no);
			
			Map<String,Object>policyDetails = new CustomerDao().isPolityExits(policy_no);
			if(policyDetails!=null && !policyDetails.isEmpty()){
				List<Map<String, Object>>userDetailsList =	new CustomerDao().getCustomerDetailsById(policyDetails.get("userdetails_id").toString());
				if(userDetailsList.size()<=0){
					 model.addObject("errorClass", GlobalVariables.errorClass);
					 model.addObject("errorMsg","Error occured while getting Policy Details.");
					 LogUtility.logInfo("PolicyServices :: searchPolicyDetails() Method Ends:::",logger);
					 return model;
				}else{
					model.addObject("userDetailsList",userDetailsList);
					model.addObject("option","showResult");
					model.addObject("userdetails_id",policyDetails.get("userdetails_id").toString());
					model.addObject("policy_type",policyDetails.get("policy_type").toString());
					model.addObject("old_policy",policyDetails.get("old_policy").toString());
					model.addObject("status",policyDetails.get("status").toString());
				}
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.addObject("errorMsg","No Record Found.Please try again.");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("errorMsg","Error occured while adding new poicy details.");
			LogUtility.logError(e.getMessage()+"PolicyServices :: searchPolicyDetails() End", logger);
			
		}
		LogUtility.logInfo("PolicyServices :: searchPolicyDetails() End", logger);
		return model;
	}

	public ModelAndView addRenewPolicyDetails(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("PolicyServices :: addRenewPolicyDetails() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String renew_policyno	 = req.getParameter("renew_policyno") 	==null ? "" :req.getParameter("renew_policyno").trim();
			String renew_expirydate  = req.getParameter("renew_expirydate") ==null ? "" :req.getParameter("renew_expirydate").trim();
			String renew_amount	 	 = req.getParameter("renew_amount") 	==null ? "" :req.getParameter("renew_amount").trim();
			String policy_type	 	 = req.getParameter("policy_type") 		==null ? "" :req.getParameter("policy_type").trim();
			String userdetails_id	 = req.getParameter("userdetails_id") 	==null ? "" :req.getParameter("userdetails_id").trim();
			String old_policy		 = req.getParameter("old_policy") 		==null ? "" :req.getParameter("old_policy").trim();
			String policy_no		 = req.getParameter("policy_no") 		==null ? "" :req.getParameter("policy_no").trim(); //existing policy no to renew
			
			HttpSession session = req.getSession();
			String user_id = session.getAttribute("user_id").toString(); 
			
			query="INSERT INTO policydetails (policy_no, amount, exp_date, old_policy, status, userdetails_id, user_id,modified_datetime) "+ 
				  " VALUES (?, ?, ?, ?, ?, ?, ?,?);";
			int affectedRows = qrun.update(query,new Object[]{renew_policyno,renew_amount,renew_expirydate,old_policy,GlobalVariables.pendingStatus,
					userdetails_id,user_id,DateTimeFormater.format_datetime()});
			if(affectedRows >0){
				
				query ="update policydetails set status = ? where policy_no = ? and userdetails_id =? ";
				affectedRows = qrun.update(query,new Object[]{GlobalVariables.renewStatus,policy_no,userdetails_id});
				if(affectedRows >0){
					model.addObject("errorClass",GlobalVariables.successClass);
					model.addObject("errorMsg","Process for Renew policy completed successfully.");
				}else{
					model.addObject("errorClass",GlobalVariables.errorClass);
					model.addObject("errorMsg","something went wrong while renewing policy.");
				}
				
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.addObject("errorMsg","something went wrong while inserting new record for renewing policy.");
			}

		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("errorMsg","Error occured while adding renewing policy.");
			LogUtility.logError(e.getMessage()+"PolicyServices :: addRenewPolicyDetails() End", logger);
			
		}
		LogUtility.logInfo("PolicyServices :: addRenewPolicyDetails() End", logger);
		return model;
	}

	public ModelAndView getReminderPolicyList(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("PolicyServices :: getReminderPolicyList() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
		
			  Calendar cal = Calendar.getInstance();
			  String currentMonthStartDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+getValidDay(cal.get(Calendar.DATE));
			  System.out.println("currentMonthStartDate ::"+currentMonthStartDate);

			  String currentMonthEndDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+getValidDay(cal.getActualMaximum(Calendar.DATE));
			  System.out.println("currentMonthEndDate ::"+currentMonthEndDate);
			  cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
			 
			  String nextMonthStartDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+getValidDay(cal.getActualMinimum(Calendar.DATE));
			  System.out.println("nextMonthStartDate ::"+nextMonthStartDate);
			  
			  String nextMonthEndDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+getValidDay(cal.getActualMaximum(Calendar.DATE));
			  System.out.println("nextMonthEndDate ::"+nextMonthEndDate);
			
			  query ="select  pt.policy_type,ud.userdetails_id, ud.name, ud.address, ud.city, ud.phone, ud.email,pd.policy_no,pd.status," +
			  		" pd.amount,pd.exp_date from userdetails ud left join policydetails pd on ud.userdetails_id = pd.userdetails_id "+ 
			  		" inner join policytype pt on ud.policy_type = pt.id where pd.status in('"+GlobalVariables.pendingStatus+"') and ud.delete_flag = 0 " +
			  		" and UNIX_TIMESTAMP(pd.exp_date) >= UNIX_TIMESTAMP('"+currentMonthStartDate+"') AND "+
			  		" UNIX_TIMESTAMP(pd.exp_date)<= UNIX_TIMESTAMP('"+currentMonthEndDate+"') order by pd.exp_date ";
			List<Map<String,Object>> currentMonthPolicyList = qrun.query(query, new MapListHandler());
			model.addObject("currentMonthPolicyList",currentMonthPolicyList);
			
			query ="select  pt.policy_type,ud.userdetails_id, ud.name, ud.address, ud.city, ud.phone, ud.email,pd.policy_no,pd.status," +
				  		" pd.amount,pd.exp_date from userdetails ud left join policydetails pd on ud.userdetails_id = pd.userdetails_id "+ 
				  		" inner join policytype pt on ud.policy_type = pt.id where pd.status in('"+GlobalVariables.pendingStatus+"') and ud.delete_flag = 0 " +
				  		" and UNIX_TIMESTAMP(pd.exp_date) >= UNIX_TIMESTAMP('"+nextMonthStartDate+"') AND "+
				  		" UNIX_TIMESTAMP(pd.exp_date)<= UNIX_TIMESTAMP('"+nextMonthEndDate+"') order by pd.exp_date";
			 List<Map<String,Object>> nextMonthPolicyList = qrun.query(query, new MapListHandler());
			 model.addObject("nextMonthPolicyList",nextMonthPolicyList);
				
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("errorMsg","Error occured while preparing reminder policy list.");
			LogUtility.logError(e.getMessage()+"PolicyServices :: getReminderPolicyList() End", logger);
			
		}
		LogUtility.logInfo("PolicyServices :: getReminderPolicyList() End", logger);
		return model;
	}
	
	private String getValidDay(int day){
		String validDay =String.valueOf(day);
		if(day <=9){
			validDay = "0"+String.valueOf(day);
		}
		return  validDay;
	}

}
