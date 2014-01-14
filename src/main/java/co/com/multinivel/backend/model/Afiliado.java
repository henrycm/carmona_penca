package co.com.multinivel.backend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_afiliados")
public class Afiliado implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String cedula;
	private String activo;
	private String apellido;
	private String banco;
	private String barrio;
	@Column(name = "cedula_papa")
	private String cedulaPapa;
	private String celular;
	private String ciudad;
	private String ciudadResidencia;
	@Column(name = "cuenta_nro")
	private String cuentaNro;
	private String departamento;
	private String departamentoResidencia;
	private String direccion;
	private String cedulaDistribuidor;
	private String cedulaDistribuidorPago;
	@Column(name = "email")
	private String email;
	private String estadoCivil;
	@Column(name = "FECHA_NACIMIENTO")
	private String fechaNacimiento;
	private String documentoConyugue;
	private String nombre;
	private String nombreConyugue;
	private String nombreTitularCta;
	private String ocupacion;
	private String red;
	private String telefono;
	@Column(name = "tipo_cuenta")
	private String tipoCuenta;
	private String tipoDocumento;
	private String tipoDocumentoConyugue;
	private Date fechaIngreso;
	private String tipoAfiliado;
	private String usuarioIngreso;
	@Column(name = "titular_cuenta")
	private String titularCuenta;
	@Column(name = "cambioDistribuidor", columnDefinition = "integer default 0")
	private int cambioDistribuidor;
	@Column(name = "idAfiliacionDist")
	private int idAfiliacionDistribuidor;

	public int getCambioDistribuidor() {
		return this.cambioDistribuidor;
	}

	public void setCambioDistribuidor(int cambioDistribuidor) {
		this.cambioDistribuidor = cambioDistribuidor;
	}

	public static long getSerialVersionUID() {
		return 1L;
	}

	public String getCedulaDistribuidor() {
		return this.cedulaDistribuidor;
	}

	public void setCedulaDistribuidor(String cedulaDistribuidor) {
		this.cedulaDistribuidor = cedulaDistribuidor;
	}

	public String getCedulaDistribuidorPago() {
		return this.cedulaDistribuidorPago;
	}

	public void setCedulaDistribuidorPago(String cedulaDistribuidorPago) {
		this.cedulaDistribuidorPago = cedulaDistribuidorPago;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getBarrio() {
		return this.barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getCedulaPapa() {
		return this.cedulaPapa;
	}

	public void setCedulaPapa(String cedulaPapa) {
		this.cedulaPapa = cedulaPapa;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCiudadResidencia() {
		return this.ciudadResidencia;
	}

	public void setCiudadResidencia(String ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}

	public String getCuentaNro() {
		return this.cuentaNro;
	}

	public void setCuentaNro(String cuentaNro) {
		this.cuentaNro = cuentaNro;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDepartamentoResidencia() {
		return this.departamentoResidencia;
	}

	public void setDepartamentoResidencia(String departamentoResidencia) {
		this.departamentoResidencia = departamentoResidencia;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreConyugue() {
		return this.nombreConyugue;
	}

	public void setNombreConyugue(String nombreConyugue) {
		this.nombreConyugue = nombreConyugue;
	}

	public String getNombreTitularCta() {
		return this.nombreTitularCta;
	}

	public void setNombreTitularCta(String nombreTitularCta) {
		this.nombreTitularCta = nombreTitularCta;
	}

	public String getOcupacion() {
		return this.ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getRed() {
		return this.red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoCuenta() {
		return this.tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoDocumentoConyugue() {
		return this.tipoDocumentoConyugue;
	}

	public void setTipoDocumentoConyugue(String tipoDocumentoConyugue) {
		this.tipoDocumentoConyugue = tipoDocumentoConyugue;
	}

	public String getTitularCuenta() {
		return this.titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}

	public void setDocumentoConyugue(String documentoConyugue) {
		this.documentoConyugue = documentoConyugue;
	}

	public String getDocumentoConyugue() {
		return this.documentoConyugue;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setUsuarioIngreso(String usuarioIngreso) {
		this.usuarioIngreso = usuarioIngreso;
	}

	public String getUsuarioIngreso() {
		return this.usuarioIngreso;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getTipoAfiliado() {
		return this.tipoAfiliado;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setIdAfiliacionDistribuidor(int idAfiliacionDistribuidor) {
		this.idAfiliacionDistribuidor = idAfiliacionDistribuidor;
	}

	public int getIdAfiliacionDistribuidor() {
		return this.idAfiliacionDistribuidor;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.model.Afiliado
 */