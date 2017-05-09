package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumit.Strategy;

@Controller
public class PrimeController {

	@Autowired
	DataService dataService;
	
	@Autowired
	GeneratorService generatorService;
	
	@RequestMapping("/")
	@ResponseBody
	public List<String> home() {
		List<String> msg = new ArrayList<String>();
		msg.add("Prime Generation Wen App!");
		msg.add("API1 : http://localhost:8080/generatePrime?start=1&end=20&algo=1");
		msg.add("API2 : http://localhost:8080/showAllRecords");
		return msg;
	}

	@RequestMapping("/generatePrime")
	@ResponseBody
	public Response generatePrime(@RequestParam(name="start", required = true) Integer start,
			@RequestParam(name="end", required = true) Integer end,
			@RequestParam(name="algo", required = true) Integer algo) {
		
		Response response = new Response();
		String errorMsg = validate(start, end, algo);
		if(!errorMsg.isEmpty()){
			response.setMessage(errorMsg);
			return response;
		}
		long startTime = System.currentTimeMillis();
		List<Integer> primes = generatorService.generatePrimes(start, end, algo);
		long endTime = System.currentTimeMillis();
		Record record = new Record(start,end,algo);
		record.setPrimeCount(primes.size());
		record.setDuration(endTime-startTime);
		record = dataService.save(record);
		response.setPrimes(primes);
		response.setMessage("success");
		List<Record> records = new ArrayList<Record>();
		records.add(record);
		response.setRecords(records);
		return response;
	}
	
	@RequestMapping("/showAllRecords")
	@ResponseBody
	public Response showAllRecords(){
		Response response = new Response();
		List<Record> records = dataService.findAll();
		response.setRecords(records);
		response.setMessage("success");
		return response;
	}
	
	private String validate(Integer start, Integer end, Integer algo) {
		StringBuilder errorMsg = new StringBuilder();
		boolean success = true;
		if (start < 1 || start > Strategy.MAX_RANGE - 1) {
			errorMsg.append("start is out of range ");
			success = false;
		}
		if(end < 1 || end > Strategy.MAX_RANGE - 1){
			if(!errorMsg.toString().isEmpty()){
				errorMsg.append("\n");
			}
			errorMsg.append("start is out of range ");
			success = false;
		}
		if(algo < 1 || algo > 3){
			if(!errorMsg.toString().isEmpty()){
				errorMsg.append("\n");
			}
			errorMsg.append("algo is out of range ");
			success = false;
		}
		if(success)
			return "";
		return errorMsg.toString();
	}

}
