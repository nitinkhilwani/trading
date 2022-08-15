package com.xm.trading.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xm.trading.dto.Constant;
import com.xm.trading.dto.SharePrice;

@Component
public class CsvPFilerocessor {

	/*
	 @Scheduled(fixedDelay = 5000)
	 public void scheduleFixedDelayTask() {
			System.out.println("inside FutureTask check ");
			JobProcessor.jobStatusList.forEach((futureTask) -> {
				if(futureTask.isDone()){
					
					try {
						System.out.println("FutureTask status ="+futureTask.get());
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	*/
	
	@Scheduled(fixedDelay = 5000)
	 public void scheduleFixedDelayTask() {
			System.out.println("inside FutureTask check ");
			
			Set<String> jobNameList= Constant.resultMapList.keySet();
			
			jobNameList.forEach( item -> {
				ArrayList<SharePrice> sharePriceList=Constant.resultMapList.get(item);
				ProcessCsvJob processCsvJob=new ProcessCsvJob(item,sharePriceList );
				JobProcessor.submitProcessJob(processCsvJob);
				Constant.resultMapList.remove(item);
			});
			
			
		}
}
