<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- jsp below -->

<form action="${pageContext.request.contextPath}/admin/category/update?id=${cate.categoryid}"
	method="post" enctype="multipart/form-data">
	<label for="fname">Category name:</label><br> <input type="text"
		id="fname" name="categoryname" value="${cate.categoryname }"><br> <label for="lname">Link
		images:</label><br> <input type="text" id="lname" name="images" value="${cate.images }"><br>

	<label for="images1">Upload file:</label><br> <input type="file" id="images1" name="images1">
		 <br> 
		 <c:if test=${cate.status == 1} >
		 	<input type="radio" id="ston" name="status" checked value="1"> 
			<label for="css">On</label>
			<br>
		 </c:if>
		 <c:if test=${cate.status != 1} >
		 	<input type="radio" id="stoff" name="status" checked value="0"> 
			<label for="javascript">Off</label>
		 </c:if>
		<br> <input type="submit" value="Update">
</form>