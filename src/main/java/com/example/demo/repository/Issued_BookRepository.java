package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Issued_Book;

public interface Issued_BookRepository extends JpaRepository<Issued_Book, Long>{

}
