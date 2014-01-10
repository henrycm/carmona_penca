<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" type="text/css" href="css/multinivel.css"></link>
<title>Informacion inicial</title>
</head>

<body>
  <body onload="document.f.j_username.focus();">
  
    <c:if test="${not empty param.login_error}">
      <font color="red">
        Tienes problemas para entrar al sistema comunicate con el administrador
        error lanzado: No te encuentras registrado o tú clave es incorrecta <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
      </font>
    </c:if>



    <form name="f" action="<c:url value='j_spring_security_check'/>"  method="post" >
    <img src="images/aloevera3.png" height="20%"/>
    <span align="center"> APLICACION MULTINIVEL</span>
  <br /><br /><br /><table align="center" width="938" border="0" >
    
    <tr>
      <td colspan="2" height="160" align="center">&nbsp;</td>
    </tr>
    <tr>
      <td width="169" height="30" class="encabezadoblan">Nombre de usuario</td>
      <td width="211"><input class="inputtext2" type='text' name='j_username' value=''/></td>
    </tr>
    <tr>
      <td height="30" class="encabezadoblan">Contraseña</td>
      <td> <input   class="inputtext2" type='password' name='j_password'></td>
    </tr>
    <tr>
      <td height="40" colspan="2">
      	<p align="center">
      	<input align="center" type="submit" name="Ingresar" id="Ingresar" value="Ingresar" class="formBoton" /> &nbsp;&nbsp;&nbsp;&nbsp;
	 <input type="reset" name="Limpiar" id="Limpiar" value="Limpiar" class="formBoton" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			</p>
      </td>
    </tr>
    <tr>
      <td height="40" colspan="2">
       <a href="cambiarClave">Cambiar Clave</a>
      </td>
    </tr>
     <tr class="encabezadoblan"><td colspan="2">
     	<input type="checkbox" name="_spring_security_remember_me">No preguntar de nuevo de la contraseña
      </td></tr>
    <tr>
      <td height="50">&nbsp;</td>
    </tr>
  </table>
  <p><c:if test="${not empty param.login_error}">El usuario no tiene acceso o no esta registrado en el sistema, comuniquese con el administrador</c:if></p>
</form>
</body>
</html>
