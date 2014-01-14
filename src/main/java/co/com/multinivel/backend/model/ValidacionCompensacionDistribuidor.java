package co.com.multinivel.backend.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_validacion_compensacion_distribuidor")
public class ValidacionCompensacionDistribuidor implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private ValidacionCompensacionDistribuidorPK id;

	public ValidacionCompensacionDistribuidorPK getId() {
		return this.id;
	}

	public void setId(ValidacionCompensacionDistribuidorPK id) {
		this.id = id;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.model.ValidacionCompensacionDistribuidor
 */