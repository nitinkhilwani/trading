package com.xm.trading.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public interface Constant {
	
	 Integer POOL_SIZE=5; 
	 
	 HashMap<String,Result> resultMap=new HashMap<>();
	 
	 ConcurrentHashMap<String,ArrayList<SharePrice>> resultMapList=new ConcurrentHashMap<>();

}
