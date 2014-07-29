package nic.utility.db;


import javax.sql.DataSource;

import nic.utility.InitialisedProperties;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


public class DbUtils {

	   static DataSource dataSource;
	   
	   public static void setDataSource(DataSource datasource) {
	      DbUtils.dataSource = datasource;
	      System.out.println("set datasource "+DbUtils.dataSource);
	   }
	   
	   public static String initialiseDataSource(){
		   XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("dbconfig.xml"));
			dataSource = (DataSource) beanFactory.getBean("nic_datasource");
			InitialisedProperties.initialiseErrors();
			
		   return "success";
	   }

	   public static DataSource getDataSource(){
		   if(dataSource != null){
			   return dataSource; 
		   }else{
			   String res = initialiseDataSource();
			   System.out.println(dataSource);
			   if(res.equalsIgnoreCase("success")){
				   return dataSource;
			   }
		   }
		   
		   System.out.println("get datasource "+DbUtils.dataSource);
	      return dataSource;
	   }
	   public static void main(String args[]){
		   getDataSource();
	   }
}
