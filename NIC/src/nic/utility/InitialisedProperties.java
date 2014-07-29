package nic.utility;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

public class InitialisedProperties {
	
	public static void initialiseErrors(){
		Properties prop = new Properties();
		try{
			
			InputStream in = InitialisedProperties.class.getResourceAsStream("error.properties");
			prop.load(in);
//			GlobalVariables.success_requestQuote = prop.getProperty("success_requestQuote");
//			GlobalVariables.error_requestQuote   = prop.getProperty("error_requestQuote");
//			GlobalVariables.error_Message        = prop.getProperty("error_Message");
//			GlobalVariables.addToCart_success    = prop.getProperty("addToCart_success");
//			GlobalVariables.updateItem_Cart_success =prop.getProperty("updateItem_Cart_success");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
//			GlobalVariables.orderMore_success_msg = prop.getProperty("orderMore_success_msg");
//			GlobalVariables.contactus_message 	  = prop.getProperty("contactus_message");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
