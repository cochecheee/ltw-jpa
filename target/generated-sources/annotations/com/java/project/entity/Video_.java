package com.java.project.entity;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Video.class)
public abstract class Video_ {

	public static final String QUERY_VIDEO_FIND_ALL = "Video.findAll";
	public static final String ACTIVE = "active";
	public static final String DESCRIPTION = "description";
	public static final String VIDEO_ID = "videoId";
	public static final String TITLE = "title";
	public static final String CATEGORY = "category";
	public static final String POSTER = "poster";
	public static final String VIEWS = "views";

	
	/**
	 * @see com.java.project.entity.Video#active
	 **/
	public static volatile SingularAttribute<Video, Integer> active;
	
	/**
	 * @see com.java.project.entity.Video#description
	 **/
	public static volatile SingularAttribute<Video, String> description;
	
	/**
	 * @see com.java.project.entity.Video#videoId
	 **/
	public static volatile SingularAttribute<Video, String> videoId;
	
	/**
	 * @see com.java.project.entity.Video#title
	 **/
	public static volatile SingularAttribute<Video, String> title;
	
	/**
	 * @see com.java.project.entity.Video#category
	 **/
	public static volatile SingularAttribute<Video, Category> category;
	
	/**
	 * @see com.java.project.entity.Video
	 **/
	public static volatile EntityType<Video> class_;
	
	/**
	 * @see com.java.project.entity.Video#poster
	 **/
	public static volatile SingularAttribute<Video, String> poster;
	
	/**
	 * @see com.java.project.entity.Video#views
	 **/
	public static volatile SingularAttribute<Video, Integer> views;

}

