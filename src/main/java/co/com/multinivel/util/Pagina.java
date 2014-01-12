package co.com.multinivel.util;

import java.util.Collection;

public class Pagina {
	private int totalPages;
	private int number;
	private int totalElements;
	private Collection<Object> content;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public Collection<Object> getContent() {
		return content;
	}

	public void setContent(Collection<Object> content) {
		this.content = content;
	}

}
