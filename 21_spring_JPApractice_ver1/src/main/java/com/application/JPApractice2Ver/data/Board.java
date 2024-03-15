package com.application.JPApractice2Ver.data;

import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@DynamicUpdate
public class Board {
	//엔터티
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;
	
	@Column(nullable = false)
	private String passwd;
	
	
	@Column(nullable = false)
	private String writer;
	
	@Column(nullable = false)
	private String subject;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private Long readCnt;
	
	@Column(nullable = false)
	private Date enrollAt;
	

}
