package com.example.dao;

import com.example.entity.Quote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteDao extends JpaRepository<Quote, Long> {
}
