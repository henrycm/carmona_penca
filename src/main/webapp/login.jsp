<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" type="text/css" href="css/multinivel.css"></link>
<link rel="StyleSheet" type="text/css"
	href="bootstrap/css/bootstrap.css"></link>
<title>Informacion inicial</title>

<c:set var="ctx" value="${pageContext.request.contextPath}"
	scope="session" />

</head>

<body onload="document.f.j_username.focus();">
	<div id="form_caja">
		<jsp:include page="encabezado.jsp"></jsp:include>
		<div id="div_login">
			<div style="height: 30px;"></div>
			<c:if test="${not empty param.login_error}">
				<div class="alert alert-danger">
					Tienes problemas para entrar al sistema comunicate con el
					administrador error lanzado: No te encuentras registrado o la clave
					es incorrecta
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
					.
				</div>
			</c:if>
			<form name="f" action="<c:url value='j_spring_security_check'/>"
				method="post" class="form-horizontal">
				<fieldset>
					<h4>INGRESO A LA PLATAFORMA MULTINIVEL</h4>
					<br></br>
					<div class="form-group">
						<label class="col-sm-3 control-label">Nombre de usuario:</label>
						<div class="col-sm-3">
							<input class="form-control" type='text' name='j_username'
								value='' />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Contraseņa:</label>
						<div class="col-sm-3">
							<input class="form-control" type='password' name='j_password' />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-4">
							<div class="checkbox">
								<label> <input type="checkbox"
									name="j_spring_security_remember_me" /> <c:out
										value="No preguntar de nuevo de la contraseņa" />
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-4">
							<input type="submit" class="boton" name="Ingresar" id="Ingresar"
								value="Ingresar" /> <input type="reset" class="boton"
								name="Limpiar" id="Limpiar" value="Limpiar" />
						</div>
					</div>
					<!-- 
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-6">
							<a href="cambiarClave">Cambiar Clave</a>
						</div>
					</div>
					 -->
				</fieldset>
				<c:if test="${not empty param.login_error}">
					<div class="alert alert-danger">El usuario no tiene acceso o
						no esta registrado en el sistema, comuniquese con el administrador</div>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>
