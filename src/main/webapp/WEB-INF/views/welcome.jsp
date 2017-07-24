<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<form action='interactWithUser' method="post">
		<table style="float: left;">
			<tr>
				<td colspan="2">
					<div align="left">
						<h5>${greeting}</h5>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<textarea name="answer" placeholder="Enter your messagehere..."
							rows="10" cols="70"></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="sendBtn">
					<div align="right">
						<button type="submit" value="submit">Send</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>