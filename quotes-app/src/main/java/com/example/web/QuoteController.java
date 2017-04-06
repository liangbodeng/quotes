package com.example.web;

import com.example.dao.QuoteDao;
import com.example.entity.Quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuoteController {
	@Autowired
	QuoteDao quoteDao;

	@GetMapping("/quotes")
	List<Quote> getAllQuotes() {
		return quoteDao.findAll();
	}
}
