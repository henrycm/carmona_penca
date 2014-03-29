package co.com.multinivel.shared.dto;

public class UsuarioDTO {
	private String username;
	private String nombreUsuario;
	private String password;
	private String distribuidor;
	private byte enabled;
	private String passwordDistribuidor;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDistribuidor() {
		return distribuidor;
	}
	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}
	public byte getEnabled() {
		return enabled;
	}
	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}
	public String getPasswordDistribuidor() {
		return passwordDistribuidor;
	}
	public void setPasswordDistribuidor(String passwordDistribuidor) {
		this.passwordDistribuidor = passwordDistribuidor;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.shared.dto.UsuarioDTO
 */