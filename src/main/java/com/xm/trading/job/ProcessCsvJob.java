package com.xm.trading.job;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.Callable;

import com.xm.trading.dto.Constant;
import com.xm.trading.dto.Result;
import com.xm.trading.dto.SharePrice;

public class ProcessCsvJob implements Callable<Boolean>{

	private String jobName;
	
	private ArrayList<SharePrice> sharePriceList=new ArrayList<>();
	
	private Result result= new Result();
	
	ProcessCsvJob(String jobName,ArrayList<SharePrice> sharePriceList){
		this.jobName=jobName;
		this.sharePriceList=sharePriceList;
	}

	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub
		
		Optional<SharePrice> minTimeStamp=this.sharePriceList.stream().min(Comparator.comparingLong(SharePrice::getTimestamp));
		
		if(minTimeStamp.isPresent()) {
			this.result.setNewest(minTimeStamp.get().getPrice());
		}
		
		Optional<SharePrice> maxTimeStamp=this.sharePriceList.stream().max(Comparator.comparingLong(SharePrice::getTimestamp));
		
		if(maxTimeStamp.isPresent()) {
			this.result.setOldest(maxTimeStamp.get().getPrice());
		}
		
		
		Optional<SharePrice> smallestPrice=this.sharePriceList.stream().min(Comparator.comparingDouble(SharePrice::getPrice));
		
		if(smallestPrice.isPresent()) {
			this.result.setMin(smallestPrice.get().getPrice());
		}
		
		
		Optional<SharePrice> maximumPrice=this.sharePriceList.stream().max(Comparator.comparingDouble(SharePrice::getPrice));
		
		if(maximumPrice.isPresent()) {
			this.result.setMax(maximumPrice.get().getPrice());
		}
		
		if(smallestPrice.isPresent() && maximumPrice.isPresent()) {
			Double temp=(this.result.getMax()-this.result.getMin())/this.result.getMin();
			this.result.setNormalizeValue(temp);
			
		}
		
		Constant.resultMap.put(jobName, result);
		
		return true;
	}


}
