import java.util.Calendar;


public class Test {
	public static void main(String[] args){
		  
		  Calendar cal = Calendar.getInstance();
		/*  System.out.println("current ");
		  System.out.println(cal.get(Calendar.MONTH));
		  System.out.println(cal.get(Calendar.YEAR));
		  System.out.println(cal.get(Calendar.DATE));*/
		  //cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		  String currentMonthStartDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
		  System.out.println("currentMonthStartDate ::"+currentMonthStartDate);
		  
		  String currentMonthEndDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.getActualMaximum(Calendar.DATE);
		  System.out.println("currentMonthEndDate ::"+currentMonthEndDate);
/*		  System.out.println();
		  System.out.println("Current Date :"+cal.getTime());
		  System.out.println("Current Month min date :"+cal.getActualMinimum(Calendar.DATE));
		  System.out.println("Current Month max date :"+cal.getActualMaximum(Calendar.DATE));*/
		  
		  cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
		/*  System.out.println("Previous month :"+cal.getTime());
		  System.out.println("Previous Month min date :"+cal.getActualMinimum(Calendar.DATE));
		  System.out.println("Previous Month max date :"+cal.getActualMaximum(Calendar.DATE));*/
		  
		  String nextMonthStartDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.getActualMinimum(Calendar.DATE);
		  System.out.println("nextMonthStartDate ::"+nextMonthStartDate);
		  
		  String nextMonthEndDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.getActualMaximum(Calendar.DATE);
		  System.out.println("nextMonthEndDate ::"+nextMonthEndDate);
		  
		 // System.out.println("Date "+cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.getActualMaximum(Calendar.DATE));
		 }
}
