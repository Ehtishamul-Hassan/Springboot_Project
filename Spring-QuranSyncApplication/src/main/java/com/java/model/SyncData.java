package com.java.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SyncData {
	
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "audio_id")
	private AudioFile audioFile;
	
	@ManyToOne
	@JoinColumn(name = "text_id")
	private TextFile textFile;
	
	private String word;
	private double startTime;
	private double endTime;

}
