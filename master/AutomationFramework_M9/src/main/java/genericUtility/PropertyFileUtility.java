package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consist of method related to read data from property file
 */

public class PropertyFileUtility {
	/**
	 * This method is used to read data from property file provided with key
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
		public String toReadDataFromPropertyFile(String key) throws IOException {
			FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
			Properties prop = new Properties();
			prop.load(fis);
			String value = prop.getProperty(key);
			return value;
		}
		
	}


