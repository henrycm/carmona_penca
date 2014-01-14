package co.com.multinivel.dto;

public class UsuarioDTO {
	private String username;
	private String password;
	private String distribuidor;
	private byte enabled;
	private String passwordDistribuidor;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDistribuidor() {
		return this.distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setPasswordDistribuidor(String passwordDistribuidor) {
		this.passwordDistribuidor = passwordDistribuidor;
	}

	public String getPasswordDistribuidor() {
		return this.passwordDistribuidor;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dto.UsuarioDTO
 */