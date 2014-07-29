import java.util.Calendar;


public class MainTest {
	public static void main(String[] args){
		  
		  Calendar cal = Calendar.getInstance();
		  
		  String currentMonthStartDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+getValidDay(cal.get(Calendar.DATE));
		  System.out.println("currentMonthStartDate ::"+currentMonthStartDate);
		  
		  String currentMonthEndDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+getValidDay(cal.getActualMaximum(Calendar.DATE));
		  System.out.println("currentMonthEndDate ::"+currentMonthEndDate);
		  cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
		 
		  String nextMonthStartDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+getValidDay(cal.getActualMinimum(Calendar.DATE));
		  System.out.println("nextMonthStartDate ::"+nextMonthStartDate);
		  
		  String nextMonthEndDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+getValidDay(cal.getActualMaximum(Calendar.DATE));
		  System.out.println("nextMonthEndDate ::"+nextMonthEndDate);
		  
		 }
	
	public static String getValidDay(int day){
		String validDay =String.valueOf(day);
		if(day <=9){
			validDay = "0"+String.valueOf(day);
		}
		return  validDay;
	}
}
