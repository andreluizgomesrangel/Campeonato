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
	<title>List of Users</title>
</head>
<body>
<f:view>
	<h:form>
		<h:outputText value="User #{userBean.name} is added successfully.">
		</h:outputText>
	</h:form>
</f:view>
</body>
</html>
</jsp:root>