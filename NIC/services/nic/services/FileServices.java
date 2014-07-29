package nic.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nic.utility.DateTimeFormater;
import nic.utility.GlobalVariables;
import nic.utility.LogUtility;
import nic.utility.db.DbUtils;
import nic.utility.file.ExcelFileUtility;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public class FileServices {

	Logger logger = Logger.getLogger(this.getClass());
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String query ="";
	String errorMsg ="";
	
	
	public ModelAndView file_entered(HttpServletRequest req, HttpServletResponse res,MultipartFile file){
		LogUtility.logInfo("FileController :: file_entered() Method Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String datetime = DateTimeFormater.format_date();
			String originFileName = file.getOriginalFilename();
			String filename = originFileName.toLowerCase();
			if(!filename.endsWith(".xls")){
				model.addObject("errorClass", GlobalVariables.errorClass);
				model.addObject("errorMsg", "Please enter correct file format. Only .Xls files are allowed.");
				model.setViewName("home/importfile");
				return model;
			}
			
			BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
			String realpath = req.getSession().getServletContext().getRealPath("/upload/")+"\\";
			String newFileName = filename.substring(0,filename.length()-4)+" "+DateTimeFormater.format_date()+".xls";
			File excelFile = new File(realpath+newFileName);
			if(!excelFile.exists()){
				excelFile.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(excelFile);
			int readBytes = 0;
            byte[] buffer = new byte[10000];
            while ((readBytes = bis.read(buffer, 0, 10000)) != -1) {
                    fos.write(buffer, 0, readBytes);
            }
            fos.close();
            bis.close();
			
            
			ExcelFileUtility excel = new ExcelFileUtility();
			excel.setInputFile(realpath+newFileName);
			HttpSession session = req.getSession();
			String user_id = session.getAttribute("user_id").toString(); 
			excel.setUserId(user_id);
			String policy_type = req.getParameter("policytype");
			excel.setPolicyType(policy_type);
			int ret = 0;
			if(policy_type.equals("4")){
				excel.setPolicyType(policy_type);
				ret = excel.vehicleFile();
			}else{
				ret = excel.mediclaimFile(); // for mediclaim, accident, fire
			}
			if(ret ==0){
				model.addObject("errorClass", GlobalVariables.errorClass);
				model.addObject("errorMsg", "Something went wrong while uploading your file.");
			}else if(ret ==8){
			
				model.addObject("errorClass", GlobalVariables.errorClass);
				model.addObject("errorMsg", "Please enter valid file having 8 columns.");
			}else{
				model.addObject("errorClass", GlobalVariables.successClass);
				model.addObject("errorMsg", "File is processed & Data inserted successfully.");
			}
			
		}catch(Exception e ){
			e.printStackTrace();
			model.addObject("errorClass", GlobalVariables.errorClass);
			model.addObject("errorMsg", "Something went wrong while uploading your file.");
			LogUtility.logInfo(e.getMessage()+"FileController :: importFile() Method End", logger);
		}
		
		model.setViewName("home/importfile");
		LogUtility.logInfo("FileController :: file_entered() Method End", logger);
		return model;
			
	 }
		
	

}
