package com.charter.comm.assign.md.api;

import java.sql.Timestamp;
import java.util.Map;
import java.util.TreeMap;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rewards {

	String customerId;
	Double totalRewards;
	String start;
	String end;
	 
	Map<String, Double> monthlyRewards;
	
	
	
	public Rewards(String customerId, String start, String end) {
		super();
		this.customerId = customerId;
		this.start = start;
		this.end = end;
		monthlyRewards = new TreeMap<String, Double>();
	}


}
