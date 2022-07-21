package com.charter.comm.assign.md.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.charter.comm.assign.md.api.Rewards;
import com.charter.comm.assign.md.api.Transaction;
import com.charter.comm.assign.md.repo.RewardRepository;
import com.charter.comm.assign.utils.RewardDateUtils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RewardService<T> {
	
	private static final Logger logger = LoggerFactory.getLogger(RewardService.class);
	
	
	private RewardRepository rewardRepository;
	
	
	@Autowired
	public RewardService(RewardRepository rewardRepository) {
		super();
		this.rewardRepository = rewardRepository;
	}
	
	public List<Transaction> getTransactions(String customerId, 
												Optional<String> optionalStart,
												Optional<String> optionalEnd) throws Exception {
		
		long enterTS = System.currentTimeMillis();
		
		Map<String, String> map = null;
		
		logger.info("Entering getTransactions ("+customerId+") :" + enterTS); 
	
		try {
			map = validateTimeRange(optionalStart, optionalEnd);
		} catch (Exception e) {
			logger.error("validation problem " + e.getMessage());
			throw e;
		}
		
		String start = map.get("start");
		String end = map.get("end");
		
		List<Transaction> transactions = getTransactions(customerId, start, end);

		long exitTS = System.currentTimeMillis();
		logger.info("Exiting getTransactions ("+customerId+") took :" + (exitTS - enterTS) + " ms"); 
		
		return transactions;

	}

	
	private List<Transaction> getTransactions(String customerId, 
												String start,
												String end) throws Exception {
		
		List<Transaction> transactions = rewardRepository.getTransactions(customerId, start, end);

		return transactions;
		
	}

		
	public Rewards getRewards(String customerId, 
										Optional<String> optionalStart,
										Optional<String> optionalEnd) throws Exception {
		
		long enterTS = System.currentTimeMillis();
		Map<String, String> map = null;
		
		logger.info("Entering getRewards ("+customerId+") :" + enterTS); 
		
		try {
			map = validateTimeRange(optionalStart, optionalEnd);
		} catch (Exception e) {
			logger.error("validation problem " + e.getMessage());
			throw e;
		}
		
		String start = map.get("start");
		String end = map.get("end");
		
		List<Transaction>  transactions = getTransactions(customerId, start, end);
		
		Rewards rewards = getRewards(transactions, customerId, start, end);
		
		System.out.println("rewards :" + rewards);
		
		long exitTS = System.currentTimeMillis();
		logger.info("Exiting getRewards ("+customerId+") took :" + (exitTS - enterTS) + " ms"); 

		
		return rewards;
	}
	
	
	private Rewards getRewards(List<Transaction> transactions, String customerId, String start, String end) {
		
		Rewards rewards = new Rewards( customerId, start, end);
		
		// monthly sum
		transactions.forEach((t) -> {
			
			Double transactionReward = calculateTransactionReward(t.getTotalAmount());
			
			String month = RewardDateUtils.getMonth(t.getTransactionTs());
			
			Double rewardAmount = rewards.getMonthlyRewards().get(month);
			
			if ( rewardAmount == null) {
				rewards.getMonthlyRewards().put(month, transactionReward);
			} else {
				rewards.getMonthlyRewards().put(month, Double.sum(rewardAmount, transactionReward));
			}
			
		});
		
		// total of all the months
		Double total = rewards.getMonthlyRewards().values().stream().mapToDouble(d -> d).sum();
		rewards.setTotalRewards(total);
		
		return rewards;
	}

	private Double calculateTransactionReward(Double totalAmount) {
		
		Double reward = 0.0;
		
		if (totalAmount.doubleValue() > 50) {
			
			reward = reward.sum(reward, 1.0);
			
			if (totalAmount.doubleValue() > 100) {
				
				Double over100 = totalAmount - 100;
				
				reward = reward.sum(reward,  (over100 * 2.0) );
			}
			
		}
		
		return reward;
	}

	private Map<String, String> validateTimeRange(Optional<String> start, Optional<String> end) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		
		if (start.isPresent()) {

			try {

				//Date startDate = formatTsZ.parse("2021-01-01T05:00:00.000Z");
				Date startDate = RewardDateUtils.getTimestampFormat().parse(start.get());
				
				map.put("start", start.get());
				
			} catch (ParseException e) {
				throw new Exception("Invalid format for start " + start.get() + " should be like this " + RewardDateUtils.getTimestampFormatString());
			}

			
			if (end.isPresent()) {
				
				// validate start and end
 				try {

 					//Date startDate = formatTsZ.parse("2021-01-01T05:00:00.000Z");
 					Date endDate = RewardDateUtils.getTimestampFormat().parse(end.get());
 					
 					map.put("end", end.get());
 					
 				} catch (ParseException e) {
 					throw new Exception("Invalid format for end " + end.get() + " should be like this " + RewardDateUtils.getTimestampFormatString());
 				}
				
			} else {
				
				// then end date is 3 months from start 
				
				// TODO
				// to implement
				
				
			}
			
		} else {
			
			if (end.isPresent()) {
				
				// then start date is 3 months from end 
				
 				try {

 					//Date startDate = formatTsZ.parse("2021-01-01T05:00:00.000Z");
 					Date endDate = RewardDateUtils.getTimestampFormat().parse(end.get());
 					
 				} catch (ParseException e) {
 					throw new Exception("Invalid format for end " + end.get() + " should be like this " + RewardDateUtils.getTimestampFormatString());
 				}

				// TODO
				// to implement
				
				
 				
				
			} else {
				
				// end is today
				// start is 3 months from today
				
				// TODO
				// to implement
				
				
				
			}
			
			
		}
		
		String startDate = map.get("start");
		String endDate = map.get("end");
		
		if (startDate.compareTo(endDate) > 0) {
			throw new Exception("Start date is past End date :" + startDate + " " + endDate);
		} 

 		return map;
	}

}
