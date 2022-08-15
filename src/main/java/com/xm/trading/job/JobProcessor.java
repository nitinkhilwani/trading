package com.xm.trading.job;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.xm.trading.dto.Constant;

public class JobProcessor {

	public static ExecutorService executorReadJobService = Executors.newFixedThreadPool(Constant.POOL_SIZE);
	
	public static ExecutorService executorProcessJobService = Executors.newFixedThreadPool(Constant.POOL_SIZE);
	
	public static List<Future<Boolean>> readJobStatusList = new LinkedList<Future<Boolean>>();
	
	public static List<Future<Boolean>> processJobStatusList = new LinkedList<Future<Boolean>>();
	
	public static void submitJob(ReadCsvJob myCallable) {
		
		Future<Boolean> status=executorReadJobService.submit(myCallable);
		
		readJobStatusList.add(status);
		
		
	}
	
public static void submitProcessJob(ProcessCsvJob myCallable) {
		
		Future<Boolean> status=executorProcessJobService.submit(myCallable);
		
		processJobStatusList.add(status);
		
		
	}
	
}
