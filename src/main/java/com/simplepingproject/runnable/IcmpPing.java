package com.simplepingproject.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IcmpPing implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(IcmpPing.class);
	
	private String host = "";
	
	public IcmpPing(String host) {
		this.host = host;
	}
	
	@Override
	public void run() {
		ping();

	}
	
	
	public void ping() {
		try{
	        String strCommand = "";
	        logger.info("My OS :" + System.getProperty("os.name"));
	        if(System.getProperty("os.name").startsWith("Windows")) {
	            // construct command for Windows Operating system
	            strCommand = "ping -n 1 " + this.host;
	        } else {
	            // construct command for Linux and OSX
	            strCommand = "ping -c 1 " + this.host;
	        }
	        logger.info("Command: " + strCommand);
	        // Execute the command constructed
	        Process myProcess = Runtime.getRuntime().exec(strCommand);
	        myProcess.waitFor();
	        if(myProcess.exitValue() == 0) {
	        	logger.info("true");
	        } else {
	        	logger.info("false");
	        }
	    } catch( Exception e ) {
	        e.printStackTrace();
	        logger.info("false");
	    }
	}

}
