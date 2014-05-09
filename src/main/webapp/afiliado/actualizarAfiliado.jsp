<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>APLICACION MULTIALOE</title>
<title>jQuery UI Accordion - Default functionality</title>
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

<script>
	$(document).ready(function() {
		$("input[type=text]").attr("required", "required");
		$("select").attr("required", "required");
		$(":required").before("<strong>*&nbsp;</strong>");
		$("#cmdDistribuidor").hide();
		$("#tipoAfiliado").change(function() {
			if ($(this).val() == 3) {
				$("#cmdDistribuidor").show();
				$("#cmdDistribuidor select").attr("required", "required");
			} else {
				$("#cmdDistribuidor").hide();
				$("#cmdDistribuidor select").removeAttr("required");
			}
		});
	});

	$(function() {
		$("#fecha").datepicker({
			maxDate : '-18y',
			dateFormat : 'yy-mm-dd'
		});
		$("#fecha").datepicker("option", "showAnim", "drop");

	});
</script>
<style>
<
style>#accordion {
	height: 100px;
}
</style>
</head>
<c:if test='${afiliado!=null}'>
	<body
		onload="javascript:cargarCombosAfiliado('<c:out value="${afiliado.departamento}"/>','<c:out value="${afiliado.departamentoResidencia}"/>','<c:out value="${afiliado.tipoDocumento}"/>','<c:out value="${afiliado.estadoCivil}"/>','<c:out value="${afiliado.banco}"/>');cargarTipoCta('<c:out value="${afiliado.tipoCuenta	}"/>');">
</c:if>
<c:if test='${afiliado==null}'>
	<body>
</c:if>
<div align="center">
	<form name="forma" action="AfiliadoFrontController" method="post">
		<input type="hidden" name="borrar">
		<c:if test='${afiliado!=null}'>
			<div class="btn-group">
				<a class="btn btn-sm btn-default"
					href="javascript:actualizarAfiliado();">Actualizar</a>
				<c:if test="${rol=='1'}">
					<a class="btn btn-sm btn-default"
						href="javascript:eliminarAfiliado()">Eliminar</a>
				</c:if>
				<div class="separador"></div>
			</div>
		</c:if>
		<div align="center" class="titulo">ACTUALIZACION DE DATOS DE
			AFILIADO</div>
		<div>
			<input name="accion" type="hidden" value="<c:out value='${accion}'/>" />

			<c:if test='${afiliado==null}'>

				<table class="tbl-lista">
					<tr>
						<td>Código del Empresario: <input name="codigoEmpresario"
							type="text" size="30" /> <select name="letra">
								<option value="A">A</option>
						</select>
							<div class="btn-group">
								<a class="btn btn-sm btn-default"
									href="javascript:consultarAfiliado()">Consultar</a>
							</div>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test='${afiliado!=null}'>
Código del Empresario: <input name="codigoEmpresario" type="text"
					size="30" value="<c:out value='${afiliado.cedula}'/>"
					readonly="readonly" />
				<c:if test="${mensaje!=null}">
					<table class="tabla">
						<tr>
							<td align="center">
								<h5 style="color: red">
									<c:out value='${mensaje}' />
								</h5>
							</td>
						</tr>
					</table>
				</c:if>
			</c:if>
		</div>
		<div class="demo">
			<c:if test='${afiliado!=null}'>

				<div id="accordion">
					<div class="titulo">DATOS PERSONALES</div>
					<div>
						<table>
							<tr>
								<td style="color: red">Estado:<select name="estado" ${rol=='1' ? '' : 'disabled'}>
										<c:if test='${afiliado.activo=="si"}'>
											<option value="si" Selected>Activo</option>
											<option value="no">Inactivo</option>
										</c:if>
										<c:if test='${afiliado.activo=="no"}'>
											<option value="no" Selected>Inactivo</option>
											<option value="si">Activo</option>
										</c:if>
								</select>
								</td>
								<td>Tipo de Afiliado: <label style="color: blue"><c:out
											value="${afiliado.tipoAfiliado == 1 ? 'Administrador' : afiliado.tipoAfiliado == 2 ? 'Distribuidor' : 'Afiliado'}" /></label>
								</td>
								<td><input name="tipoAfiliado" type="hidden"
									value="<c:out value="${afiliado.tipoAfiliado}"/>"></td>
							</tr>
							<tr>
								<td>Nombre: <input name="nombre" type="text" size="20"
									maxlength="40" value="<c:out value='${afiliado.nombre}'/>" /></td>
								<td>Apellidos Completos: <input name="apellido" type="text"
									maxlength="50" size="15"
									value="<c:out value='${afiliado.apellido}'/>" /></td>
								<td>Identificación:<br> <select name="tipoDocumento">
										<option value="C.C.">C.C.</option>
										<option value="Nit">Nit</option>
								</select> <input name="numeroIdentificacion" type="text" size="13"
									value="<c:out value='${afiliado.cedula}'/>" maxlength="13" />
								</td>
								<td>Fecha de Nac: <input name="fechaNacimiento" type="text"
									class="ui-datepicker-calendar" id="fecha" size="15"
									value="<fmt:formatDate pattern='yyyy-MM-dd' 
            value='${afiliado.fechaNacimiento}' />" /></td>
							</tr>
							<tr>
								<td>Lugar de Nac.: Ciudad <input name="ciudadNacimiento"
									type="text" size="13"
									value="<c:out value='${afiliado.ciudad}'/>" />
								</td>
								<td height="26">Dpto Nac <select id="departamento"
									name="departamento">
										<option value="">Elija el depart...</option>
										<c:forEach var='dept' items='${listaDepartamentos}'>
											<option value="<c:out value='${dept.codigo}'/>">
												<c:out value='${dept.descripcion}' />
											</option>
										</c:forEach>
								</select>
								</td>
							</tr>
							<tr>
								<td>Barrio:<input name="barrio" type="text" size="25"
									value="<c:out value='${afiliado.barrio}'/>" /></td>
								<td height="24">Dirección Resid. (completa): <input
									name="direccion" type="text" size="15"
									value="<c:out value='${afiliado.direccion}'/>" />
								</td>
								<td>Ciudad: <input name="ciudadResidencia" type="text"
									size="15" value="<c:out value='${afiliado.ciudadResidencia}'/>" /></td>
								<td height="51">Departamento: <select
									id="departamentoResidencia" name="departamentoResidencia"
									size="1">
										<option value="">Elija el depart...</option>
										<c:forEach var='dept' items='${listaDepartamentos}'>
											<option value="<c:out value='${dept.codigo}'/>">
												<c:out value='${dept.descripcion}' />
											</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>Teléfono: <input name="telefono" type="text" size="10"
									value="<c:out value='${afiliado.telefono}'/>" /></td>
								<td>Celular: <input name="celular" type="text" size="10"
									value="<c:out value='${afiliado.celular}'/>" />
								</td>
								<td>E-mail: <input name="email" type="text" size="20"
									maxlength="50" value="<c:out value='${afiliado.email}'/>" /></td>
							</tr>
						</table>
					</div>
					<div class="titulo">DATOS PARA PAGO DE RECONOCIMIENTOS
						MONETARIOS</div>
					<div>
						<table>
							<tr>
								<td>PAGAR A DISTRIBUIDOR <input name="pagarADistribuidor"
									type="checkbox" onclick="javascript:validarChequeCta()" />

								</td>
								<td>Cuenta Número: <input name="numeroCuenta" type="text"
									size="20" value="<c:out value='${afiliado.cuentaNro}'/>"
									maxlength="20" />
								</td>
								<td>Entidad:<select id="entidadBancaria"
									name="entidadBancaria">
										<option value="">Elija el banco</option>
										<c:forEach var='banco' items='${listaBancos}'>
											<option value="<c:out value='${banco.codigo}'/>">
												<c:out value='${banco.descripcion}' />
											</option>
										</c:forEach>
								</select>
								</td>
							</tr>
							<tr>
								<td>Tipo de cta. Ahorro <input name="tipoCuenta"
									type="radio" value="AHORRO" checked="checked" /> Cte. <input
									name="tipoCuenta" type="radio" value="CRTE" />
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>Titular de la cuenta (Si es diferente al empresario)</td>
							</tr>
							<tr>
								<td>Nombre: <input name="nombreTitular" type="text"
									size="30" maxlength="50"
									value="<c:out value='${afiliado.nombreTitularCta}'/>" />
								</td>
								<td>C.C. No.<input name="documentoTitular" type="text"
									size="15" maxlength="15"
									value="<c:out value='${afiliado.titularCuenta}'/>" /></td>
							</tr>
							<tr>
								<td>Autorizo que las bonificaciones generadas en mi red de
									ALOE de COLOMBIA sean consignadas en esta cuenta.</td>
							</tr>
						</table>
					</div>
					<div class="titulo">DATOS DEL PATROCINADOR</div>
					<div id="datosPatrocinador">
						<table>
							<tr>
								<td>Nombres y Apellidos(completos) <c:out
										value='${patrocinador.nombre}' /> &nbsp;<c:out
										value='${patrocinador.apellido}' />
								</td>
							</tr>
							<tr>
								<td>Número de Empresario: <input name="numeroEmpresario"
									type="hidden" value="<c:out value='${afiliado.cedulaPapa}'/>" />
									<c:out value='${afiliado.cedulaPapa}' /> <input type="hidden"
									name="red" value="<c:out value='${afiliado.red}'/>">

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
								<td>Nombres y Apellidos del distribuidor <input
									name="nombreDistribuidor" type="text" size="50"
									value="<c:out value='${distribuidor.nombre}'/> <c:out value='${distribuidor.apellido}'/>"
									readonly="readonly"
									onclick="javascript:document.forma.nombrePatrocinador.readOnly =true;" />
								</td>
							</tr>
							<tr>
								<td>Codigo de Empresario(Distribuidor): <input
									name="numeroDistribuidor"
									value="<c:out value='${distribuidor.cedula}'/>" type="text"
									size="15" readonly="readonly"
									onclick="javascript:document.forma.numeroEmpresario.readOnly =true;" />
									<c:if test="${rol=='1'}">
										<a href="javascript:abrirBuscarDistribuidor()">Buscar
											Distribuidor</a>
									</c:if> <input type="hidden" name="numeroCuentaDistribuidor"
									value="<c:out value='${distribuidor.cuentaNro}'/>"> <input
									type="hidden" name="tipoCuentaDistribuidor"
									value="<c:out value='${distribuidor.tipoCuenta}'/>"> <input
									type="hidden" name="bancoDistribuidor"
									value="<c:out value='${distribuidor.banco}'/>">
								</td>
							</tr>
						</table>
					</div>
				</div>
			</c:if>
		</div>
	</form>
</div>
</body>
</html>


