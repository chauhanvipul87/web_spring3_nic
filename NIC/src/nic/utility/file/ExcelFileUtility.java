package nic.utility.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import nic.utility.CommonDAO;
import nic.utility.DateTimeFormater;
import nic.utility.GlobalVariables;
import nic.utility.LogUtility;
import nic.utility.db.DbUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelFileUtility {
	
	Logger logger = Logger.getLogger(this.getClass());
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String query ="";
	String errorMsg ="";
	String policy_type = "";
	private String inputFile;
	private String user_id;

	  public void setUserId(String userString){
		  this.user_id = userString;
	  }
	  public void setInputFile(String inputFile) {
	    this.inputFile = inputFile;
	  }
	  
	  public void setPolicyType(String type){
		  this.policy_type = type;
	  }

	  public int mediclaimFile() throws IOException  {
	    LogUtility.logInfo("ExcelFileUtility :: readFile() Method Start", logger);  
	    File inputWorkbook = new File(inputFile);
	    Workbook w;
	    int flag =0;
	    int userdetails_id = 0;
	    try {
	      w = Workbook.getWorkbook(inputWorkbook);
	      String [] sheetNames = w.getSheetNames();
	      Sheet sheet=null;
	      for (int sheetNumber =0; sheetNumber < sheetNames.length; sheetNumber++){
	          sheet=w.getSheet(sheetNames[sheetNumber]);
	          System.out.println("sheetNames[sheetNumber] ::"+sheetNames[sheetNumber]);
			  System.out.println("sheet.getColumns() ::"+sheet.getColumns());
		      System.out.println("sheet.getRows() ::"+sheet.getRows());

		     /* if(sheet.getColumns()!=7){
		    	  return 8;
		      }*/
		      List<Map<String,Object>> finalList = new ArrayList<Map<String,Object>>();
		      for (int i = 1; i < sheet.getRows(); i++) {
		    	  System.out.println("*** Row no is "+i);	
		    	  Cell[] cell = sheet.getRow(i);
		    	  Map<String,Object> map= new HashMap<String, Object>();
		          for(int j=1;j<cell.length;j++){
//		        	  if(j>0 && j<5){
		        		  switch(j){
		        		  	case 1:{
		        		  		map.put("name",cell[j].getContents());
		        		  		map.put("sheet_name", sheetNames[sheetNumber]);
		        		  		break;
		        		  	}
		        		  	case 2:{
		        		  		map.put("address",cell[j].getContents());
		        		  		break;
		        		  	}
		        		  	case 3:{
		        		  		map.put("city",cell[j].getContents());
		        		  		break;
		        		  	}
		        		  	case 4:{
		        		  		map.put("phone",cell[j].getContents());
		        		  		break;
		        		  	}
//		        		  }
//		        	  }else{
//		        		  if(j>0){
//		        			  switch(j){
			        		  	case 5:{
			        		  		if(cell[j].getContents()!=null && !cell[j].getContents().equals("")){
			        		  			map.put("policy_no",cell[j].getContents());
			        		  		}else{
			        		  			map.put("policy_no","");
			        		  		}
			        		  		break;
			        		  	}
			        		  	case 6:{
										
			        		  		if (cell[j].getType() == CellType.DATE)
										{
										  DateCell dc = (DateCell) cell[j];
										  Date datec2 = dc.getDate();
										  System.out.println("date is  "+datec2);
										  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										  String date = sdf.format(datec2);
										  map.put("expiry_date",date);
										}else{
											map.put("expiry_date","");
										}
			        		  		break;
			        		  	}
			        		  	case 7:{
			        		  		if(cell[j].getContents()!=null && !cell[j].getContents().equals("")){
			        		  			String amount ="";
			        		  			if(cell[j].getContents().contains("=")){
			        		  				amount =cell[j].getContents().substring(0,cell[j].getContents().indexOf("="));  
			        		  				map.put("amount",amount);
			        		  			}else{
			        		  				map.put("amount",cell[j].getContents());
			        		  			}
			        		  		}else{
			        		  			map.put("amount","0.0");
			        		  		}
			        		  		break;
			        		  	}
			        		  }
//		        		  }
//		        	  }
		          }
		          finalList.add(map);
		      }
		          
		      System.out.println("userQuery is "+finalList);
		      for(int a=0;a<finalList.size();a++){
		    	  System.out.println("insert record no "+a);
		    	  Map<String,Object> map = finalList.get(a);
		    	  
		    	  String name 			= map.get("name")!=null		  ? map.get("name").toString() : "";
		    	  String sheet_name 	= map.get("sheet_name")!=null ? map.get("sheet_name").toString() : "";
		    	  String address	    = map.get("address")!=null 	  ? map.get("address").toString() : "";
		    	  String city 		    = map.get("city")!=null 	  ? map.get("city").toString() : "";
		    	  String phone 		    = map.get("phone")!=null 	  ? map.get("phone").toString() : "";
		    	  
		    	  System.out.print("name ::"+name);
		    	  System.out.print(" sheet_name ::"+sheet_name);
		    	  System.out.print("address ::"+address);
		    	  System.out.print("city ::"+city);
		    	  System.out.println("phone :: "+phone);
		    	  
		    	  if(!name.equals("")){
			    	  userdetails_id = new CommonDAO().getautoGenerateID("userdetails", "userdetails_id");
			    	  
			    	  query="INSERT INTO userdetails (userdetails_id, name, address, city, phone,sheet_name, modified_datetime, policy_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			    	  int affectedRows = qrun.update(query,userdetails_id,name,address,city,phone,sheet_name,DateTimeFormater.format_datetime(),this.policy_type);
			    	  if(affectedRows >0){
			    		  flag =1;
			    		  if(map.get("policy_no")!=null && !map.get("policy_no").toString().equals("")){
			    			 
			    			  String policy_no 				= map.get("policy_no").toString();
			    			  String expiry_date 		    = map.get("expiry_date")!=null 	  ? map.get("expiry_date").toString() : "";
			    			  String amount 		        = map.get("amount")!=null 	 	  ? map.get("amount").toString() : "0.0";
	
			    			  query ="INSERT INTO policydetails (policy_no, amount, exp_date, old_policy, status, " +
			    			  		" userdetails_id, user_id, modified_datetime) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			    			  affectedRows = qrun.update(query,new Object[]{policy_no,amount,expiry_date,policy_no,GlobalVariables.pendingStatus,userdetails_id,user_id,new DateTimeFormater().format_datetime()});
			    		  }
			    		  new CommonDAO().setautoGenerateID("userdetails", "userdetails_id",(userdetails_id+1));  
			    	  }
		    	  }
		      }
	      }
		      
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	      if(flag ==1){
	    	  new CommonDAO().setautoGenerateID("userdetails", "userdetails_id",(userdetails_id+1));  
	      }
	      LogUtility.logInfo(e.getMessage()+"ExcelFileUtility :: readFile() Method End", logger);  
	      return 0;
	    }
	    LogUtility.logInfo("ExcelFileUtility :: readFile() Method End", logger);  
	    return 1;
	  }
	  
	  public int vehicleFile() throws IOException  {
		    LogUtility.logInfo("ExcelFileUtility :: readFile() Method Start", logger);  
		    File inputWorkbook = new File(inputFile);
		    Workbook w;
		    int flag =0;
		    int userdetails_id = 0;
		    try {
		      w = Workbook.getWorkbook(inputWorkbook);
		      String [] sheetNames = w.getSheetNames();
		      Sheet sheet=null;
		      for (int sheetNumber =0; sheetNumber < sheetNames.length; sheetNumber++){
		          sheet=w.getSheet(sheetNames[sheetNumber]);
		          System.out.println("sheetNames[sheetNumber] ::"+sheetNames[sheetNumber]);
				  System.out.println("sheet.getColumns() ::"+sheet.getColumns());
			      System.out.println("sheet.getRows() ::"+sheet.getRows());

			     /* if(sheet.getColumns()!=7){
			    	  return 8;
			      }*/
			      List<Map<String,Object>> finalList = new ArrayList<Map<String,Object>>();
			      for (int i = 1; i < sheet.getRows(); i++) {
			    	  System.out.println("*** Row no is "+i);	
			    	  Cell[] cell = sheet.getRow(i);
			    	  Map<String,Object> map= new HashMap<String, Object>();
			          for(int j=0;j<cell.length;j++){
			        		  switch(j){
			        		  	case 1:{
			        		  		map.put("name",cell[j].getContents());
			        		  		map.put("sheet_name", sheetNames[sheetNumber]);
			        		  		break;
			        		  	}
			        		  	case 2:{
			        		  		map.put("address",cell[j].getContents());
			        		  		break;
			        		  	}
			        		  	case 3:{
			        		  		map.put("city",cell[j].getContents());
			        		  		break;
			        		  	}
			        		  	case 4:{
			        		  		map.put("phone",cell[j].getContents());
			        		  		break;
			        		  	}
			        		  	case 5:{
			        		  		if(cell[j].getContents()!=null && !cell[j].getContents().equals("")){
			        		  			map.put("policy_no",cell[j].getContents());
			        		  		}else{
			        		  			map.put("policy_no","");
			        		  		}
			        		  		break;
			        		  	}
			        		  	case 6:{
			        		  		if(cell[j].getContents()!=null && !cell[j].getContents().equals("")){
			        		  			map.put("vehicle_no",cell[j].getContents());
			        		  		}else{
			        		  			map.put("vehicle_no","");
			        		  		}
			        		  	}
			        		  	case 7:{
										
			        		  		if (cell[j].getType() == CellType.DATE)
										{
										  DateCell dc = (DateCell) cell[j];
										  Date datec2 = dc.getDate();
										  System.out.println("date is  "+datec2);
										  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										  String date = sdf.format(datec2);
										  map.put("expiry_date",date);
										}else{
											map.put("expiry_date","");
										}
			        		  		break;
			        		  	}
			        		  	case 8:{
			        		  		if(cell[j].getContents()!=null && !cell[j].getContents().equals("")){
			        		  			String amount ="";
			        		  			if(cell[j].getContents().contains("=")){
			        		  				amount =cell[j].getContents().substring(0,cell[j].getContents().indexOf("="));  
			        		  				map.put("amount",amount);
			        		  			}else{
			        		  				map.put("amount",cell[j].getContents());
			        		  			}
			        		  		}else{
			        		  			map.put("amount","0.0");
			        		  		}
			        		  		break;
			        		  	}
			        		  }
			        	  }
			          finalList.add(map);
			      }
			      System.out.println("userQuery is "+finalList);
			      for(int a=0;a<finalList.size();a++){
			    	  System.out.println("insert record no "+a);
			    	  Map<String,Object> map = finalList.get(a);
			    	  
			    	  String name 			= map.get("name")!=null		  ? map.get("name").toString() : "";
			    	  String sheet_name 	= map.get("sheet_name")!=null ? map.get("sheet_name").toString() : "";
			    	  String address	    = map.get("address")!=null 	  ? map.get("address").toString() : "";
			    	  String city 		    = map.get("city")!=null 	  ? map.get("city").toString() : "";
			    	  String phone 		    = map.get("phone")!=null 	  ? map.get("phone").toString() : "";
			    	  String vehicle_no 	= map.get("vehicle_no")!=null ? map.get("vehicle_no").toString() : "";
			    	  
			    	  System.out.print("name ::"+name);
			    	  System.out.print(" sheet_name ::"+sheet_name);
			    	  System.out.print("address ::"+address);
			    	  System.out.print("city ::"+city);
			    	  System.out.println("phone :: "+phone);
			    	  
			    	  if(!name.equals("")){
				    	  userdetails_id = new CommonDAO().getautoGenerateID("userdetails", "userdetails_id");
				    	  
				    	  query="INSERT INTO userdetails (userdetails_id, name, address, city, phone,sheet_name, modified_datetime, vehicle_no, policy_type ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
				    	  int affectedRows = qrun.update(query,userdetails_id,name,address,city,phone,sheet_name,new DateTimeFormater().format_datetime(), vehicle_no, this.policy_type);
				    	  if(affectedRows >0){
				    		  flag =1;
				    		  if(map.get("policy_no")!=null && !map.get("policy_no").toString().equals("")){
				    			 
				    			  String policy_no 				= map.get("policy_no").toString();
				    			  String expiry_date 		    = map.get("expiry_date")!=null 	  ? map.get("expiry_date").toString() : "";
				    			  String amount 		        = map.get("amount")!=null 	 	  ? map.get("amount").toString() : "0.0";
	
				    			  query ="INSERT INTO policydetails (policy_no, amount, exp_date, old_policy, status, " +
				    			  		" userdetails_id, user_id, modified_datetime) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
				    			  affectedRows = qrun.update(query,new Object[]{policy_no,amount,expiry_date,policy_no,GlobalVariables.pendingStatus,userdetails_id,user_id,new DateTimeFormater().format_datetime()});
				    		  }
				    		  new CommonDAO().setautoGenerateID("userdetails", "userdetails_id",(userdetails_id+1));  
				    	  }
			    	  }
			      }
		      }
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		      if(flag ==1){
		    	  new CommonDAO().setautoGenerateID("userdetails", "userdetails_id",(userdetails_id+1));  
		      }
		      LogUtility.logInfo(e.getMessage()+"ExcelFileUtility :: readFile() Method End", logger);  
		      return 0;
		    }
		    LogUtility.logInfo("ExcelFileUtility :: readFile() Method End", logger);  
		    return 1;
		  }
	  
	  public static void main(String args[]) throws IOException{
		  ExcelFileUtility ex=  new ExcelFileUtility();
		  ex.setInputFile("C:\\Users\\vipul\\Desktop\\Motor2012-2013.xls");
		  ex.setUserId("1");
		  System.out.println(ex.vehicleFile());
	  }
	
	
	
	
}
