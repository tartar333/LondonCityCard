package com.mcann.service;

import com.mcann.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
}