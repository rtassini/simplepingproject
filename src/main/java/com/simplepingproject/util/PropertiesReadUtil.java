package com.simplepingproject.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertiesReadUtil {

	private PropertiesReadUtil() {}
	
	private static final Logger logger = LoggerFactory.getLogger(PropertiesReadUtil.class);
	
	public static String propertyRead(String key) {

	    Properties prop = new Properties();
	    InputStream input = null;
	    String property = "";
	    try {
	    	input = PropertiesReadUtil.class.getResourceAsStream("/config.properties");
	    	prop.load(input);
	    	
	    	property = prop.getProperty("delay");
	    	logger.info(key + ": "+ property);
	    } catch (IOException ex) {
	    	logger.error(ex.getMessage());
	    } finally {
	        if (input != null) {
	            try {
	                input.close();
	            } catch (IOException e) {
	            	logger.error(e.getMessage());
	            }
	        }
	    }
	    return property;
	  }
	
}
