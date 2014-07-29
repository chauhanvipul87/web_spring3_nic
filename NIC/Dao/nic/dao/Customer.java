package nic.dao;

import java.util.List;
import java.util.Map;

public interface Customer {

	public List<Map<String,Object>> getCustomerDetailsById(String userDetails_id);
	public List<Map<String,Object>> getEditCustomerDetailsById(String userDetails_id,String policy_no);
	public List<Map<String,Object>> getPolicyTypeList();
	public Map<String, Object>    isPolityExits(String policy_no);
	
}
