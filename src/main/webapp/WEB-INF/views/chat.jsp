<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css' />" />
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-3.2.1.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/chatbot-commons.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/config.js' />"></script>
	
<script type="text/javascript">
	$(document).ready(function() {
		//Tab press
		$('#userText').keydown(function(e) {
			var keypressed = event.keyCode || event.which;
			if (keypressed == 9) {
				sendChat();
			}
		});
		
		//Press enter key
		$('#userText').keypress(function(e) {
			var keypressed = event.keyCode || event.which;
			if (keypressed == 13) {
				sendChat();
			}
		});
	});
	
	function sendChat() {
		//Clear errors
		$("#valErr").remove();
		var validationResp = validateForm("chatBotInteraction", [ "chat" ]);
		var objKeys = Object.keys(validationResp);
		if (objKeys != null && objKeys.length > 0) {
			for ( var eachKey in validationResp) {
				console.log(validationResp[eachKey]);
				if (eachKey == "userName") {
					alert(validationResp[eachKey]);
				} else {
					$('textarea[name=' + eachKey + ']').after(
							'<span id="valErr" style="color:red;">'
									+ validationResp[eachKey] + '</span>');
				}
			}
			return false;
		} else {
			var formData = $('form[name=chatBotInteraction]').serializeArray();
			var formDataJson = getFormData(formData);
			ajaxRequest(properties.actionName, formDataJson,
					renderChat, {
						"showSpinner" : false
					})
		}
	}

	function renderChat(data, options) {
		console.log(data); //Log response.
		var chatArea = $("#chat");
		var userName = $("#userName");
		var userInput = $("#userText");
		userInput.val(""); // Set user input as blank

		if (chatArea.val() != null || chatArea.val() != '') {
			chatArea.append("\n"); //If chat is already initiated then appen a new line
		}
		chatArea.append("\n"+data.userText); //Add userprovided input to chat area
		chatArea.append("\n"+data.botMessage); // append bot message
		//Auto Scroll to bottom
		if (chatArea.length) {
			chatArea.scrollTop(chatArea[0].scrollHeight - chatArea.height());
		}
	}
</script>
<title><spring:message code="chatbot.title" /></title>
</head>
<body>
	<div id="greeting">
		<table style="float: left;">
			<tr>
				<td colspan="2">
					<div align="left">
						<h5>${greeting}</h5>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<br style="clear: both;" />
	<div id="chatbox" class="gradientBoxesWithOuterShadows">
		<form name="chatBotInteraction" method="post">
			<%-- Set the userName from session or request --%>
			<input type="hidden" id="hiddenUserName" name="userName"
				value='<%=request.getParameter("userName")%>' />
			<table align="center">
				<tr>
					<td colspan="2">
						<div align="center">
							<textarea id="chat" name="chat" rows="14" cols="70" readonly></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<textarea id="userText" name="userText"
								placeholder="Enter your messagehere..." rows="2" cols="70"></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="sendBtn">
						<div align="right">
							<spring:message code="chat.send" var="chatSend"></spring:message>
							<button id="send" type="button" onclick="javascript:sendChat();">${chatSend}</button>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>