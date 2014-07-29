<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<head>
	<jsp:include page="../header/header.jsp"></jsp:include>  
</head>
<body>
	<!-- TOP BAR -->
	<div id="top-bar">
		
		<div class="page-full-width">&nbsp;
			<!-- <a href="#" class="round button dark ic-left-arrow image-left ">Return to website</a> -->
		</div> <!-- end full-width -->	
	</div> <!-- end top-bar -->
	<!-- HEADER -->
	<div id="header">
		<div class="page-full-width cf">
			<div id="login-intro" class="fl">
				<h1>National Insurance Company - Admin Login to NIC</h1>
				<h5>Enter your credentials below</h5>
			</div> <!-- login-intro -->
			
			<!-- Change this image to your own company's logo -->
			<!-- The logo will automatically be resized to 39px height. -->
			<a href="javascript:void(0);" id="company-branding" class="fr"><img src="images/company-logo.png" alt="Blue Hosting" /></a>
			
		</div> <!-- end full-width -->	

	</div> <!-- end header -->

	<!-- MAIN CONTENT -->
	<div id="content">
	
		<form action="adminlogin.html" method="POST" id="login-form">
			<c:if test="${errorMsg !=null}" >
				<div class="${errorClass}">${errorMsg}</div>
			</c:if>
			<fieldset>

				<p>
					<label for="login-username">username</label>
					<input type="text" id="login-username" name="username" class="round full-width-input" autofocus />
				</p>

				<p>
					<label for="login-password">password</label>
					<input type="password" id="login-password" name="password" class="round full-width-input"  />
				</p>
				
				<!-- <p>I've <a href="#">forgotten my password</a>.</p> -->
				<input type="submit" value="Log In"  class="button round blue image-right ic-right-arrow" />

			</fieldset>
		</form>
		
	</div> <!-- end content -->
	
	<!-- FOOTER -->
		<jsp:include page="../footer/footer.jsp"></jsp:include>
	 <!-- end footer -->

</body>
</html>