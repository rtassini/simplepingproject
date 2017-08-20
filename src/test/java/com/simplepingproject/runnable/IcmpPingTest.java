package com.simplepingproject.runnable;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;

public class IcmpPingTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPing() {
		String host = "www.google.com.br";
		IcmpPing ping = new IcmpPing(host);
		
		try {
			ping.ping();
		}catch (Exception e) {
			fail();
		}
	}
	
	
	@Test
	public void testPingMultipleThreads() {
		String host = "www.google.com.br";
		String host2 = "jasmin.com";
		String host3 = "oranum.com";
        
        try {
		for (int i = 0; i < 3; i++) {
        	Runnable task = null;
        	if(i == 1)
        		task = new IcmpPing(host);
        	else if(i == 2)
        		task = new IcmpPing(host2);
        	else if(i == 3)
        		task = new IcmpPing(host3);
        	
            Thread worker = new Thread(task);
            worker.setName(String.valueOf(i));
            worker.start();
        }
		}catch(Exception e) {
			fail();
		}
	}

}
