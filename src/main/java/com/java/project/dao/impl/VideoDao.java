package com.java.project.dao.impl;

import java.util.List;

import com.java.project.configs.JPAConfig;
import com.java.project.dao.ICategoryDao;
import com.java.project.dao.IVideoDao;
import com.java.project.entity.Category;
import com.java.project.entity.Video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class VideoDao implements IVideoDao{

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(v) FROM Video v";
		Query query = enma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<Video> findByTitle(String title) {
		String jpql = "select v from Video v where v.title like :title";
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public Video findById(int videoId) {
		EntityManager enma = JPAConfig.getEntityManager();
		Video vid = enma.find(Video.class, videoId);
		return vid;
	}

	@Override
	public void delete(int videoId) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Video video = enma.find(Video.class, videoId);
			if(video != null) {
				enma.remove(video);
			}else {
				throw new Exception("Not Found!");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			enma.merge(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void insert(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			enma.persist(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	public static void main(String[] args) {
		// test insert video
		ICategoryDao cateDao = new CategoryDao();
		Category cate = cateDao.findById(1);
		
		Video vid = new Video();
		vid.setActive(0);
		vid.setCategory(cate);
		vid.setDescription("Nothing to say");
		vid.setPoster(null);
		vid.setTitle("Nope video");
		vid.setVideoId("vid01");
		vid.setViews(0);
		
		IVideoDao vidDao = new VideoDao();
		vidDao.insert(vid);
	}
}
