package com.charter.comm.assign.md.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.charter.comm.assign.md.api.Transaction;

public class TransactionRowMapper <T extends Transaction> implements RowMapper<T> {

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Transaction transaction = new Transaction();
		
		transaction.setId(rs.getLong("id"));
		transaction.setCustomerId(rs.getString("customerId"));
		transaction.setTransactionTs(rs.getTimestamp("transactionTs"));
		transaction.setTotalAmount(rs.getDouble("totalAmount"));
		
		return (T)transaction;
	}

}
