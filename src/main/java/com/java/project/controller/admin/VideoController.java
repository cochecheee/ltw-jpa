package com.java.project.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.java.project.entity.Category;
import com.java.project.entity.Video;
import com.java.project.service.ICategoryService;
import com.java.project.service.IVideoService;
import com.java.project.service.impl.CategoryService;
import com.java.project.service.impl.VideoService;
import com.java.project.utils.Constant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 100 // 100MB
)
//ding nghi tat ca url muon thao tac
@WebServlet(urlPatterns = { "/admin/video/processing", "/admin/videos", "/admin/video/insert", "/admin/video/delete",
		"/admin/video/update" })
public class VideoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();
		IVideoService vidService = new VideoService();
		ICategoryService cateService = new CategoryService();

		if (url.contains("/admin/videos")) {
			List<Video> list = vidService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();
		IVideoService vidService = new VideoService();
		ICategoryService cateService = new CategoryService();

		if (url.contains("/admin/video/processing")) {
			String buttonName = req.getParameter("btnsubmit");
			System.out.println(buttonName);
			if (buttonName.equals("Create")) {
				Video vid = new Video();

				String id = req.getParameter("videoid");
				int active = Integer.parseInt(req.getParameter("status"));
				String description = req.getParameter("description");
				String title = req.getParameter("videotitle");
				int views = Integer.parseInt(req.getParameter("views"));

				String categoryname = req.getParameter("categoryname");
				List<Category> list = cateService.findByCategoryname(categoryname);
				Category cate = list.get(0);

				vid.setVideoId(id);
				vid.setActive(active);
				vid.setDescription(description);
				vid.setTitle(title);
				vid.setViews(views);
				vid.setCategory(cate);

				// xữ lí upload file
				String fname = "";
				String uploadPath = Constant.DIR;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}

				try {
					Part part = req.getPart("images1");
					if (part.getSize() > 0) {
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						// đổi tên file
						int index = filename.lastIndexOf(".");
						String ext = filename.substring(index + 1);
						fname = System.currentTimeMillis() + "." + ext;
						// upload file
						part.write(uploadPath + "/" + fname);
						// ghi ten file vao data
						vid.setPoster(fname);

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				vidService.insert(vid);
				resp.sendRedirect(req.getContextPath() + "/admin/videos");

			} else if (buttonName.equals("Update")) {
				Video vid = new Video();

				String id = req.getParameter("videoid");
				int active = Integer.parseInt(req.getParameter("status"));
				String description = req.getParameter("description");
				String title = req.getParameter("videotitle");
				int views = Integer.parseInt(req.getParameter("views"));

				String categoryname = req.getParameter("categoryname");
				List<Category> list = cateService.findByCategoryname(categoryname);
				Category cate = list.get(0);
				
				vid.setVideoId(id);
				vid.setActive(active);
				vid.setDescription(description);
				vid.setTitle(title);
				vid.setViews(views);
				vid.setCategory(cate);

				// xữ lí upload file
				String fname = "";
				String uploadPath = Constant.DIR;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}

				try {
					Part part = req.getPart("images1");
					if (part.getSize() > 0) {
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						// đổi tên file
						int index = filename.lastIndexOf(".");
						String ext = filename.substring(index + 1);
						fname = System.currentTimeMillis() + "." + ext;
						// upload file
						part.write(uploadPath + "/" + fname);
						// ghi ten file vao data
						vid.setPoster(fname);

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				vidService.update(vid);
				resp.sendRedirect(req.getContextPath() + "/admin/videos");
				
			} else if (buttonName.equals("Delete")) {
				String id = req.getParameter("id");
				try {
					vidService.delete(id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resp.sendRedirect(req.getContextPath() + "/admin/videos");
			} else if (buttonName.equals("Reset")) {
				resp.sendRedirect(req.getContextPath() + "/admin/videos");
			}
		}
	}
	/*
	 * @Override protected void doGet(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException { // thiet lap bien moi truong
	 * req.setCharacterEncoding("UTF-8"); resp.setCharacterEncoding("UTF-8");
	 * 
	 * // when user click String url = req.getRequestURI(); IVideoService vidService
	 * = new VideoService();
	 * 
	 * if (url.contains("/admin/videos")) { List<Video> list = vidService.findAll();
	 * req.setAttribute("listvideo", list);
	 * req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp); }
	 * else if (url.contains("/admin/video/delete")) { String id =
	 * req.getParameter("id"); try { vidService.delete(id); } catch (Exception e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); }
	 * resp.sendRedirect(req.getContextPath() + "/admin/videos"); } }
	 * 
	 * @Override protected void doPost(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException { // thiet lap bien moi truong
	 * req.setCharacterEncoding("UTF-8"); resp.setCharacterEncoding("UTF-8");
	 * 
	 * // when user click String url = req.getRequestURI(); IVideoService vidService
	 * = new VideoService(); ICategoryService cateService = new CategoryService();
	 * 
	 * if(url.contains("/admin/video/insert")) { ADD TO DATABASE // STEP 1: get data
	 * String videoId = req.getParameter("videoId"); String categoryname =
	 * req.getParameter("categoryname"); String videoTitle =
	 * req.getParameter("title"); int views =
	 * Integer.parseInt(req.getParameter("views")); String description =
	 * req.getParameter("description"); int active =
	 * Integer.parseInt(req.getParameter("status")); System.out.println(videoId);
	 * 
	 * // STEP 2: create video entity Video video = new Video();
	 * video.setVideoId(videoId); video.setActive(active);
	 * video.setTitle(videoTitle); video.setDescription(description);
	 * video.setViews(views);
	 * 
	 * // xữ lí upload file String fname = ""; String uploadPath = Constant.DIR;
	 * File uploadDir = new File(uploadPath); if (!uploadDir.exists()) {
	 * uploadDir.mkdir(); }
	 * 
	 * try { Part part = req.getPart("images1"); if (part.getSize() > 0) { String
	 * filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
	 * // đổi tên file int index = filename.lastIndexOf("."); String ext =
	 * filename.substring(index + 1); fname = System.currentTimeMillis() + "." +
	 * ext; // upload file part.write(uploadPath + "/" + fname); // ghi ten file vao
	 * data video.setPoster(fname);
	 * 
	 * } } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
	 * 
	 * // xử lí category List<Category> cate =
	 * cateService.findByCategoryname(categoryname); if(cate != null) {
	 * video.setCategory(cate.get(0)); }
	 * 
	 * // STEP 3: add to database and redirect to home vidService.insert(video);
	 * resp.sendRedirect(req.getContextPath() + "/admin/videos"); } }
	 */

}
