package com.simplepingproject.main;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplepingproject.runnable.IcmpPing;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		logger.info("Starting");
		
		logger.info("Add hosts separeted by comma and in the end press enter");
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);
		
		String hosts [] = stdin.next().split(",");
		
		
        for (int i = 0; i < hosts.length; i++) {
        	Runnable task = new IcmpPing(hosts[i]);
        	
            Thread worker = new Thread(task);
            worker.setName(hosts[i]);
            worker.start();
        }
        logger.info("Finishing");
	}

}
