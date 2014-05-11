<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>APLICACION MULTIALOE</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
<script src="../js/jquery/jquery-1.5.1.js"></script>
<script src="../js/jquery/jquery.ui.core.js"></script>
<script src="../js/jquery/jquery.ui.widget.js"></script>
<script src="../js/jquery/jquery.ui.accordion.js"></script>
<script src="../js/jquery/jquery.effects.core.js"></script>
<script src="../js/jquery/jquery.effects.drop.js"></script>
<script src="../js/jquery/jquery.ui.datepicker.js"></script>
<script src="../js/afiliado/afiliado.js"></script>
<script src="../js/generico.js"></script>
<link rel="StyleSheet" type="text/css"
	href="${ctx}/bootstrap/css/bootstrap.css"></link>
<link rel="stylesheet" href="../css/jquery/demos.css">
<link rel="stylesheet" href="../css/multinivel.css">
<style>
style>#accordion {
	height: 100px;
}
</style>
</head>
<body>
	<div align="center">
		<form name="forma" class="form-inline"
			action="AfiliadoFrontController" method="post">
			<div align="center" class="titulo">CONSULTA DE DATOS DE
				AFILIADO</div>
			<input name="accion" type="hidden" value="<c:out value='${accion}'/>" />
			<c:if test='${afiliado==null}'>
				<fieldset>
					<div class="form-group">
						<select name="nomFiltro" class="form-control input-sm">
							<option value="nombre">Nombre</option>
							<option value="apellido">Apellido</option>
							<option value="cedula">Cedula</option>
						</select>
					</div>
					<div class="form-group">
						<input type="text" class="form-control input-sm" name="filtro"
							required="required" pattern=".{3,}" title="Minimo 3 caracteres"
							maxlength="50">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-sm">Buscar</button>
					</div>
				</fieldset>
				<br/>
				<table class="tbl-lista" border="1">
					<tr>
						<th colspan="15">Lista de Afiliados</th>
					</tr>
					<tr>
						<th>Documento</th>
						<th>Nombre Afiliado</th>
						<th>Tipo</th>
						<th>Activo</th>
						<th>Seleccionar</th>
					</tr>
					<c:if test='${listaAfiliados!=null}'>
						<c:forEach var='af' items='${listaAfiliados}'>
							<tr>
								<td><c:out value='${af.cedula}' /></td>
								<td><c:out value='${af.nombre} ${af.apellido}' /></td>
								<td align="center"><c:if test='${af.tipoAfiliado=="1"}'>
										<c:out value="Administrador" />
									</c:if> <c:if test='${af.tipoAfiliado=="2"}'>
										<c:out value="Distribuidor" />
									</c:if> <c:if test='${af.tipoAfiliado=="3"}'>
										<c:out value="Afiliado" />
									</c:if></td>
								<td align="center"><c:out value='${af.activo}' /></td>
								<td><a
									href="AfiliadoFrontController?accion=E&letra=A&codigoEmpresario=${af.cedula}">Seleccionar</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</c:if>
			<c:if test='${afiliado!=null}'>
Código Nuevo Empresario: <input name="codigoEmpresario" type="text"
					size="30" value="<c:out value='${afiliado.cedula}'/>"
					readonly="readonly" />
			</c:if>
			<div class="demo">
				<c:if test='${afiliado!=null}'>
					<div id="accordion">
						<h3 class="tabla">DATOS PERSONALES</h3>
						<div id="datosPersonales">
							<table>
								<tr align="left">
									<td width="25%">Nombre: <c:out value='${afiliado.nombre}' /></td>
									<td width="25%">Apellidos Completos: <c:out
											value='${afiliado.apellido}' />
									</td>
									<td width="25%">Identificación:<br> <c:out
											value='${afiliado.cedula}' />
									</td>
									<td width="25%">Fecha de Nac: <fmt:formatDate
											pattern='yyyy-MM-dd' value='${afiliado.fechaNacimiento}' /></td>
								</tr>
								<tr align="left">
									<td>Lugar de Nac.: Ciudad <c:out
											value='${afiliado.ciudad}' />
									</td>
									<td height="26">Dpto <c:out
											value='${afiliado.departamento}' />
									</td>
								</tr>
								<tr align="left">
									<td>Barrio: <c:out value='${afiliado.barrio}' /></td>
									<td height="24">Dirección Resid. (completa): <c:out
											value='${afiliado.direccion}' />
									</td>
									<td>Ciudad: <c:out value='${afiliado.ciudadResidencia}' /></td>
									<td height="51">Departamento: <c:out
											value='${afiliado.departamentoResidencia}' /></td>
								</tr>
								<tr align="left">
									<td>Teléfono: <c:out value='${afiliado.telefono}' /></td>
									<td>Celular: <c:out value='${afiliado.celular}' /></td>
									<td>E-mail: <c:out value='${afiliado.email}' /></td>
								</tr>
							</table>
						</div>
						<h3 class="tabla">DATOS PARA PAGO DE RECONOCIMIENTOS
							MONETARIOS</h3>
						<div>
							<table>
								<tr>
									<td width="50%">Cuenta Número: <c:out
											value='${afiliado.cuentaNro}' />
									</td>
									<td width="50%">Entidad: <c:out
											value='${banco.descripcion}' />
									</td>
								</tr>
								<tr>
									<td>Tipo de cta. Ahorro <c:out
											value='${afiliado.tipoCuenta}' />
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2">Titular de la cuenta (Si es diferente al
										empresario)</td>
								</tr>
								<tr>
									<td>Nombre: <c:out value='${afiliado.nombreTitularCta}' />
									</td>
									<td>C.C. No.<c:out value='${afiliado.titularCuenta}' /></td>
								</tr>
								<tr>
									<td colspan="2">Autorizo que las bonificaciones generadas
										en mi red de MULTI - ALOE sean consignadas en esta cuenta.</td>
								</tr>
							</table>
						</div>
						<h3 class="tabla">DATOS DEL PATROCINADOR</h3>
						<div id="datosPatrocinador">
							<table>
								<tr>
									<td align="left">Nombres y Apellidos(completos) <c:out
											value='${patrocinador.nombre}' />&nbsp;<c:out
											value='${patrocinador.apellido}' />
									</td>
								</tr>
								<tr>
									<td align="left">Número de Empresario: <c:out
											value='${afiliado.cedulaPapa}' /> <input type="hidden"
										name="red">
									</td>
								</tr>
								<tr>
									<td>En mi calidad de Patrocinador postulante que suscribe
										el presente convenio vengo recomendarla(o) y me consta que se
										trata de una persona<br /> honorable y que representará
										adecuadamente la imagen y los productos de la empresa en su
										actuar como empresario independiente.
									</td>
								</tr>
							</table>
							<table>
								<tr>
									<td align="left">Codigo de Empresario(Distribuidor): <c:out
											value='${distribuidor.cedula}' />
									</td>
								</tr>
								<tr>
									<td align="left">Nombres y Apellidos del distribuidor <c:out
											value='${distribuidor.nombre}' />&nbsp; <c:out
											value='${distribuidor.apellido}' />
									</td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>
				<c:if test="${noExisteAfiliado==true}">
					<table class="tabla">
						<tr>
							<td>No existen los datos del afiliado en el sistema
								comuniquese con el administrador.</td>
						</tr>
					</table>
				</c:if>
			</div>
		</form>
	</div>
</body>
</html>


