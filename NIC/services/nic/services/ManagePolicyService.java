package nic.services;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import nic.dao.CustomerDao;
import nic.utility.GlobalVariables;
import nic.utility.LogUtility;
import nic.utility.db.DbUtils;
import nic.utility.file.FileManagement;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public class ManagePolicyService {

	Logger logger = Logger.getLogger(this.getClass());
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String query ="";
	String errorMsg ="";
	
	public ModelAndView listCustomer(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("ManagePolicyService :: listCustomer() Method Start", logger);
		ModelAndView model =new ModelAndView();
		String cpage=(req.getParameter("current_page") ==null || req.getParameter("current_page").equalsIgnoreCase("undefined"))? String.valueOf(GlobalVariables.currentPage):req.getParameter("current_page");
		String record=(req.getParameter("record") ==null || req.getParameter("record").equalsIgnoreCase("undefined"))? String.valueOf(GlobalVariables.record):req.getParameter("record");
		
		if(cpage == null || record == null){
			if(cpage == null) cpage=String.valueOf(GlobalVariables.currentPage);
			if(record == null) record=String.valueOf(GlobalVariables.record);
		}
		
		if(cpage.equals("0")){
			cpage = "1";
		}
		int data= (Integer.parseInt(cpage)-1)*Integer.parseInt(record);
//		System.out.println("cpage "+cpage);
//		System.out.println("record "+record);
//		System.out.println("page "+cpage+ "record "+record);
	
		//filter_policyno=&filter_expdate=&filter_amount=
		
		String filter_name	 	 =req.getParameter("filter_name")==null ? "" : req.getParameter("filter_name").trim();
		String filter_address	 =req.getParameter("filter_address")==null ? "" : req.getParameter("filter_address").trim();
		String filter_city		 =req.getParameter("filter_city")==null ? "" : req.getParameter("filter_city").trim();
		String filter_phone	 	 =req.getParameter("filter_phone")==null ? "" : req.getParameter("filter_phone").trim();
		String filter_email		 =req.getParameter("filter_email")==null ? "" : req.getParameter("filter_email").trim();
		String filter_policyno	 =req.getParameter("filter_policyno")==null ? "" : req.getParameter("filter_policyno").trim();
		String filter_expdate	 =req.getParameter("filter_expdate")==null ? "" : req.getParameter("filter_expdate").trim();
		String filter_amount	 =req.getParameter("filter_amount")==null ? "" : req.getParameter("filter_amount").trim();
		String filter_status	 =(req.getParameter("filter_status")==null || req.getParameter("filter_status").equals("0")) ? "" : req.getParameter("filter_status").trim();
		String filter_policytype =(req.getParameter("filter_policytype")==null || req.getParameter("filter_policytype").equals("0")) ? "" : req.getParameter("filter_policytype").trim();
		String filter_vehicleno	 =req.getParameter("filter_vehicleno")==null ? "" : req.getParameter("filter_vehicleno").trim();
		
		StringBuilder sql =new StringBuilder("select count(ud.id)as cnt from userdetails ud  left join policydetails pd on ud.userdetails_id = pd.userdetails_id where");
		sql.append(" ud.delete_flag = 0 ");
		int flag =0;
		
		if(!filter_name.equals("")){
			flag =1;
			sql.append(" and ud.name like '"+filter_name+"%'");
		}
		if(!filter_address.equals("")){
			flag =1;
			sql.append(" and ud.address like '"+filter_address+"%'");
		}
		if(!filter_city.equals("")){
			flag =1;
			sql.append(" and ud.city like '"+filter_city+"%'");
		}
		if(!filter_phone.equals("")){
			flag =1;
			sql.append(" and ud.phone like '"+filter_phone+"'");
		}
		if(!filter_email.equals("")){
			flag =1;
			sql.append(" and ud.email like '"+filter_email+"%'");
		}
		if(!filter_policytype.equals("")){
			flag =1;
			sql.append(" and ud.policy_type = '"+filter_policytype+"'");
		}
		if(!filter_vehicleno.equals("")){
			flag =1;
			sql.append(" and ud.vehicle_no like '"+filter_vehicleno+"%'");
		}
		if(!filter_policyno.equals("")){
			flag =1;
			sql.append(" and pd.policy_no like '"+filter_policyno+"%'");
		}
		if(!filter_expdate.equals("")){
			flag =1;
			sql.append(" and pd.exp_date like '"+filter_expdate+"'");
		}
		if(!filter_amount.equals("")){
			flag =1;
			sql.append(" and pd.amount = '"+filter_amount+"'");
		}
		if(!filter_status.equals("")){
			flag =1;
			
			if(filter_status.equalsIgnoreCase("pending")){
				
				sql.append(" and pd.status in('pending') ");
			}else{
				sql.append(" and pd.status is null ");
			}
		}else{
			if(flag ==0){
				sql.append(" and pd.status in('pending') or pd.status is null ");
			}
		}
		System.out.println("1 : "+sql.toString());
		try{
			long cnt= (Long) qrun.query(sql.toString(),new ScalarHandler());
			//System.out.println("total records "+cnt);
			long totalPage = 0;
			if(cnt > 0 && cnt < Integer.parseInt(record)){
				totalPage = 1;
			}else if(cnt % Integer.parseInt(record)==0){
				totalPage = cnt/Integer.parseInt(record);
			}else{
				totalPage = cnt/Integer.parseInt(record);
				totalPage += 1;
			}
					
			if(totalPage == 1){
				cpage = "1";
			}
			
		/*	query="select  ud.userdetails_id, ud.name, ud.address, ud.city, ud.phone, ud.email,pd.policy_no,pd.status,pd.amount,pd.exp_date from userdetails ud "+ 
				  " left join policydetails pd on ud.userdetails_id = pd.userdetails_id "+
				  " where pd.status in('pending') or pd.status is null and ud.delete_flag = 0 ";*/

			sql =new StringBuilder("select ud.userdetails_id, ud.name, ud.address, ud.city, ud.phone, ud.email,ud.policy_type,ud.vehicle_no,pd.policy_no,pd.status,pd.amount,pd.exp_date from userdetails ud  left join policydetails pd on ud.userdetails_id = pd.userdetails_id where");
			sql.append(" ud.delete_flag = 0 ");
			
					
			if(!filter_name.equals("")){
				flag =1;
				sql.append(" and ud.name like '"+filter_name+"%'");
			}
			if(!filter_address.equals("")){
				flag =1;
				sql.append(" and ud.address like '"+filter_address+"%'");
			}
			if(!filter_city.equals("")){
				flag =1;
				sql.append(" and ud.city like '"+filter_city+"%'");
			}
			if(!filter_phone.equals("")){
				flag =1;
				sql.append(" and ud.phone like '"+filter_phone+"'");
			}
			if(!filter_email.equals("")){
				flag =1;
				sql.append(" and ud.email like '"+filter_email+"%'");
			}
			if(!filter_policytype.equals("")){
				flag =1;
				sql.append(" and ud.policy_type = '"+filter_policytype+"'");
			}
			if(!filter_vehicleno.equals("")){
				flag =1;
				sql.append(" and ud.vehicle_no like '"+filter_vehicleno+"%'");
			}
			if(!filter_policyno.equals("")){
				flag =1;
				sql.append(" and pd.policy_no like '"+filter_policyno+"%'");
			}
			if(!filter_expdate.equals("")){
				flag =1;
				sql.append(" and pd.exp_date like '"+filter_expdate+"'");
			}
			if(!filter_amount.equals("")){
				flag =1;
				sql.append(" and pd.amount = '"+filter_amount+"'");
			}
			if(!filter_status.equals("")){
				flag =1;
				
				if(filter_status.equalsIgnoreCase("pending")){
					sql.append(" and pd.status in('pending') ");
				}else{
					sql.append(" and pd.status is null ");
				}
			}else{
				if(flag ==0){
					sql.append(" and pd.status in('pending') or pd.status is null ");
				}
			}
			
			sql.append(" limit ?,"+record);
			
			int rec=Integer.parseInt(record);
			System.out.println("2 : "+sql.toString());
			List<Map<String,Object>> list=qrun.query(sql.toString(),new MapListHandler(),data);
			System.out.println("data limit ::"+data);
			//System.out.println("query :: "+query+"data:: "+data);
			if(list == null || list.size() == 0){
				data = 0;
				list=qrun.query(sql.toString(),new MapListHandler(),data);
				cpage = "1";
			}
			
			
		   int initno = (Integer.parseInt(cpage)-1)*Integer.parseInt(record);
		   initno+=1;
		   int uptono = Integer.parseInt(cpage) * Integer.parseInt(record);
		   
		    model.addObject("initno",initno);
		    model.addObject("uptono",uptono);
			model.addObject("dataList",list);
			model.addObject("currentPage", cpage);
			model.addObject("record", record);
			model.addObject("totalPage", totalPage);
			
			LogUtility.logInfo("ManagePolicyService :: listCustomer() Method Ends:::",logger);
			return model;
		}catch(Exception e){
			e.getMessage();
			model.addObject("errorClass", GlobalVariables.errorClass);
			 model.addObject("errorMsg","Something went wrong while fetching Customer list");
			 LogUtility.logError("ManagePolicyService :: listCustomer() Method Ends:::"+e.getMessage(),logger);
			return model;
		}
	}

	public ModelAndView viewCustomerDetail(HttpServletRequest req,	HttpServletResponse res) {
		LogUtility.logInfo("ManagePolicyService :: viewCustomerDetail() Method Start", logger);
		ModelAndView model =new ModelAndView();
		try{	
			
			String userdetails_id	 	 =req.getParameter("userdetails_id")==null ? "" : req.getParameter("userdetails_id").trim();
			if(userdetails_id.equals("")){
				 model.addObject("errorClass", GlobalVariables.errorClass);
				 model.addObject("errorMsg","Please enter valid parameters to proceed further");
				 LogUtility.logInfo("ManagePolicyService :: viewCustomerDetail() Method Ends:::",logger);
				 return model;
			}
			List<Map<String, Object>>userDetailsList =	new CustomerDao().getCustomerDetailsById(userdetails_id);
			if(userDetailsList.size()<=0){
				 model.addObject("errorClass", GlobalVariables.errorClass);
				 model.addObject("errorMsg","Error occured while getting Customer Details.");
				 LogUtility.logInfo("ManagePolicyService :: viewCustomerDetail() Method Ends:::",logger);
				 return model;
			}else{
				model.addObject("userDetailsList", userDetailsList);
			}
		}catch(Exception e){
			 e.getMessage();
			 model.addObject("errorClass", GlobalVariables.errorClass);
			 model.addObject("errorMsg","Something went wrong while fetching Customer Details");
			 LogUtility.logError("ManagePolicyService :: viewCustomerDetail() Method Ends:::"+e.getMessage(),logger);
			return model;
		}
		LogUtility.logInfo("ManagePolicyService :: viewCustomerDetail() Method Ends:::",logger);
		return model;
	}
	
	public ModelAndView editCustomerDetail(HttpServletRequest req,	HttpServletResponse res) {
		LogUtility.logInfo("ManagePolicyService :: editCustomerDetail() Method Start", logger);
		ModelAndView model =new ModelAndView();
		try{	
			
			String userdetails_id	 	 =req.getParameter("userdetails_id")==null ? "" : req.getParameter("userdetails_id").trim();
			String policy_no	 		 =req.getParameter("policy_no")		==null ? "" : req.getParameter("policy_no").trim();
			if(userdetails_id.equals("") || policy_no.equals("")){
				 	model.addObject("errorClass", GlobalVariables.errorClass);
				 	model.addObject("errorMsg","Please enter valid parameters to proceed further");
					LogUtility.logInfo("ManagePolicyService :: editCustomerDetail() Method Ends:::",logger);
					return model;
			}
			
			if(new CustomerDao().getCustomerDetailsById(userdetails_id).size()<=0){
				 model.addObject("errorClass", GlobalVariables.errorClass);
				 model.addObject("errorMsg","Error occured while getting Customer Details.");
				 LogUtility.logInfo("ManagePolicyService :: editCustomerDetail() Method Ends:::",logger);
				 return model;
			}else{
				model.addObject("editUserDetailList", new CustomerDao().getEditCustomerDetailsById(userdetails_id,policy_no));
				model.addObject("policyList", new CustomerDao().getPolicyTypeList());
			}
			
		}catch(Exception e){
			 e.getMessage();
			 model.addObject("errorClass", GlobalVariables.errorClass);
			 model.addObject("errorMsg","Something went wrong while fetching Customer Details for editing");
			 LogUtility.logError("ManagePolicyService :: editCustomerDetail() Method Ends:::"+e.getMessage(),logger);
			return model;
		}
		LogUtility.logInfo("ManagePolicyService :: editCustomerDetail() Method Ends:::",logger);
		return model;
	}
	
	public ModelAndView updateCustomerDetail(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("ManagePolicyService :: updateCustomerDetail() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String userdetails_id = req.getParameter("edit_userdetails_id")     ==null ? "" :req.getParameter("edit_userdetails_id");
			
			String policy_type  = req.getParameter("edit_policytype") ==null ? "" :req.getParameter("edit_policytype");
			String name		 	= req.getParameter("edit_name")       ==null ? "" :req.getParameter("edit_name");
			String email 		= req.getParameter("edit_email")      ==null ? "" :req.getParameter("edit_email");
			String address 		= req.getParameter("edit_address")    ==null ? "" :req.getParameter("edit_address");	
			String city 		= req.getParameter("edit_city")       ==null ? "" :req.getParameter("edit_city");
			String phone 		= req.getParameter("edit_phone")      ==null ? "" :req.getParameter("edit_phone");
			
			String policy_no    =req.getParameter("edit_policyno")	  ==null ? "" : req.getParameter("edit_policyno").trim();
			String expdate      = req.getParameter("edit_expdate")    ==null ? "" :req.getParameter("edit_expdate");
			String amount 	    = req.getParameter("edit_amount")     ==null ? "" :req.getParameter("edit_amount");
			String vehicleno    = req.getParameter("edit_vehicleno")  ==null ? "" :req.getParameter("edit_vehicleno");

			errorMsg ="";
			 if(policy_no.equalsIgnoreCase("")){
				 errorMsg ="Please Enter Policy No.";
			}
			if(!errorMsg.equalsIgnoreCase("")){
				model.addObject("errorMsg",errorMsg);
				model.addObject("errorClass",GlobalVariables.errorClass);
				LogUtility.logInfo("ManagePolicyService :: updateCustomerDetail() End", logger);
				return model;
			}
			
			StringBuilder sql = new StringBuilder("UPDATE userdetails SET name = ? ,address = ? , email = ? , city = ?, phone = ?,policy_type = ? ");
			if(!vehicleno.equals("")){
				sql.append(" ,vehicle_no = '"+vehicleno+"'");
			}
			sql.append(" where userdetails_id = ? and delete_flag =0");
			Object[]params = {name,address,email,city,phone,policy_type,userdetails_id};
			int affectedRows  = qrun.update(sql.toString(), params);
			
			if(affectedRows>0){
				model.addObject("errorClass",GlobalVariables.successClass);
				errorMsg ="Customer Details Updated  Successfully.";
				model.addObject("errorMsg",errorMsg);
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				errorMsg ="Error occured while updating User Details";
				model.addObject("errorMsg",errorMsg);
			}	
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			errorMsg ="Error occured while updating User Details";
			model.addObject("errorMsg",errorMsg);
			LogUtility.logError(e.getMessage()+"ManagePolicyService :: updateCustomerDetail() End", logger);
			
		}
		LogUtility.logInfo("ManagePolicyService :: updateCustomerDetail() End", logger);
		return model;
	}

	public ModelAndView deleteCustomerDetails(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("ManagePolicyService :: deleteCustomerDetails() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String userdetails_id = req.getParameter("userdetails_id")     ==null ? "" :req.getParameter("userdetails_id");
			
			 errorMsg ="";
			 if(userdetails_id.equals("")){
				 	model.addObject("errorClass", GlobalVariables.errorClass);
				 	model.addObject("errorMsg","Please enter valid parameters to proceed further");
					LogUtility.logInfo("ManagePolicyService :: deleteCustomerDetails() Method Ends:::",logger);
					return model;
			}
			
			StringBuilder sql = new StringBuilder("UPDATE userdetails SET delete_flag = 1 where userdetails_id = ? and delete_flag =0");
			Object[]params = {userdetails_id};
			int affectedRows  = qrun.update(sql.toString(), params);

			if(affectedRows>0){
				
				sql = new StringBuilder("UPDATE policydetails SET delete_flag = 1 where userdetails_id = ? and delete_flag =0");
				Object[]param = {userdetails_id};
				affectedRows  = qrun.update(sql.toString(), param);
				if(affectedRows>0){
					model.addObject("errorClass",GlobalVariables.successClass);
					model.addObject("errorMsg","Customer Details deleted Successfully.");
				}else{
					model.addObject("errorClass",GlobalVariables.errorClass);
					model.addObject("errorMsg","Error occured while deleting User Details");
				}
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.addObject("errorMsg","Error occured while deleting User Details");
			}	
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("errorMsg","Error occured while deleting User Details");
			LogUtility.logError(e.getMessage()+"AdminService :: deleteCustomerDetails() End", logger);
			
		}
		LogUtility.logInfo("ManagePolicyService :: deleteCustomerDetails() End", logger);
		return model;
		
	}

	public ModelAndView userProfile(HttpServletRequest req,	HttpServletResponse res) {
		LogUtility.logInfo("ManagePolicyService :: userProfile() Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String user_id = req.getSession().getAttribute("user_id").toString();
			errorMsg ="";
			if(user_id.equalsIgnoreCase("")){
				errorMsg ="Data is not sufficient for processing your request.";
			}
			if(!errorMsg.equalsIgnoreCase("")){
				model.addObject("errorMsg",errorMsg);
				model.addObject("errorClass",GlobalVariables.errorClass);
				return model;
			}
			query ="select id, username,if(usertype_id=1,'Admin User','Staff User') as usertype, fname, lname, address, phone, birthdate, email, doj  from user_login where id = ? and delete_flag =0";
			Map<String,Object> userDetailMap = (Map<String,Object>) qrun.query(query, new MapHandler(),user_id);
			if(userDetailMap!=null && userDetailMap.size()>0){
					model.addObject("userMap", userDetailMap);
					
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				errorMsg ="There is no user found having user id = "+user_id;
				model.addObject("errorMsg",errorMsg);
			}	
			
		}catch(Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("errorMsg","Error occured while getting User Details");
			LogUtility.logError(e.getMessage()+"ManagePolicyService :: userProfile() End", logger);
			
		}
		LogUtility.logInfo("ManagePolicyService :: userProfile() End", logger);
		return model;	
	}

	public StringBuffer exportFile(HttpServletRequest req,	HttpServletResponse res) {
		LogUtility.logInfo("ManagePolicyService :: exportFile() Start", logger);
		String policy_ids=req.getParameter("ids");
		System.out.println("Before policy_ids : "+policy_ids);
		policy_ids=policy_ids.substring(1);
		System.out.println("policy_ids : "+policy_ids);
		StringBuffer policyResult=null;
		String exporttype=req.getParameter("export_type");
		if(exporttype.equalsIgnoreCase("csv")){
				List<Map<String, Object>> customerList = new ArrayList<Map<String, Object>>();
				try{
					
					StringBuilder sql =new StringBuilder("select ud.userdetails_id, ud.name, ud.address, ud.city, ud.phone, ud.email,ud.policy_type,ud.vehicle_no,pd.policy_no,pd.status,pd.amount,pd.exp_date from userdetails ud  left join policydetails pd on ud.userdetails_id = pd.userdetails_id where");
					sql.append(" ud.delete_flag = 0 and ud.userdetails_id in ("+policy_ids+" )");
					
					System.out.println("Query :"+sql.toString());
					customerList = qrun.query (sql.toString(),  new MapListHandler());
					for(int i=0;i<customerList.size();i++){
						query ="select policy_type from policytype where id= ? and delete_flag =0";
						String policytype =  (String) qrun.query(query,new ScalarHandler(),customerList.get(i).get("policy_type"));
						customerList.get(i).put("policy_type", policytype);
					}
					System.out.println("size in customerList  "+customerList.size());
					List datalist = new ArrayList();
					
					datalist.add("Name");
					datalist.add("Address");
					datalist.add("City");
					datalist.add("Phone");
					datalist.add("Policy No");
					datalist.add("Expiry Date");
					datalist.add("Amount");
					datalist.add("Policy Type ");
					datalist.add("Status");
					datalist.add("Email");
					datalist.add("Vehicle_no");
					Iterator it = customerList.iterator();
					while(it.hasNext()){
						Map<String, Object> m = (Map) it.next();
						datalist.add(m.get("name"));
						datalist.add(StringEscapeUtils.escapeCsv(m.get("address").toString()));
						datalist.add(m.get("city"));
						datalist.add(m.get("phone"));
						datalist.add(m.get("policy_no"));
						datalist.add(m.get("exp_date"));
						datalist.add(m.get("amount"));
						datalist.add(m.get("policy_type"));
						datalist.add(m.get("status"));
						datalist.add(m.get("email"));
						datalist.add(m.get("vehicle_no"));
					}
					policyResult=FileManagement.writeToCSV(11,datalist);
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logError("ManagePolicyService :: exportFile() End:::"+e.getMessage(), logger);
		}
       }
		
		LogUtility.logInfo("ManagePolicyService :: exportFile() Ends:::",logger);
		return policyResult;
	}

	
}
