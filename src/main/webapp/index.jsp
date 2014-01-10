<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>MULTINIVEL - ALOE DE COLOMBIA</title>
</head>

<frameset rows="100,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="encabezado.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset rows="*" cols="300,*" framespacing="1" frameborder="no" border="0" bordercolor="#000000">
    <c:if test="${rol=='1'}"> 
    	<frame src="menu.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    </c:if>
     <c:if test="${rol=='2'}"> 
    	<frame src="menuDistribuidor.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    </c:if>
      <c:if test="${rol=='3'}"> 
    	<frame src="menuAfiliado.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    </c:if>
    	<frame src="contenido.html" name="mainFrame" id="mainFrame" title="mainFrame" />
  </frameset>
</frameset>
<noframes><body>
Este navegador no soporta FRAME
</body>
</noframes></html>
