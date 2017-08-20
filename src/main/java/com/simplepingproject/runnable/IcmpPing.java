package com.simplepingproject.runnable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplepingproject.util.PropertiesReadUtil;

public class IcmpPing implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(IcmpPing.class);
	
	private String host = "";
	private int pingResult; 
	
	public IcmpPing(String host) {
		this.host = host;
	}
	
	@Override
	public void run() {
		try {
			ping();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	
	public void ping() throws Exception {
		try{
	        String strCommand = "";
	        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	        for(int i=1;i<5;i++){
	        	logger.info("Start at :" + df.format(new Date()));
	        	if(System.getProperty("os.name").startsWith("Windows")) {
		            strCommand = "ping -n 5 " + this.host;
		        } else {
		            strCommand = "ping -c 5 " + this.host;
		        }
		        logger.info("Command: " + strCommand);
		        Process myProcess = Runtime.getRuntime().exec(strCommand);
		        myProcess.waitFor();
		        
		        this.pingResult = myProcess.exitValue();
		        
		        try{
	        		Thread.sleep(Long.parseLong(PropertiesReadUtil.propertyRead("delay")));
	        	}catch(InterruptedException e){
	        		System.out.println(e);
        		}
	        	logger.info("Wake at :" + df.format(new Date()));
	        }
	    } catch( Exception e ) {
	        logger.error(e.getMessage());
	        throw e;
	    }
	}

	public int getPingResult() {
		return pingResult;
	}

	public void setPingResult(int pingResult) {
		this.pingResult = pingResult;
	}

}
