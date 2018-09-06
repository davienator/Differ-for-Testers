package org.waes.differ;

import org.junit.Test;
import org.waes.differ.model.Comparison;
import org.waes.differ.model.Comparison.Type;
import org.waes.differ.model.Sides;
import org.waes.differ.service.DifferService;

import static org.junit.Assert.assertEquals;

public class DifferServiceTest {

	@Test
	public void testDifferOfEqualValuesShouldReturnEqual() {
		DifferService tester = new DifferService();
		Sides equalSides = new Sides("123abc", "123abc");
		Comparison comparison = new Comparison(Type.EQUAL, null);
		assertEquals("123abc and 123abc values must be EQUAL.",
				comparison.getType(), tester.validateDiff(equalSides).getType());
	}
	
	@Test
	public void testDifferOfDifferentLengthsShouldReturnUnequalLengths() {
		DifferService tester = new DifferService();
		Sides unequalSidesLength = new Sides("1b3a2c", "123abcd");
		Comparison comparison = new Comparison(Type.DIFFERENT_LENGTH, null);
		assertEquals("123abc and 123abcd values must be DIFFERENT in LENGHT.",
				comparison.getType(), tester.validateDiff(unequalSidesLength).getType());
	}
	
	@Test
	public void testDifferAgainstNullLeftShouldReturnUnequalLengths() {
		DifferService tester = new DifferService();
		Sides unequalSidesLength = new Sides(null, "1b3a2c");
		Comparison comparison = new Comparison(Type.DIFFERENT_LENGTH, "Left side contains no value.");
		assertEquals("1b3a2c and null values must be DIFFERENT in LENGHT.",
				comparison.getType(), tester.validateDiff(unequalSidesLength).getType());
		assertEquals("Null Left should return detail information.",
				comparison.getDetail(), tester.validateDiff(unequalSidesLength).getDetail());
	}

	@Test
	public void testDifferOfDifferentValuesShouldReturnUnequalValues() {
		DifferService tester = new DifferService();
		Sides unequalSidesValue = new Sides("ab3d5f", "123456");
		Comparison comparison = new Comparison(Type.DIFFERENT_CHARS, null);
		assertEquals("ab3d5f and 123456 values must be DIFFERENT in values.",
				comparison.getType(), tester.validateDiff(unequalSidesValue).getType());
	}
	
	@Test
	public void testDifferOfDifferentValuesShouldReturnLocationsOfUnequalValuesPositions0135() {
		DifferService tester = new DifferService();
		Sides unequalSidesValue = new Sides("ab3d5f", "123456");
		Comparison comparison = new Comparison(null, "Values are different on char(s) [0-1] [3] [5].");
		assertEquals("ab3d5f and 123456 values must be DIFFERENT at chars 0-1, 3 and 5.",
				comparison.getDetail(), tester.validateDiff(unequalSidesValue).getDetail());
	}
	
	@Test
	public void testDifferOfDifferentValuesShouldReturnLocationsOfUnequalValuesPositions0245() {
		DifferService tester = new DifferService();
		Sides unequalSidesValue = new Sides("a2c4ef", "123456");
		Comparison comparison = new Comparison(null, "Values are different on char(s) [0] [2] [4-5].");
		assertEquals("a2c4ef and 123456 values must be DIFFERENT at chars 0, 2 and 4-5.",
				comparison.getDetail(), tester.validateDiff(unequalSidesValue).getDetail());
	}
	
	@Test
	public void testDifferOfDifferentValuesShouldReturnLocationsOfUnequalValuesPositions14() {
		DifferService tester = new DifferService();
		Sides unequalSidesValue = new Sides("1b34e6", "123456");
		Comparison comparison = new Comparison(null, "Values are different on char(s) [1] [4].");
		assertEquals("1b34e6 and 123456 values must be DIFFERENT at chars 1 and 4.",
				comparison.getDetail(), tester.validateDiff(unequalSidesValue).getDetail());
	}
	
	@Test
	public void testDifferOfDifferentValuesShouldReturnLocationsOfUnequalValuesPositions23() {
		DifferService tester = new DifferService();
		Sides unequalSidesValue = new Sides("12cd56", "123456");
		Comparison comparison = new Comparison(null, "Values are different on char(s) [2-3].");
		assertEquals("12cd56 and 123456 values must be DIFFERENT at chars 2-3.",
				comparison.getDetail(), tester.validateDiff(unequalSidesValue).getDetail());
	}
}
