<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>


<div>
	<!-- CREATE DELETE UPDATE RESET FORM -->
	<form action="${pageContext.request.contextPath}/admin/video/processing" method="post" enctype="multipart/form-data">
		
		<label for="images1">Upload file:</label><br> 
		<input type="file" id="images1" name="images1"> <br> 
		<label for="fname">Video ID:</label>
		<input type="text" id="fname" name="videoid"><br> 
		<label for="fname">Video Title:</label>
		<input type="text" id="fname" name="videotitle"><br> 
		<label for="fname">View counts:</label>
		<input type="text" id="fname" name="views"><br> 
		<label for="category" style="margin-right: 5px;">Category:</label>
        <select id="category" name="categoryname" required>
            <option value="Oppo">Oppo</option>
            <option value="Apple">Apple</option>
        </select><br />
        <label for="description" style="margin-right: 5px;">Description:</label>
        <textarea id="description" name="description" rows="3" cols="30"></textarea><br />
		<input type="radio" id="ston" name="status" value="1"> 
		<label for="css">On</label><br>
		<input type="radio" id="stoff" name="status" value="0"> 
		<label for="javascript">Off</label><br> 
		<input type="submit" value="Create" name="btnsubmit">
		<input type="submit" value="Update" name="btnsubmit">
		<input type="submit" value="Delete" name="btnsubmit">
		<input type="submit" value="Reset" name="btnsubmit">
	</form>
</div>

<hr>
<br>
<table>
	<tr>
		<th>STT</th>
		<th>Video ID</th>
		<th>Images</th>
		<th>Video Title</th>
		<th>Description</th>
		<th>Views</th>
		<th>Category</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listvideo}" var="video" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td>${video.videoId }</td>
			<c:if test="${videp.poster.substring(0,5) == 'https' }">
				<c:url value="${video.poster }" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${video.poster.substring(0,5)!= 'https' }">
				<c:url value="/image?fname=${video.poster }" var="imgUrl"></c:url>
			</c:if>

			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${video.title }</td>
			<td>${video.description }</td>
			<td>${video.views }</td>
			<td>${video.category.categoryname }</td>
			<td>${video.active }</td>
			<td><a
				href="<c:url value='/admin/video/edit?id=${video.videoId }'/>">Edit</a>
				| <a
				href="<c:url value='/admin/video/delete?id=${video.videoId }'/>">Delete</a></td>
		</tr>
	</c:forEach>
</table>