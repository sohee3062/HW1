<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<div class="container-wrapper">
	<div class="container">
		<h2>Product Detail</h2>
		<p>Here is detail information of the product</p>
		<br> <br>
		<div class="row">

			<div class="col-md-6">

				<c:set var="imageFilename"
					value="/resources/images/${product.name}.jpg" />
				<img src="<c:url value="${imageFilename}" />" alt="image"
					style="width: 80%" />
			</div>

			<div class="col-md-6">
				<h3>${product.name}</h3>
				<p>
					<strong> Description : </strong> ${product.description}
				</p>
				<p>
					<strong> Manufacturer : </strong> ${product.manufacturer}
				</p>
				<p>
					<strong> Category : </strong> ${product.category}
				</p>
				<p>
					<strong> Price : </strong> ${product.price}원
				</p>

			</div>

		</div>
	</div>
</div>
<br>
<br>
