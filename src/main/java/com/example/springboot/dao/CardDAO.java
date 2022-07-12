package com.example.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.smartcardio.Card;

public interface CardDAO extends JpaRepository<Card, Integer> {
}
