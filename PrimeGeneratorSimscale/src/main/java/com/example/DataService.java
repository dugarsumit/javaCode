package com.example;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service is used for interacting with the datastore.
 * @author sumit
 *
 */
@Service
public class DataService {

	@Autowired
	private RecordRepository recordRepository;
	
	@Transactional(readOnly=true)
	public List<Record> findAll(){
		return (List<Record>) recordRepository.findAll();
	}
	
	@Transactional()
	public Record save(Record record){
		record.setUpdated(new Date());
		recordRepository.save(record);
		return record;
	}
}

