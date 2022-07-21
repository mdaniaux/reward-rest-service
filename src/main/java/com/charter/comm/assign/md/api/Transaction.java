package com.charter.comm.assign.md.api;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Transaction {

	Long id;
	String customerId;
	Timestamp transactionTs;
	Double totalAmount;
	
	
	
}
