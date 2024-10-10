package com.java.project.service.impl;

import java.util.List;

import com.java.project.configs.JPAConfig;
import com.java.project.dao.IVideoDao;
import com.java.project.dao.impl.VideoDao;
import com.java.project.entity.Video;
import com.java.project.service.IVideoService;

import jakarta.persistence.EntityManager;

public class VideoService implements IVideoService{
	IVideoDao videoDao = new VideoDao();
	@Override
	public int count() {
		return videoDao.count();
	}

	@Override
	public List<Video> findByTitle(String title) {
		return videoDao.findByTitle(title);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public Video findById(int videoId) {
		return videoDao.findById(videoId);
	}

	@Override
	public void delete(int videoId) throws Exception {
		videoDao.delete(videoId);
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
	}

	@Override
	public void insert(Video video) {
		videoDao.insert(video);
	}

}
