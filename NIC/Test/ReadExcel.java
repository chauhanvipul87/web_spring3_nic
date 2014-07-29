import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

  private String inputFile;

  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }

  public void read() throws IOException  {
    File inputWorkbook = new File(inputFile);
    Workbook w;
    try {
      w = Workbook.getWorkbook(inputWorkbook);
      String [] sheetNames = w.getSheetNames();
      Sheet sheet=null;
      for (int sheetNumber =0; sheetNumber < sheetNames.length; sheetNumber++){
          List<String > fields = new ArrayList<String>();
          sheet=w.getSheet(sheetNames[sheetNumber]);
          System.out.println("sheetNames[sheetNumber] ::"+sheetNames[sheetNumber]);
		  System.out.println("sheet.getColumns() ::"+sheet.getColumns());
	      System.out.println("sheet.getRows() ::"+sheet.getRows());

	      if(sheet.getColumns()!=8){
	    	  // return "Please enter valid file having 8 columns."
	      }
	      for (int i = 1; i < sheet.getRows(); i++) {
	    	  System.out.println("*** Row no is "+i);	
	    	  Cell[] cell = sheet.getRow(i);
	          for(int j=0;j<cell.length;j++){
	        	  System.out.println("User Details......");
	        	  if(j>0 && j<5){
	        		  
	        		  System.out.println("cell is "+cell[j].getContents());
	        	  }else{
	        		  if(j>0){
	        			  System.out.println("Policy Details......");
	        			  System.out.println("Policy cell is "+cell[j].getContents());
	        		  }
	        		  
	        	  }
	          }
	      }
	      
	      
        /* for (int columns=0;columns < sheet.getColumns();columns++){
             fields.add(sheet.getCell(columns, 0).getContents());                 
         }*/
      }
      
    } catch (BiffException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    ReadExcel test = new ReadExcel();
    test.setInputFile("C:\\Users\\vipul\\Desktop\\Hashmukh bhai's Data\\Mediclaim2012-2013.xls");
    test.read();
  }

} 
