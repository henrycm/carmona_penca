package co.com.multinivel.backend.dao;

import co.com.multinivel.shared.dto.DatosArbol;

public interface ArbolDAO {

	public abstract DatosArbol getArbol(String paramString) throws Exception;

}