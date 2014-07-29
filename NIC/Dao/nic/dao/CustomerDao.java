package nic.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nic.utility.GlobalVariables;
import nic.utility.LogUtility;
import nic.utility.db.DbUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

public class CustomerDao implements Customer{

	Logger logger = Logger.getLogger(this.getClass());
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String query ="";
	String errorMsg ="";
	
	@Override
	public List<Map<String, Object>> getCustomerDetailsById(String userDetails_id) {
		List<Map<String,Object>>userDetails = new ArrayList<Map<String,Object>>();
		LogUtility.logInfo("CustomerDao :: getCustomerDetailsById() Method Start", logger);
		try{	
			
			//get specific user details based on id
			StringBuilder sql  =new StringBuilder("select pd.id,ud.userdetails_id, ud.name, ud.address, ud.city, ud.phone, ud.email,ud.policy_type,ud.vehicle_no,pd.policy_no,pd.status, ");
			sql.append(" pd.amount,pd.exp_date from userdetails ud ");
			sql.append(" left join policydetails pd on ud.userdetails_id = pd.userdetails_id where ud.delete_flag = 0 and ");
			sql.append(" ud.userdetails_id =?  ORDER BY  pd.id desc ");
			userDetails = qrun.query(sql.toString(),new MapListHandler(),userDetails_id);
		
		}catch(Exception e){
			 e.getMessage();
			 LogUtility.logError("CustomerDao :: getCustomerDetailsById() Method Ends:::"+e.getMessage(),logger);
		}
		LogUtility.logInfo("CustomerDao :: getCustomerDetailsById() Method Ends:::",logger);
		return userDetails;
	}
	
	@Override
	public List<Map<String, Object>> getEditCustomerDetailsById(String userDetails_id, String policy_no) {
		List<Map<String,Object>>editUserDetails = new ArrayList<Map<String,Object>>();
		LogUtility.logInfo("CustomerDao :: getEditCustomerDetailsById() Method Start", logger);
		try{	
			
			//get specific user details based on id
			StringBuilder sql  =new StringBuilder("select pd.id,ud.userdetails_id, ud.name, ud.address, ud.city, ud.phone, ud.email,ud.policy_type,ud.vehicle_no,pd.policy_no,pd.status, ");
			sql.append(" pd.amount,pd.exp_date from userdetails ud ");
			sql.append(" left join policydetails pd on ud.userdetails_id = pd.userdetails_id where ud.delete_flag = 0 and ");
			sql.append(" ud.userdetails_id =? and pd.policy_no= ? and (pd.status in('pending') or pd.status is null) ");
			editUserDetails = qrun.query(sql.toString(),new MapListHandler(),userDetails_id,policy_no);
		
		}catch(Exception e){
			 e.getMessage();
			 LogUtility.logError("CustomerDao :: getEditCustomerDetailsById() Method Ends:::"+e.getMessage(),logger);
		}
		LogUtility.logInfo("CustomerDao :: getEditCustomerDetailsById() Method Ends:::",logger);
		return editUserDetails;
	}

	@Override
	public List<Map<String, Object>> getPolicyTypeList() {
		List<Map<String,Object>>policyTypeList = new ArrayList<Map<String,Object>>();
		LogUtility.logInfo("CustomerDao :: getPolicyTypeList() Method Start", logger);
		try{	
			
			//get specific user details based on id
			StringBuilder sql  =new StringBuilder("SELECT id, policy_type  FROM policytype where delete_flag =0 ");
			policyTypeList = qrun.query(sql.toString(),new MapListHandler());
		
		}catch(Exception e){
			 e.getMessage();
			 LogUtility.logError("CustomerDao :: getPolicyTypeList() Method Ends:::"+e.getMessage(),logger);
		}
		LogUtility.logInfo("CustomerDao :: getPolicyTypeList() Method Ends:::",logger);
		return policyTypeList;
		
	}

	@Override
	public Map<String, Object> isPolityExits(String policy_no) {
		Map<String,Object> policyDetails = new HashMap<String,Object>();
		LogUtility.logInfo("CustomerDao :: isPolityExits() Method Start", logger);
		try{	

			StringBuilder sql =new StringBuilder("select ud.userdetails_id,ud.policy_type,pd.old_policy ,pd.status from userdetails ud  left join policydetails pd on ud.userdetails_id = pd.userdetails_id where");
//			sql.append(" ud.delete_flag = 0  and pd.status = ? ");
			sql.append(" ud.delete_flag = 0 ");
			if(!policy_no.equals("")){
				sql.append(" and pd.policy_no ='"+policy_no+"'");
			}
			policyDetails = qrun.query(sql.toString(),new MapHandler());
			
		}catch(Exception e){
			 e.getMessage();
			 LogUtility.logError("CustomerDao :: isPolityExits() Method Ends:::"+e.getMessage(),logger);
		}
		LogUtility.logInfo("CustomerDao :: isPolityExits() Method Ends:::",logger);
		return policyDetails;
	}
	
}
