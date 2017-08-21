package com.simplepingproject.rest;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplepingproject.model.ReportVO;
import com.simplepingproject.runnable.IcmpPing;

@Path("/report")
public class Report {

	
		
	private static final Logger logger = LoggerFactory.getLogger(Report.class);
		
	@SuppressWarnings("finally")
	@POST
	public Response calling(@QueryParam("param") final List<String> hosts) {
 
		logger.info("Calling report");
		
		int pingResult = 0;
		ReportVO vo = new ReportVO();
		for (String host : hosts) {
			IcmpPing task = new IcmpPing(host);
			vo.setHost(host);
			
            Thread worker = new Thread(task);
            worker.setName(host);
            worker.start();
            
            pingResult = task.getPingResult();
		}
		
        
		JSONObject json = new JSONObject();
		if(pingResult == 0) {
			vo.setIcmp_ping(Integer.toString(pingResult));
		}
		Status status =  Response.Status.OK;
		try {
			json.put("message", vo);
		
		
		}catch (Exception e){
			json.put("message", e.getMessage());
			status =  Response.Status.FORBIDDEN;
			
		} finally{
			return Response.status(status).entity(json.toString()).build();
		}
 
 
	}
	
}
