<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root version="2.0"
      xmlns:jsp="http://java.sun.com/JSP/Page"
      xmlns:c="http://java.sun.com/jsp/jstl/core"
      xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
      xmlns:fn="http://java.sun.com/jsp/jstl/functions">
<jsp:directive.page language="java"
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<jsp:text><![CDATA[<?xml version="1.0" encoding="UTF-8" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">]]>
</jsp:text>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"      
      xmlns:h="http://java.sun.com/jsf/html">
<head>
	<title>Add New User Form</title>
</head>
<body>
<f:view>
		<p>
			<h:message id="errors" for="User_ID" style="color:red"/>
		</p>
	<h:form>
		<h:panelGrid border="1" columns="2">
			<h:outputText value="ID"></h:outputText>
			<h:inputText id="User_ID" value="#{userBean.id}" required="true">
				<f:validateLongRange minimum="1" maximum="500"/>
			</h:inputText>
			<h:outputText value="Name"></h:outputText>
			<h:inputText value="#{userBean.name}"></h:inputText>
			<h:commandButton action="#{userBean.addUser}" 
				value="Add Customer"></h:commandButton>
		</h:panelGrid>
	</h:form>
</f:view>
</body>
</html>
</jsp:root>