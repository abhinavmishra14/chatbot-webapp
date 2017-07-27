<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css' />" />
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-1.4.3.min.js' />"></script>
<script type="text/javascript">
	function ajaxRequest(actionName, formData, callback, options) {
		var optionsLocal = {
			"showSpinner" : options
		};

		optionsLocal = $.extend(optionsLocal, options);

		$.ajax({
			url : actionName,
			type : "POST",
			cache : false,
			data : formData,
			dataType : "html",
			context : document.body,
			success : function(data) {
				alert(data);
			},
			beforeSend : function() {
				if (optionsLocal.showSpinner == true) {
					$('#ajaxImage').show();
					$('#ajaxBackground').show();
				}
			},
			complete : function() {
				if (optionsLocal.showSpinner == true) {
					$('#ajaxImage').hide();
					$('#ajaxBackground').hide();
				}
			},
			error : function() {
				//Hide the spinner div
				$('#ajaxImage').hide();
				$('#ajaxBackground').hide();
			}
		});
	};

	function sendChat() {
		alert($("#answer").val());
		var formData = $('form[name=chatBotInteraction]').serialize();
		alert(formData);
		ajaxRequest("chat", formData, renderChat, {
			"showSpinner" : false
		})
	}

	function renderChat(data, options) {
		alert(data);
	}
</script>
<title><spring:message code="chatbot.title" /></title>
</head>
<body>
	<form name="chatBotInteraction" method="post">
	    <%-- Set the username from session or request --%>
		<input type="hidden" id="hiddenUserName" name="userName" value='<%=request.getParameter("userName")%>'/>
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
						<textarea id="chat" name="chat" rows="10" cols="70" readonly></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<textarea id="answer" name="answer"
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
</body>
</html>