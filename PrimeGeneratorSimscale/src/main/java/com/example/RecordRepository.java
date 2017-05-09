package com.example;

import org.springframework.data.repository.CrudRepository;

/**
 * This is the data access layer. Write your data store queries here.
 * @author sumit
 *
 */
public interface RecordRepository extends CrudRepository<Record, Long> {
	
}