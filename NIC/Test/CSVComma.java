import org.apache.commons.lang3.StringEscapeUtils;



public class CSVComma {

	public static void main(String[] args){
		String escaped = StringEscapeUtils.escapeCsv("hello, welcome dear"); 

			System.out.println(escaped); 
	}

	
}
