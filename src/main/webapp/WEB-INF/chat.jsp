<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Chat Page</title>
</head>
<br><link rel="stylesheet" type="text/css" href="css/styleClient.css" />
<body>
	<div class="chat">
	    <pre class="preStyle">
	 		<%=request.getAttribute("contenu")%>
	    </pre>
		<form name="chatForm" action="chat" method="get">
			<input class="button1" type="text" name="ligne" value=""> 
 			<input class="button" type="submit" name="action" value="submit"> 
			<input class="button" type="submit" name="action" value="refresh">
			 <input class="button" type="submit" name="action" value="logout" />
		</form>
		
	</div>
</body>
</html>