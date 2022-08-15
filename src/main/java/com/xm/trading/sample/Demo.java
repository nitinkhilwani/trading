package com.xm.trading.sample;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Demo {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		 LocalTime time = LocalTime.now();
	      System.out.println("Current time: "+time.getSecond());
	      
	      for(int i=1;i<=30;i++) {
	    	  LocalDateTime now = LocalDateTime.now().withDayOfMonth(i);
		      long milliSeconds = Timestamp.valueOf(now).getTime();
		      System.out.println("MilliSeconds: "+milliSeconds);  
		      
		      
		      
//		      LocalDateTime dateTime = Instant.ofEpochMilli(milliSeconds)
//		    	        .atZone(ZoneId.systemDefault()) // default zone
//		    	        .toLocalDateTime(); // returns actual LocalDateTime object
//		      
		      //System.out.println("The Millisecond " + milliSeconds + " refers to Time " + dateTime);
	      }
	      
	      

	      
	      
	}

}
