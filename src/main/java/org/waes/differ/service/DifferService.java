package org.waes.differ.service;

import org.waes.differ.model.Comparison;
import org.waes.differ.model.Sides;

public class DifferService {

	public Comparison validateDiff(Sides sides) {
		try {
			if (sides.getLeft().equals(sides.getRight())) {
			return new Comparison(Comparison.Type.EQUAL, null);
			}
			else if (sides.getLeft().length() != sides.getRight().length()) {
				return new Comparison(Comparison.Type.DIFFERENT_LENGTH, null);
			}
			else {
				return new Comparison(Comparison.Type.DIFFERENT_CHARS, getDiffLocation(sides));
			}
		} catch (NullPointerException e) {
			if (sides.getLeft() == null) {
				return new Comparison(Comparison.Type.DIFFERENT_LENGTH, "Left side contains no value.");
			} else {
//				return new Comparison(Type.DIFFERENT_LENGTH, "Right side contains no value.");
				return null;
			}
		}
	}
	
	private String getDiffLocation(Sides sides) {
		String response = "Values are different on char(s)";
		StringBuilder locations = new StringBuilder();
		int diffStart = 0, diffEnd = -1;
		for (int i = 0; i < sides.getLeft().length(); i++) {
			if (sides.getLeft().charAt(i) != sides.getRight().charAt(i)) {
				if (diffEnd == -1) {
					diffStart = i;
				}
				diffEnd = i;
			}
			if ((sides.getLeft().charAt(i) == sides.getRight().charAt(i)) ||
					i == sides.getLeft().length() - 1){
				if (diffEnd != -1) {
					if (diffStart == diffEnd) {
						locations.append(" [").append(diffStart).append("]");
					}
					else {
						locations.append(" [").append(diffStart).append("-").append(diffEnd).append("]");
					}
					diffEnd = -1;
				}
			}
		}
		response = response + locations + ".";
		return response;
	}

}
