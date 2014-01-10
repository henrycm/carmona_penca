package co.com.multinivel.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_liquidacion_premiacion")
public class LiquidacionPremiacion implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private LiquidacionPremiacionPK id;

	public LiquidacionPremiacionPK getId() {
		return this.id;
	}

	public void setId(LiquidacionPremiacionPK id) {
		this.id = id;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.model.LiquidacionPremiacion
 * 
 * 
 */