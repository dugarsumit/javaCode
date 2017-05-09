package com.example;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Record implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * record update time
	 */
	@Column(nullable = false)
	private Date updated;
	
	/**
	 * time taken to generate this record
	 */
	@Column(nullable = false)
	private Long duration;
	
	@Column(nullable = false)
	private Integer start;
	
	@Column(nullable = false)
	private Integer end;
	
	@Column(nullable = false)
	private Integer primeCount;
	
	@Column(nullable = false)
	private Integer algorithm;

	public Record(Integer a, Integer b, Integer algo) {
		this.start = a;
		this.end = b;
		this.algorithm = algo;
	}
	
	public Record(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getPrimeCount() {
		return primeCount;
	}

	public void setPrimeCount(Integer primeCount) {
		this.primeCount = primeCount;
	}

	public Integer getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Integer algorithm) {
		this.algorithm = algorithm;
	}

}
