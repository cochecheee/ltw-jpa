package com.java.project.service;

import java.util.List;

import com.java.project.entity.Video;

public interface IVideoService {
	int count();

	List<Video> findByTitle(String title);

	List<Video> findAll();

	Video findById(int videoId);

	void delete(int videoId) throws Exception;

	void update(Video video);

	void insert(Video video);
}
