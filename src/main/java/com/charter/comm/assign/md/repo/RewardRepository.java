package com.charter.comm.assign.md.repo;

import java.util.List;

import com.charter.comm.assign.md.api.Transaction;
import com.charter.comm.assign.md.api.mapper.TransactionRowMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;


@Repository
public class RewardRepository {

	private static final Logger logger = LoggerFactory.getLogger(RewardRepository.class);
	
	private final JdbcTemplate jdbcTemplate;
	
	private final String SQL_TRANSACTION = "select id, customerId, transactionTs, totalAmount from trx where customerId = '{customerId}' and transactionTs >= '{start}' and transactionTs <= '{end}' order by transactionTs ASC";
	
	
	

	public RewardRepository(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	public List<Transaction> getTransactions(String customerId, String start, String end) {
		
		String sql = SQL_TRANSACTION.replaceFirst("\\{customerId\\}", customerId);	  
		sql = sql.replaceFirst("\\{start\\}", start);	  
		sql = sql.replaceFirst("\\{end\\}", end);	
		
		logger.info("sql_transaction :" + sql);
		
		List<Transaction> transactions = jdbcTemplate.query(sql, new TransactionRowMapper<Transaction>());

		return transactions;
	}

}
