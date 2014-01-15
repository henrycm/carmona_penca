package co.com.multinivel.frontend.arbol;

import java.io.Serializable;

public class BookProperty implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private Object value;
	private boolean required;

	public String getName() {
		return this.name;
	}

	public void setName(String paramString) {
		this.name = paramString;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object paramObject) {
		this.value = paramObject;
	}

	public boolean isRequired() {
		return this.required;
	}

	public void setRequired(boolean paramBoolean) {
		this.required = paramBoolean;
	}

	public BookProperty(String paramString, boolean paramBoolean) {
		this.name = paramString;

		this.required = paramBoolean;
	}

	public BookProperty(String paramString, Object paramObject, boolean paramBoolean) {
		this.name = paramString;

		this.value = paramObject;

		this.required = paramBoolean;
	}
}

/*
 * Location: D:\tmp\arbol\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.controller.BookProperty
 * 
 * JD-Core Version: 0.7.0.1
 */