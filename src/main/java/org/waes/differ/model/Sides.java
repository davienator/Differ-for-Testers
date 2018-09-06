package org.waes.differ.model;

import org.waes.differ.exceptions.SideNameNotSupportedException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sides {

	private String left;
	private String right;
	
	//serializable constructor
	public Sides() {
		
	}

	public Sides(String left, String right) {
		super();
		this.left = left;
		this.right = right;
	}

	public void updateSides(String side, String value) {
		switch (side) {
			case "left":
				this.left = value;
				break;
			case "right":
				this.right = value;
				break;
			default:
				throw new SideNameNotSupportedException("This side is not supported, please use either 'left' or 'right'.");
		}
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}	
	
}
