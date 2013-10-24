<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>


<html>
<head>
	<title>Student Teacher </title>
	
<style>
   
#tabs ul#main {
	list-style: none;
	padding:0;
	margin:0;
	background:purple;
}


#tabs li#main {
	display: inline;
	border: solid;
	border-width: 1px 1px 0 1px;
	margin: 0 0.5em 0 0;
}


#tabs li a {
	padding: 0 0em;
}

#teacher-form{
border: solid;
}


body { font-size: 100%; }
label, input { display:block; }
input.text { margin-bottom:12px; width:95%; padding: .4em; }
fieldset { padding:0; border:0; margin-top:25px; }
h1 { font-size: 1.2em; margin: .6em 0; }
div#users-contain { width: 350px; margin: 20px 0; }
div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
.ui-dialog .ui-state-error { padding: .3em; }
.validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
</head>


<body id="mainbody">
    <div id="tabs">
               <ul id="main">
                         <li id="main"><a href="#home">Home</a></li>
                         <li id="main"><a href="#teacher">Teacher</a></li>
                         <li id="main"><a href="#student">Student</a></li>
                         <li id="main"><a href="#coach">Coach</a></li>
                </ul>
                <div id="home">
                     <p >Welcome To Student Teacher Relation Site</p>
                     <ul>
                          <li><a href="#teacher" id="clickmeteacher">Teacher's Click Here</a></li>
                          <li><a href="#student" id="clickmestudent">Students Zone</a></li>
                           <li><a href="#coach" id="clickmecoach">Coach see what u got</a></li>
                     </ul>
               </div>
                <div id="teacher">
                 <p>Welcome Teachers</p>
                 <button id="new-teacher">Sign Up</button>
                 <select id="tlist">
                        <c:forEach items="${teacherlist}" var="teacher" >
            	               <option  value="${teacher.id}">${teacher.name}</option>
                        </c:forEach>       	
                 </select>
                 
                 <div id="teachermaindiv" style="display: none">
			                    <p>Teacher Info</p>
								<table>
									<th>Name</th>
									<th>Email</th>
									<tr>
										<td><span id="teachername"></span></td>
										<td><span id="teacheremail"></span></td>
									</tr>
								</table>
								<br/>
								<p> Your Student</p>
								<table id="tslist">
				
								</table>
								<br/>
								<p>Students That Can Be Added</p>
								<table id="studenttobeadded">
				
								</table>
								<table id="teachernotification">
									
									
								</table>
					</div>
                </div>
                <div id="student">
                      <p>Welcome Students</p>
                      <button id="new-student">Sign Up</button>
                      <select id="slist">
                             <c:forEach items="${studentlist}" var="student" >
            	                 <option  value="${student.id}">${student.name}</option>
                             </c:forEach>       	
                      </select>

			          <div id="studentmaindiv" style="display: none">
			                    <p>Student Info</p>
								<table>
									<th>Name</th>
									<th>Email</th>
									<tr>
										<td><span id="name"></span></td>
										<td><span id="email"></span></td>
									</tr>
								</table>
								<br/>
								<p>Teachers You Are Enrolled With</p>
								<table id="stlist">
				
								</table>
								<br/>
								<p>Teachers That Can Be Added</p>
								<table id="teachertobeadded">
				
								</table>
								
								<table id="studentnotification">
									
									
								</table>
								
					</div>
		     </div>
		     <div id="coach">
                      <p>Welcome Coach</p>
                      <button id="new-coach">Sign Up</button>
                      <select id="clist">
                             <c:forEach items="${coachlist}" var="coach" >
            	                 <option  value="${coach.id}">${coach.name}</option>
                             </c:forEach>       	
                      </select>

			          <div id="coachmaindiv" style="display: none">
			                    <p>Coach Info</p>
								<table>
									<th>Name</th>
									<th>Email</th>
									<tr>
										<td><span id="coachname"></span></td>
										<td><span id="coachemail"></span></td>
									</tr>
								</table>
								<br/>
								<p>you are coaching students:</p>
								<table id="sclist">
				
								</table>
								<br/>
								<!-- <p>Teachers That Can Be Added</p>
								<table id="teachertobeadded">
				
								</table> -->
					</div>
		     </div>
   </div>
	<div id="teacher-form" title="Create new Teacher">
			<p class="validateTips">All form fields are required.</p>
			<form>
				<fieldset>
					<label for="name">Name</label>
					<input type="text" name="name" id="tname" class="text ui-widget-content ui-corner-all" />
					<label for="email">Email</label>
					<input type="text" name="email" id="temail" value="" class="text ui-widget-content ui-corner-all" />
				</fieldset>
			</form>
	</div>

	<div id="student-form" title="Create new Student">
			<p class="validateTips">All form fields are required.</p>
			<form>
				<fieldset>
					<label for="name">Name</label>
					<input type="text" name="name" id="sname" class="text ui-widget-content ui-corner-all" />
					<label for="email">Email</label>
					<input type="text" name="email" id="semail" value="" class="text ui-widget-content ui-corner-all" />
				</fieldset>
			</form>
	</div>
	
	<div id="coach-form" title="Create new Coach">
			<p class="validateTips">All form fields are required.</p>
			<form>
				<fieldset>
					<label for="name">Name</label>
					<input type="text" name="name" id="cname" class="text ui-widget-content ui-corner-all" />
					<label for="email">Email</label>
					<input type="text" name="email" id="cemail" value="" class="text ui-widget-content ui-corner-all" />
				</fieldset>
			</form>
	</div>
	<div id="errorMessage">
	 
	</div>


	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script type="text/javascript">

	$("docment").ready(function(){
	
		var a=$("#tabs").tabs();
		$("#tabs").bind("tabsselect", function(event, ui) { window.location.hash = ui.tab.hash; });
		$("a#clickmeteacher").click(function(){
					a.tabs("option", "active", 1);
					$("#tabs").bind("tabsselect", function(event, ui) { window.location.hash = ui.tab.hash; });
		});
		$("a#clickmestudent").click(function(){
			a.tabs("option", "active", 2);
			$("#tabs").bind("tabsselect", function(event, ui) { window.location.hash = ui.tab.hash; });
		});
		$("a#clickmecoach").click(function(){
			a.tabs("option", "active", 3);
			$("#tabs").bind("tabsselect", function(event, ui) { window.location.hash = ui.tab.hash; });
		});
		$( "#coach-form" ).dialog({
			autoOpen: false,
			height: 300,
			width: 350,
			modal: true,
			buttons: {
			"Create an account":function(){
				var coach = new Object();
				var form=$( "#coach-form" );
				student.name=$( "#cname" ).val();
				student.email=$("#cemail").val();
				
				
				
				 $.ajax({
					 url: 'coach/coach',
					 type: 'POST',
					 contentType: "application/json",
					 dataType: "json",
					 data:JSON.stringify(coach),
					 success : function(response){
						 alert(response.name+" added");
						 $("#clist").append($('<option></option>').val(response.id).html(response.name));
						 form.dialog( "close" );
						 console.log(this.response);
					      }	,
			          error:function(response){
				                alert("hiii");
				         }		 
				 });
				
			} ,
			Cancel: function() {
				$( this ).dialog( "close" );
			}
			},
			close: function() {
				$( this ).dialog( "close" );
			}
			
			});
		$( "#student-form" ).dialog({
			autoOpen: false,
			height: 300,
			width: 350,
			modal: true,
			buttons: {
			"Create an account":function(){
				var student = new Object();
				var form=$( "#student-form" );
				student.name=$( "#sname" ).val();
				student.email=$("#semail").val();
				
				 $.ajax({
					 url: 'student/newstudent',
					 type: 'POST',
					 contentType: "application/json",
					 dataType: "json",
					 data:JSON.stringify(student),
					 success : function(response){
						 alert(response.name+" added");
						 $("#slist").append($('<option></option>').val(response.id).html(response.name));
						 form.dialog( "close" );
					},
					error: function(response){
						//form.dialog('close');
					    $( "#errorMessage" ).html(response.responseText);
					    $( "#errorMessage" ).dialog();
					    $( "#errorMessage").css("background","none repeat scroll 0 0 #808080");
					    $( "#errorMessage").prev().children('button').css("position","absolute");
					    $( "#errorMessage").prev().children('button').css("margin","31 239");
					    
					   }			 
				 });
				
			} ,
			Cancel: function() {
			$( this ).dialog( "close" );
			}
			},
			close: function() {
			$( this ).dialog( "close" );
			}
			
			});
		$( "#teacher-form" ).dialog({
			autoOpen: false,
			height: 300,
			width: 350,
			modal: true,
			buttons: {
			"Create an account":function(){
				var teacher = new Object();
				var form=$("#teacher-form");
				teacher.name=$( "#tname" ).val();
				teacher.email=$("#temail").val();
				
				 $.ajax({
					 url: 'teacher/newteacher',
					 type: 'POST',
					 contentType: "application/json",
					 dataType: "json",
					 data:JSON.stringify(teacher),
					 success : function(response){
						 if(response==null){
							 alert("adding new teacher failed");
							 }
						 else{
						 alert(response.name+" added");
						$("#tlist").append($('<option></option>').val(response.id).html(response.name));
						    }
						 form.dialog( "close" ); 
					     },
					error: function(response){
							//form.dialog('close');
						    $( "#errorMessage" ).html(response.responseText);
						    $( "#errorMessage" ).dialog();
						    $( "#errorMessage").css("background","none repeat scroll 0 0 #808080");
						    $( "#errorMessage").prev().children('button').css("position","absolute");
						    $( "#errorMessage").prev().children('button').css("margin","31 239");
						    
						   }			 
				 });
				
			} ,
			Cancel: function() {
			$( this ).dialog( "close" );
			}
			},
			close: function() {
			$( this ).dialog( "close" );
			}
			
			});
		$( "#new-teacher" ).button().click(function() {
				$( "#teacher-form" ).dialog( "open" );
		});
		$( "#new-student" ).button().click(function() {
				$( "#student-form" ).dialog( "open" );
		});
		$( "#new-coach" ).button().click(function() {
			$( "#coach-form" ).dialog( "open" );
	    });
		
		$('#stlist').on('click', '[name=remove_teacher]',function(e) {
			var removeTeacherBtn = $(this);
	 	    var teacherid=$(e.target).closest('tr').find("td:nth-child(1)").text();
	 	    var studentid   =$("#slist").val();
	 	    $.get( "student/removeteacher","studentid="+studentid+"&teacherid="+teacherid, function( teacherDto ) {
	 	        
	 	        removeTeacherBtn.parent().parent().remove();
	            $('table#teachertobeadded').append("<tr><td>"+teacherDto.id+"</td><td>"+teacherDto.name+"</td><td>"+teacherDto.email+"</td>"+"<td><a name=\"add_teacher\" href='#'>Add</a></td></tr>");
	 	        });
	 	    
	 	});

	 $('#teachertobeadded').on('click', '[name=add_teacher]', function(e) {
		    var addTeacherBtn = $(this);
		    var teacherid=$(e.target).closest('tr').find("td:nth-child(1)").text();
		    var studentid   =$("#slist").val();
		    $.get( "student/addteacherforstudent","teacherid="+teacherid+"&studentid="+studentid, function( teacherDto ) {
		    	    
		            addTeacherBtn.parent().parent().remove();
		            $('table#stlist').append("<tr><td>"+teacherDto.id+"</td><td>"+teacherDto.name+"</td><td>"+teacherDto.email+"<td><a name=\"remove_teacher\" href='#'>Remove</a></td></tr>");
		        });
		    
		});
	 $('#studenttobeadded').on('click', '[name=add_student]', function(e) {
		    var addStudentBtn = $(this);
		    var studentid=$(e.target).closest('tr').find("td:nth-child(1)").text();
		    var teacherid   =$("#tlist").val();
		    $.get( "teacher/addstudentforteacher","teacherid="+teacherid+"&studentid="+studentid, function( studentDto ) {
		    	    
		            addStudentBtn.parent().parent().remove();
		            $('table#tslist').append("<tr><td>"+studentDto.id+"</td><td>"+studentDto.name+"</td><td>"+studentDto.email+"<td><a name=\"remove_student\" href='#'>Remove</a></td></tr>");
		        });
		    
		});
	 $('#tslist').on('click', '[name=remove_student]',function(e) {
			var removeStudentBtn = $(this);
	 	    var studentid=$(e.target).closest('tr').find("td:nth-child(1)").text();
	 	    var teacherid   =$("#tlist").val();
	 	    $.get( "teacher/removestudent","studentid="+studentid+"&teacherid="+teacherid, function( studentDto ) {
	 	        
	 	        removeStudentBtn.parent().parent().remove();
	            $('table#studenttobeadded').append("<tr><td>"+studentDto.id+"</td><td>"+studentDto.name+"</td><td>"+studentDto.email+"</td>"+"<td><a name=\"add_student\" href='#'>Add</a></td></tr>");
	 	        });
	 	    
	 	});
  
	 /*$("document").on("click","a",function(){
		alert("hiiii");
	});*/
	$("#slist").change(function() {
						
						var selected=$(this).val();
						//alert(selected);
						$.get( "student/getstudent","studentId="+selected, function( data ) {
		                                  //alert(data.studentDto.name);
										 $("#studentmaindiv").css("display","block");
										 $("#name").html(data.studentDto.name);
										 $("#email").html(data.studentDto.email);
										 $('table#stlist').empty();
										 $('table#stlist').append("<th>ID</th><th>Name</th><th>Email</th>");
		                                 var tr;
		                                 for (var i = 0; i < data.studentDto.teachers.length; i++) {
									    	 tr = $('<tr/>');
									    	 tr.append("<td>" + data.studentDto.teachers[i].id + "</td>");
									         tr.append("<td>" + data.studentDto.teachers[i].name + "</td>");
									         tr.append("<td>" + data.studentDto.teachers[i].email + "</td>");
									         tr.append("<td><a name=\"remove_teacher\" href='#'>Remove</a></td>");
									         $('table#stlist').append(tr);
									     }
						
								        $('#teachertobeadded').empty();
								       $('#teachertobeadded').append("<th>ID</th><th>Name</th><th>Email</th>");
								
								       for (var i = 0; i < data.teachers_to_be_added.length; i++) {
								      	   tr = $('<tr/>');
								      	   tr.append("<td>" + data.teachers_to_be_added[i].id + "</td>");
								           tr.append("<td>" + data.teachers_to_be_added[i].name + "</td>");
								           tr.append("<td>" + data.teachers_to_be_added[i].email + "</td>");
								           tr.append("<td><a name=\"add_teacher\" href='#'>Add</a></td>");
								           $('#teachertobeadded').append(tr);
								       } 
								       $("#studentnotification").empty();
								       $("#studentnotification").append("<th>Your Notifications</th>")
								       for (var i = 0; i < data.studentDto.notifications.length; i++) {
								    	   var html="<tr><td>" + data.studentDto.notifications[i] + "</td></tr>";
								           $('#studentnotification').append(html);
								       } 
						});
						
	});
	


	$("#tlist").change(function() {
		
		var selected=$(this).val();
		
		$.get( "teacher/getteacher","teacherId="+selected, function( data ) {
                          alert(data.teacherDto.name);
						 $("#teachermaindiv").css("display","block");
						 $("#teachername").html(data.teacherDto.name);
						 $("#teacheremail").html(data.teacherDto.email);
						 $('table#tslist').empty();
						 $('table#tslist').append("<th>ID</th><th>Name</th><th>Email</th>");
                         var tr
                         for (var i = 0; i < data.teacherDto.students.length; i++) {
					    	 tr = $('<tr/>');
					    	 tr.append("<td>" + data.teacherDto.students[i].id + "</td>");
					    	 tr.append("<td>" + data.teacherDto.students[i].name + "</td>");
					    	 tr.append("<td>" + data.teacherDto.students[i].email + "</td>");
					    	 tr.append("<td><a name=\"remove_student\" href='#'>Remove</a></td>");
					    	 $('table#tslist').append(tr);
					    	 }
				    	 
					         
					       
					     
		
				        $('#studenttobeadded').empty();
				        $('#studenttobeadded').append("<th>ID</th><th>Name</th><th>Email</th>");
				      
				       for (var i = 0; i < data.students_to_be_added.length; i++) {
				      	   tr = $('<tr/>');
				      	   tr.append("<td>" + data.students_to_be_added[i].id + "</td>");
				           tr.append("<td>" + data.students_to_be_added[i].name + "</td>");
				           tr.append("<td>" + data.students_to_be_added[i].email + "</td>");
				           tr.append("<td><a name=\"add_student\" href='#'>Add</a></td>");
				           $('#studenttobeadded').append(tr);
				       }  
				       $("#teachernotification").empty();
				       $("#teachernotification").append("<th>Your Notifications</th>")
				       for (var i = 0; i < data.teacherDto.notifications.length; i++) {
				    	  var html="<tr><td>" + data.teacherDto.notifications[i] + "</td></tr>";
				           $('#teachernotification').append(html);
				       } 
					
		});
		
});


 
});




</script>


</body>
</html>