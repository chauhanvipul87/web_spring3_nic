<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div id="top-bar">
		
		<div class="page-full-width cf">
			<ul id="nav" class="fl">
				<li class="v-sep"><a href="index.html" class="round button dark ic-left-arrow image-left">Go to website</a></li>
				<li class="v-sep"><a href="adminhome.html" class="round button dark ic_left_home image-left">&nbsp;&nbsp;Manage Staff Member</a></li> 
				<li class="v-sep"><a href="viewUserWork.html" class="round button dark icon_view-data image-left">&nbsp;&nbsp;View /Search Work By User </a></li>
				<li class="v-sep"><a href="javascript:void(0);" class="round button dark menu-user image-left">Logged in as <strong>${username}</strong></a>
					<ul>
						<li><a href="userProfile.html">My Profile</a></li>
						<li><a href="adminlogout.html">Log out</a></li>
					</ul> 
				</li>
				<li><a href="adminlogout.html" class="round button dark menu-logoff image-left">Log out</a></li>
				
				
			</ul> <!-- end nav -->

					
			<!-- <form action="#" method="POST" id="search-form" class="fr">
				<fieldset>
					<input type="text" id="search-keyword" class="round button dark ic-search image-right" placeholder="Search..." />
					<input type="hidden" value="SUBMIT" />
				</fieldset>
			</form> -->

		</div> <!-- end full-width -->	
	
	</div> 