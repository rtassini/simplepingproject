package com.simplepingproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplepingproject.runnable.IcmpPing;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		String host = "www.google.com.br";
		logger.info("Starting debug");
		IcmpPing thread = new IcmpPing(host);
		thread.run();
		
		logger.info("Finishing debug");
	}

}
