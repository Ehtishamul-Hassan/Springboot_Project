package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.TextFile;

public interface TextRepository extends JpaRepository<TextFile, Long> {

}
