package nic.utility.file;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class FileManagement {
	/**
	 * @param args
	 */

	public void readFromCSV(String filepath)
	{
		System.out.println("readFromCSV Start...");
		try
		{
			//csv file containing data
			String strFile = filepath;
			//create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(strFile));
			String strLine = "";
			StringTokenizer st = null;
			int lineNumber = 0, tokenNumber = 0;
		 
			//read comma separated file line by line
			while( (strLine = br.readLine()) != null)
			{
				lineNumber++;
		 		//break comma separated line using ","
				st = new StringTokenizer(strLine, ",");
				while(st.hasMoreTokens())
				{
					//	display csv values
					tokenNumber++;
					System.out.println("Line # " + lineNumber +", Token # " + tokenNumber+ ", Token : "+ st.nextToken());
				}
		 		//reset token number
				tokenNumber = 0;
		 	}
			System.out.println("Successfully Read From CSV");
		}
		catch(Exception e)
		{
			System.out.println("Error Message : "+e.getMessage());
			e.printStackTrace();
		}
		System.out.println("readFromCSV End...");
	}
	
	/*public void writeToCSV(String filepath,int datacolumns,List datalist)
	   {
		System.out.println("writeToCSV Start...");
		try
		{
		    FileWriter writer = new FileWriter(filepath);
		    int currentcolumn=0;
		    
		    for(int i =0;i<datalist.size();i++)
		    {
		    	currentcolumn++;
		    	writer.append(datalist.get(i).toString());
		    	if(currentcolumn==datacolumns)
		    	{
		    		currentcolumn=0;
		    		writer.append('\n');
		    		continue;
		    	}else
		    	{
		    		writer.append(',');
		    	}
		    }
		    //writer.flush();
		    writer.close();
		    System.out.println("Successfully Write to CSV");
		}
		catch(IOException e)
		{
			System.out.println("Error Message : "+e.getMessage());
		    e.printStackTrace();
		} 
		System.out.println("writeToCSV End...");
	    }*/
	
	public  static StringBuffer writeToCSV(int datacolumns,List datalist)
	   {
		String str="";
		StringBuffer returnString = new StringBuffer();
		System.out.println("writeToCSV Start..."+datalist.size());
		try
		{
		    int currentcolumn=0;
		    
		    for(int i =0;i<datalist.size();i++)
		    {
		    	currentcolumn++;
		    	if(datalist.get(i) ==  null){
		    		returnString.append("");
		    	}else{
		    		returnString.append(datalist.get(i).toString());
		    	}
		    	if(currentcolumn==datacolumns)
		    	{
		    		currentcolumn=0;
		    		returnString.append('\n');
		    		continue;
		    	}else
		    	{
		    		returnString.append(',');
		    	}
		    }
		    //writer.flush();
		    System.out.println("Successfully Write to CSV");
		}
		catch(Exception e)
		{
			System.out.println("Error Message : "+e.getMessage());
		    e.printStackTrace();
		} 
		System.out.println("writeToCSV End...");
	   return returnString; 
	   }

	public  void writeToPDF(String filepath,int datacolumns,List datalist)
	   {
		System.out.println("writeToPDF Start...");
		try
		{
			PdfPTable table = new PdfPTable(datacolumns);
			Document document=new Document();
		   	PdfWriter.getInstance(document,new FileOutputStream(filepath));
		   	//int currentrow=0;
		    for(int i =0;i<datalist.size();i++)
		    {
		    	//currentrow++;
				table.addCell(datalist.get(i).toString());
		    }
		    document.open();
		    document.add(table);
		    document.close(); 
		    System.out.println("Successfully Write to PDF");
		}
		catch(Exception e)
		{
			System.out.println("Error Message : "+e.getMessage());
		    e.printStackTrace();
		} 
		System.out.println("writeToPDF End...");
	    }

	
	public void readFromExcel(String filepath) throws IOException  
	{
		System.out.println("readFromExcel Start...");
		try {
			File inputWorkbook = new File(filepath);
			Workbook w;
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines
			for (int j = 0; j < sheet.getColumns(); j++) {
				for (int i = 0; i < sheet.getRows(); i++) {
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					if (cell.getType() == CellType.LABEL) {
						//System.out.println("I got a label "	+ cell.getContents());
					}
					if (cell.getType() == CellType.NUMBER) {
						//System.out.println("I got a number "+ cell.getContents());
					}
				}
			}
			System.out.println("Successfully Read From Excel");
		} catch (BiffException e) {
			System.out.println("Error Message : "+e.getMessage());
			e.printStackTrace();
		}
		System.out.println("readFromExcel End...");
	}
	
	
	public void readFromXML(String filepath)
	{
		
	}
	

	public static void main(String args[])
	{
		FileManagement fm = new FileManagement();
		try{
		//fm.readFromExcel("D://FTP//Client.xls");
			ArrayList datalist = new ArrayList();
			int rowFetch = 6;
			for(int i=0;i<rowFetch;i++)
			{
				datalist.add("Id"+i);
				datalist.add("Code"+i);
				datalist.add("Name"+i);
			}
			//fm.writeToCSV("D://NewClient.csv", 3, datalist);
			fm.writeToPDF("D://NewClient.pdf", 3, datalist);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
