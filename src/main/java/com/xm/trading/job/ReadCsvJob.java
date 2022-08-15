package com.xm.trading.job;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.xm.trading.dto.Constant;
import com.xm.trading.dto.SharePrice;

public class ReadCsvJob implements Callable<Boolean>{

	private String jobName;
	private String filename;
	
	private ArrayList<SharePrice> sharePriceList=new ArrayList<>();
	
	public ReadCsvJob(String filename,String jobName){
		this.jobName=jobName;
		this.filename=filename;
	}
	
	
	@Override
	public Boolean call() throws Exception {
		System.out.println("---- Processing job for filename "+jobName+" -----");
		
		 try {
	           
			 CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();

	            File inputStream = new File(this.jobName);
	            CSVParser csvParser = CSVParser.parse(inputStream, StandardCharsets.UTF_8, csvFormat);

	            for(CSVRecord csvRecord : csvParser) {
	            	SharePrice sharePrice=SharePrice.parse(csvRecord);
	            	sharePriceList.add(sharePrice);
	                System.out.println(sharePrice);
	            }
	            
	            String names[]=this.filename.split("_");
	            
	            if(names.length==2)
	            	Constant.resultMapList.put(names[0], sharePriceList);
	            
	            csvParser.close();
	        } catch (IOException e) {	        	
	            e.printStackTrace();
	        }
		
		System.out.println("---- Processing job for filename "+jobName+" ----- completed");
        return true;
	}
}
