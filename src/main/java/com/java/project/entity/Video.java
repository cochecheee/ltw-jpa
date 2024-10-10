package com.java.project.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Video implements Serializable{

	private static final long serialVersionUID = 1194288729639283020L;
	
	//primary key
	@Id
	@Column(name="VideoId")
	private String videoId;
	
	@Column(name="Active")
	private int active;
	
	@Column(name="Description", columnDefinition = "nvarchar(500) null")
	private String description;
	
	@Column(name="Poster", columnDefinition = "nvarchar(500) null")
	private String poster;
	
	@Column(name="Title", columnDefinition = "nvarchar(500) null")
	private String title;
	
	@Column(name="Views")
	private int views;
	
	 @ManyToOne
	 @JoinColumn(name="CategoryId")
	 private Category category;
}
