package com.charter.comm.assign.md.controller;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.charter.comm.assign.md.api.Rewards;
import com.charter.comm.assign.md.api.Transaction;
import com.charter.comm.assign.md.service.RewardService;

@RestController
@RequestMapping(produces = {"application/json"})
public class RewardController {

	
	private static final Logger logger = LoggerFactory.getLogger(RewardController.class);
	
	
	private final RewardService<?> rewardService;
	
	@Autowired
	public RewardController(RewardService<?> rewardService) {
		this.rewardService = rewardService;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/transactions", method = RequestMethod.GET, params = {"customerId"})
	public List<Transaction> getTransactions(final @RequestParam(name = "customerId", required = true) String customerId,
			final @RequestParam(name = "start" , required = false) Optional<String> optionalStart,
			final @RequestParam(name = "end", required = false) Optional<String> optionalEnd) throws IOException {
		
		logger.info("customerId     :" + customerId);
		logger.info("start          :" + optionalStart);
		logger.info("end            :" + optionalEnd);

		
		List<Transaction> operations;
		try {
			operations = rewardService.getTransactions(customerId, optionalStart, optionalEnd);
		} catch (Exception e) {
			throw new IOException(e);
		}
		
		return operations;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/rewards", method = RequestMethod.GET, params = {"customerId"})
	public Rewards getRewards(final @RequestParam(name = "customerId", required = true) String customerId,
			final @RequestParam(name = "start" , required = false) Optional<String> optionalStart,
			final @RequestParam(name = "end", required = false) Optional<String> optionalEnd) throws IOException {
		
		logger.info("customerId     :" + customerId);
		logger.info("start          :" + optionalStart);
		logger.info("end            :" + optionalEnd);

		
		Rewards rewards;
		try {
			rewards = rewardService.getRewards(customerId, optionalStart, optionalEnd);
		} catch (Exception e) {
			throw new IOException(e);
		}
		
		return rewards;
	}
	
}
