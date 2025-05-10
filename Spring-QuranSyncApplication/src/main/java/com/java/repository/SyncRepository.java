package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.SyncData;

public interface SyncRepository extends JpaRepository<SyncData, Long> {
	
	List<SyncData> findByAudioFileId(Long audioId);

}
