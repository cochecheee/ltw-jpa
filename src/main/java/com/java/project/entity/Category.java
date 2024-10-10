package com.java.project.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	// primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private int categoryid;

	@Column(name = "CategoryName", columnDefinition = "nvarchar(50) NOT NULL")
	private String categoryname;

	@Column(name = "Images", columnDefinition = "nvarchar(500) NULL")
	private String images;

	@Column(name = "Status")
	private int status;

	@OneToMany(mappedBy = "category")
	private List<Video> videos;
}
