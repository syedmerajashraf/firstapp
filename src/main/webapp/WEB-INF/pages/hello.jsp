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
</ul>
<div id="home">
<p >Welcome To Student Teacher Relation Site</p>
<ul>
<li><a href="#teacher" id="clickmeteacher">Teacher's Click Here</a></li>
<li><a href="#student" id="clickmestudent">Students Zone</a></li>
</ul>
<p> ${message}</p>
</div>
<div id="teacher">
<p style="{color: "red" }">Welcome Teachers</p>
<button id="new-teacher">Sign Up</button>
<select id="tlist">
<c:forEach items="${teacher_db}" var="teacher" >
            	
            	<option  >${teacher.key}</option>
  </c:forEach>       	
 </select>
</div>
<div id="student">
<p style="{color: "red" }">Welcome Students</p>
<button id="new-student">Sign Up</button>
<select id="slist">
<c:forEach items="${student_db}" var="student" >
            	
            	<option  value="${student.key}">${student.key}</option>
  </c:forEach>       	
 </select>
 
 <div id="maindiv" style="display:none">
     <table>
     <th>Name</th>
     <th>Email</th>
    
     <tr>
 	<td> <span id="name"></span></td>
 	 <td><span id="email"></span></td>
 	 
 	 
 	 </tr>
 	 </table>
 	 <table id="stlist">
 	
 	 
 	 </table>
 	 <table id="teachertobeadded">
 	 
 	 
 	 </table>
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
<label for="password">Password</label>
<input type="password" name="password" id="tpassword" value="" class="text ui-widget-content ui-corner-all" />

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
	$( "#student-form" ).dialog({
		autoOpen: false,
		height: 300,
		width: 350,
		modal: true,
		buttons: {
		"Create an account":function(){
			var student = new Object();
			student.name=$( "#sname" ).val();
			student.email=$("#semail").val();
			alert(student.name+student.email);

			
			
			 $.ajax({
				 url: 'newstudent',
				 type: 'POST',
				 contentType: "application/json",
				 dataType: "json",
				 data:JSON.stringify(student),
				 success : function(response){
					alert(response);
					
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
			teacher.name=$( "#tname" ).val();
			teacher.email=$("#temail").val();
			alert(teacher.name+teacher.email);

			
			
			 $.ajax({
				 url: 'newteacher',
				 type: 'POST',
				 contentType: "application/json",
				 dataType: "json",
				 data:JSON.stringify(teacher),
				 success : function(response){
					alert(response);
					
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
	$( "#new-teacher" )
	.button()
	.click(function() {
	
	$( "#teacher-form" ).dialog( "open" );
	});
	$( "#new-student" )
	.button()
	.click(function() {
	
	$( "#student-form" ).dialog( "open" );
	});

$("#slist").change(function() {
alert( $(this).val() );
var selected=$(this).val();

$.get( "getstudent","id="+selected, function( data ) {
	//alert( "Data Loaded: " + data.student.name );
	$("#maindiv").css("display","block");
	 $("#name").html(data.student.name);
	 $("#email").html(data.student.email);
	 $('table#stlist').empty();
	 $('table#stlist').append("<th>Name</th><th>Email</th>");
	 var tr;
	 
	 
     for (var i = 0; i < data.teacher.length; i++) {
    	 tr = $('<tr/>');
         tr.append("<td>" + data.teacher[i].name + "</td>");
         tr.append("<td>" + data.teacher[i].email + "</td>");
         tr.append("<td><a href='#'>Remove</a></td>");
         $('table#stlist').append(tr);
     }
       $('#teachertobeadded').empty();
       $('#teachertobeadded').append("<th>Name</th><th>Email</th>");

       for (var i = 0; i < data.teacher_to_be_added.length; i++) {
      	 tr = $('<tr/>');
           tr.append("<td>" + data.teacher_to_be_added[i].name + "</td>");
           tr.append("<td>" + data.teacher_to_be_added[i].email + "</td>");
           tr.append("<td><a name=\"add_teacher\" href='#'>Add</a></td>");
           $('#teachertobeadded').append(tr);
       }

       $('table#stlist').on('click', function(e) {
    	    var email=$(e.target).closest('tr').find("td:nth-child(2)").text();
    	    var id   =$("#slist").val();
    	    alert(id+"  "+ email);
    	    $.get( "removeteacher","id="+id+"&email="+email, function( data ) {
    	        alert(data);
    	        });
    	    
    	});
   	
    	 $('[name=add_teacher]').on('click', function(e) {
    		    var addTeacherBtn = $(this);
    		    var email=$(e.target).closest('tr').find("td:nth-child(2)").text();
    		    var id   =$("#slist").val();
    		    alert(id+"  "+ email);
    		    $.get( "addteacherforstudent","id="+id+"&email="+email, function( teacher ) {
    		        alert(data);
    		        var newTeacher = '<tr>
					    		        <td>
					    		        	<span id="name">ajeet</span>
					    		        </td>
					    		        <td>
					    		        <span id="email">ajeet@talentica.com</span>
					    		        </td>
					    		        </tr>
    		        addTeacherBtn.parent().parent().remove();
		         	console.log(btn.parent());
    		        });
    		    
    		});
	});

});

/*$("document").on("click","a",function(){
	alert("hiiii");
});*/


 
});




</script>


</body>
</html>