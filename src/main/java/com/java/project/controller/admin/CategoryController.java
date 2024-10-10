package com.java.project.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.java.project.entity.Category;
import com.java.project.service.*;
import com.java.project.service.impl.*;
import com.java.project.utils.Constant;

//add for upload
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 100 // 100MB
)
//ding nghi tat ca url muon thao tac
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/delete", "/admin/category/edit", "/admin/category/update" })
public class CategoryController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// thiet lap bien moi truong
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// when user click
		String url = req.getRequestURI();
		if (url.contains("/admin/category/add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}else if (url.contains("/admin/categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}else if ((url.contains("/admin/category/delete"))) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if ((url.contains("/admin/category/edit"))) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);

			// passing parameter back to views (category-edit.jsp)
			req.setAttribute("cate", category);

			// resp.sendRedirect("/views/"); //?????
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// thiet lap bien moi truong
				req.setCharacterEncoding("UTF-8");
				resp.setCharacterEncoding("UTF-8");

				// whent user click
				String url = req.getRequestURI();
				if (url.contains("/admin/category/insert")) {

					// xu li
					// lay du lieu tu view
					String categoryName = req.getParameter("categoryname");
					int status = Integer.parseInt(req.getParameter("status"));
					String images = req.getParameter("images");
					// dua vao model
					Category cate = new Category();
					cate.setCategoryname(categoryName);
					cate.setStatus(status);
					cate.setImages(images);

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
							cate.setImages(fname);

						} else if (images != null) {
							cate.setImages(images);
						} else {
							cate.setImages("avatar.png");
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					// mvc khong can service va dao
					// truyen model vao insert

					cateService.insert(cate);
					// System.out.println(cate.getImages());
					// tra ve view, chuyen tiep
					resp.sendRedirect(req.getContextPath() + "/admin/categories");
				}else if (url.contains("/admin/category/update")) {

					/*
					 * CategoryModel category = new CategoryModel();
					 * 
					 * int id = Integer.parseInt(req.getParameter("id")); String categoryname =
					 * req.getParameter("categoryname"); int status =
					 * Integer.parseInt(req.getParameter("status")); String images =
					 * req.getParameter("images1");
					 * 
					 * category.setCategoryid(id); category.setCategoryname(categoryname);
					 * category.setStatus(status); category.setImages(images);
					 * 
					 * // xữ lý ảnh cũ
					 * 
					 * 
					 * 
					 * // update database cateService.update(category);
					 * resp.sendRedirect(req.getContextPath() + "/admin/categories");
					 */
					int categoryid = Integer.parseInt(req.getParameter("id"));
					String categoryname = req.getParameter("categoryname");
					int status = Integer.parseInt(req.getParameter("status"));
					String images = req.getParameter("images");

					Category category = new Category();
					category.setCategoryid(categoryid);
					category.setCategoryname(categoryname);
					category.setImages(images);
					category.setStatus(status);

					// lưu hình cũ
					Category cateold = cateService.findById(categoryid);
					String fileold = cateold.getImages();

					// xử lý images
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
							// ghi tên file vào data
							category.setImages(fname);
						} else if (images != null) {
							category.setImages(images);
						} else {
							category.setImages(fileold);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					cateService.update(category);
					resp.sendRedirect(req.getContextPath() + "/admin/categories");
				}
	}

}
