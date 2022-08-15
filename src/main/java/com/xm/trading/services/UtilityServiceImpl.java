package com.xm.trading.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.xm.trading.dto.Constant;
import com.xm.trading.dto.Result;
import com.xm.trading.job.JobProcessor;
import com.xm.trading.job.ReadCsvJob;

@Service
public class UtilityServiceImpl {

	@Value("file:*/**/resources/*.csv")
	private Resource[] resources;

	public void readAllFileJob() {
		String basepPath = "src/main/resources/";

		List<String> filenames = getResourceNames();

		for (String filename : filenames) {
			String path = basepPath + filename;

			ReadCsvJob fileJob = new ReadCsvJob(filename,path);
			JobProcessor.submitJob(fileJob);
			
		}

	}

	public HashMap<String, Result> getResultByName(String cryptoName) {
		HashMap<String, Result> resultMap = new HashMap<>();
		if (Constant.resultMap.containsKey(cryptoName)) {
			resultMap.put(cryptoName, Constant.resultMap.get(cryptoName));
		}
		return resultMap;
	}

	public LinkedHashMap<String, Result> getResultByOrder(String sortOrder) {
		LinkedHashMap<String, Result> resultMap = new LinkedHashMap<>();

		List<Map.Entry<String, Result>> entryList = new ArrayList<Map.Entry<String, Result>>(Constant.resultMap.entrySet());

		// Sort the list using lambda expression
		
		if(sortOrder.equalsIgnoreCase("ASC")) {
			Collections.sort(entryList,(o1, o2) -> o1.getValue().getNormalizeValue().compareTo(o2.getValue().getNormalizeValue()));
		}else if(sortOrder.equalsIgnoreCase("DESC")){
	       Collections.sort(entryList,(o1, o2) -> o2.getValue().getNormalizeValue().compareTo(o1.getValue().getNormalizeValue()));
		}
		
		// put data from sorted list to hashmap
		for (Map.Entry<String, Result> aa : entryList) {
			resultMap.put(aa.getKey(), aa.getValue());
		}

		return resultMap;
	}

	public List<String> getResourceNames() {
		return Arrays.stream(resources).map(Resource::getFilename).collect(Collectors.toList());
	}

}
