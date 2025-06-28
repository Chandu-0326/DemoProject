package genericUtility;

import java.util.Date;
import java.util.Random;

/**
 * This class contains all the methods related to Java
 */
public class JavaUtility {
	/**
	* This method is used to generate random integer number in the range of 1000
	* 
	* @return
	*/
	public int toGenerateRandomNumbers() {
		Random r = new Random();
		int value = r.nextInt(1000);
		return value;
	}
	
	/**
	 * This method is used to get system date and time in format
	 * 
	 * @return
	 */
	public String toGetSystemDateandTime() {
		Date d = new Date();
		String date[] = d.toString().split(" ");
		String day = date[0];
		String month = date[1];
		String date1 = date[2];
		String time = date[3].replace(":", "-");
		String year = date[5];
		String finalDate = day + " " + month + " " + date1 + " " + time + " " + year;
		return finalDate;
		
	}
	
}
