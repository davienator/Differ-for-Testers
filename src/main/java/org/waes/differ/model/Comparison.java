package org.waes.differ.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comparison {

	public enum Type { EQUAL, DIFFERENT_LENGTH, DIFFERENT_CHARS }
	private Type type;
	private String detail;
	
	public Comparison() {
		
	}

	public Comparison(Type type, String detail) {
		super();
		this.type = type;
		this.detail = detail;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
	
}
